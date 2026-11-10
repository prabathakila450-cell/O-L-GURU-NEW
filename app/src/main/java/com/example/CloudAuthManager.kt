package com.example

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

object CloudAuthManager {
    private const val TAG = "CloudAuthManager"
    private const val COLLECTION_USERS = "users"
    private var isSettingsConfigured = false

    private fun getFirestore(context: Context): FirebaseFirestore {
        val db = try {
            FirebaseFirestore.getInstance(context.getString(R.string.firestore_database_id))
        } catch (_: Exception) {
            FirebaseFirestore.getInstance()
        }
        if (!isSettingsConfigured) {
            try {
                val settings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
                db.firestoreSettings = settings
                isSettingsConfigured = true
            } catch (e: Exception) {
                Log.d(TAG, "Firestore settings already configured or set: ${e.localizedMessage}")
                isSettingsConfigured = true
            }
        }
        return db
    }

    suspend fun syncUserToCloud(context: Context, user: UserAccount) {
        try {
            val db = getFirestore(context)
            val docId = user.usernameOrPhone.trim().lowercase().replace("+", "").replace(" ", "").replace("-", "")
            if (docId.isBlank()) return
            
            db.collection(COLLECTION_USERS).document(docId).set(user, SetOptions.merge()).await()
            Log.d(TAG, "User synced to cloud: $docId")
        } catch (e: Exception) {
            Log.e(TAG, "Error syncing user to cloud", e)
        }
    }

    suspend fun fetchLatestUsersOnce(context: Context): List<UserAccount> {
        return try {
            val db = getFirestore(context)
            val snapshot = db.collection(COLLECTION_USERS).get().await()
            snapshot.documents.mapNotNull { doc ->
                try {
                    doc.toObject(UserAccount::class.java)
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing user doc ${doc.id}", e)
                    null
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching users once", e)
            emptyList()
        }
    }

    fun observeAllUsers(context: Context): Flow<List<UserAccount>> = callbackFlow {
        val db = getFirestore(context)
        val listener = db.collection(COLLECTION_USERS)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e(TAG, "Error observing users", error)
                    return@addSnapshotListener
                }
                val users = snapshot?.documents?.mapNotNull { doc ->
                    try {
                        doc.toObject(UserAccount::class.java)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error parsing user doc ${doc.id}", e)
                        null
                    }
                } ?: emptyList()
                trySend(users)
            }
        awaitClose { listener.remove() }
    }

    suspend fun updateUserApproval(context: Context, usernameOrPhone: String, isApproved: Boolean, timestamp: Long?) {
        try {
            val db = getFirestore(context)
            val docId = usernameOrPhone.trim().lowercase().replace("+", "").replace(" ", "").replace("-", "")
            db.collection(COLLECTION_USERS).document(docId).set(
                mapOf(
                    "isApproved" to isApproved,
                    "paymentStatus" to if (isApproved) "Approved" else "Pending Approval",
                    "approvalTimestamp" to timestamp
                ),
                SetOptions.merge()
            ).await()
            Log.d(TAG, "User approval updated in cloud: $docId")
        } catch (e: Exception) {
            Log.e(TAG, "Error updating user approval in cloud", e)
        }
    }

    suspend fun deleteUserFromCloud(context: Context, usernameOrPhone: String) {
        try {
            val db = getFirestore(context)
            val docId = usernameOrPhone.trim().lowercase().replace("+", "").replace(" ", "").replace("-", "")
            if (docId.isBlank()) return
            db.collection(COLLECTION_USERS).document(docId).delete().await()
            Log.d(TAG, "User deleted from cloud: $docId")
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting user from cloud", e)
        }
    }
}
