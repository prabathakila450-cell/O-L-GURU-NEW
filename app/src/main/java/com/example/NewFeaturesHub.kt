package com.example

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InteractiveFlashcardsScreen(onBack: () -> Unit) {
  var selectedGrade by remember { mutableStateOf("11") }
  var selectedSubject by remember { mutableStateOf("විද්‍යාව") }
  var selectedSetNumber by remember { mutableIntStateOf(1) }
  var currentCardIndex by remember { mutableIntStateOf(0) }
  var isFlipped by remember { mutableStateOf(false) }

  val subjects = remember { ComprehensiveFlashcardRepository.availableSubjects }
  val grades = remember { ComprehensiveFlashcardRepository.availableGrades }

  val cards = remember(selectedGrade, selectedSubject, selectedSetNumber) {
    ComprehensiveFlashcardRepository.getFlashcards(selectedGrade, selectedSubject, selectedSetNumber)
  }

  val activeCard = cards.getOrNull(currentCardIndex) ?: cards.firstOrNull()

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Column {
            Text(
              text = "💡 මතක කාඩ්පත් (Flashcards)",
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = Color.White
            )
            Text(
              text = "$selectedGrade ශ්‍රේණිය • $selectedSubject • කාණ්ඩය $selectedSetNumber",
              fontSize = 11.sp,
              color = Color(0xFF93C5FD)
            )
          }
        },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color(0xFF1E3A8A)
        )
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .background(Color(0xFFF8FAFC))
        .verticalScroll(rememberScrollState())
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // 1. Grade Selector
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        grades.forEach { g ->
          val isSel = selectedGrade == g
          Button(
            onClick = {
              selectedGrade = g
              currentCardIndex = 0
              isFlipped = false
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = if (isSel) Color(0xFF2563EB) else Color.White,
              contentColor = if (isSel) Color.White else Color(0xFF334155)
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, if (isSel) Color(0xFF2563EB) else Color(0xFFCBD5E1)),
            modifier = Modifier.weight(1f).height(38.dp)
          ) {
            Text("$g ශ්‍රේණිය", fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal, fontSize = 12.sp)
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // 2. Subject Selector Row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        subjects.forEach { subj ->
          val isSel = selectedSubject == subj
          FilterChip(
            selected = isSel,
            onClick = {
              selectedSubject = subj
              selectedSetNumber = 1
              currentCardIndex = 0
              isFlipped = false
            },
            label = { Text(subj, fontSize = 11.5.sp) },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = Color(0xFF1E3A8A),
              selectedLabelColor = Color.White
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // 3. Set Selector Row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        (1..20).forEach { setNum ->
          val isSel = selectedSetNumber == setNum
          Surface(
            onClick = {
              selectedSetNumber = setNum
              currentCardIndex = 0
              isFlipped = false
            },
            shape = RoundedCornerShape(8.dp),
            color = if (isSel) Color(0xFF047857) else Color.White,
            border = BorderStroke(1.dp, if (isSel) Color(0xFF047857) else Color(0xFFCBD5E1)),
            modifier = Modifier.padding(vertical = 2.dp)
          ) {
            Text(
              text = "කාණ්ඩය $setNum",
              color = if (isSel) Color.White else Color(0xFF334155),
              fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Card Header Count
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "කාඩ්පත ${currentCardIndex + 1} / ${cards.size.coerceAtLeast(1)}",
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp,
          color = Color(0xFF1E293B)
        )
        Text(
          text = if (isFlipped) "🔄 පිළිතුර (Answer Side)" else "❓ ප්‍රශ්නය (Question Side)",
          fontSize = 11.sp,
          color = if (isFlipped) Color(0xFF047857) else Color(0xFF2563EB),
          fontWeight = FontWeight.SemiBold
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      // 4. MAIN FLASHCARD
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isFlipped) Color(0xFFF0FDF4) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.5.dp, if (isFlipped) Color(0xFF86EFAC) else Color(0xFFBFDBFE)),
        modifier = Modifier
          .fillMaxWidth()
          .height(260.dp)
          .clip(RoundedCornerShape(18.dp))
          .clickable { isFlipped = !isFlipped }
      ) {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
          verticalArrangement = Arrangement.SpaceBetween,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Top tag
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = if (isFlipped) Color(0xFFDCFCE7) else Color(0xFFEFF6FF)
            ) {
              Text(
                text = activeCard?.unitCategory?.ifBlank { selectedSubject } ?: selectedSubject,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (isFlipped) Color(0xFF15803D) else Color(0xFF1D4ED8),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
            Text(
              text = "#${activeCard?.globalIndex ?: (currentCardIndex + 1)}",
              fontSize = 11.sp,
              color = Color(0xFF94A3B8)
            )
          }

          // Content Text
          Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
          ) {
            if (!isFlipped) {
              Text(
                text = activeCard?.questionFront ?: "ප්‍රශ්න සකස් වෙමින් පවතී...",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFF0F172A),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
              )
            } else {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                  text = activeCard?.answerBack ?: "පිළිතුර සකස් වෙමින් පවතී...",
                  fontWeight = FontWeight.Bold,
                  fontSize = 15.sp,
                  color = Color(0xFF065F46),
                  textAlign = TextAlign.Center,
                  lineHeight = 22.sp
                )
                if (!activeCard?.explanationTip.isNullOrBlank()) {
                  Spacer(modifier = Modifier.height(8.dp))
                  Text(
                    text = "💡 ${activeCard?.explanationTip}",
                    fontSize = 11.sp,
                    color = Color(0xFF475569),
                    textAlign = TextAlign.Center
                  )
                }
              }
            }
          }

          // Bottom Prompt
          Text(
            text = "ස්පර්ශ කර කාඩ්පත පෙරළන්න (Tap to Flip) 👆",
            fontSize = 10.sp,
            color = Color(0xFF64748B)
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // 5. Controls
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Button(
          onClick = {
            if (currentCardIndex > 0) {
              currentCardIndex--
              isFlipped = false
            }
          },
          enabled = currentCardIndex > 0,
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
          shape = RoundedCornerShape(10.dp)
        ) {
          Text("⬅️ පෙර", fontSize = 12.sp)
        }

        Button(
          onClick = { isFlipped = !isFlipped },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB)),
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(Icons.Default.SwapHoriz, contentDescription = "Flip", modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text(if (isFlipped) "ප්‍රශ්නය බලන්න" else "පිළිතුර බලන්න", fontSize = 12.sp)
        }

        Button(
          onClick = {
            if (currentCardIndex < cards.size - 1) {
              currentCardIndex++
              isFlipped = false
            }
          },
          enabled = currentCardIndex < cards.size - 1,
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF047857)),
          shape = RoundedCornerShape(10.dp)
        ) {
          Text("මීළඟ ➡️", fontSize = 12.sp)
        }
      }
    }
  }
}

@Composable
fun BookmarksAndFavoritesScreen(
  onBack: () -> Unit,
  onOpenPdf: (url: String, title: String) -> Unit,
  savedBookmarks: List<BookmarkItem>
) {
  BookmarksAndFavoritesScreenContent(
    onBack = onBack,
    onOpenPdf = onOpenPdf,
    savedBookmarks = savedBookmarks
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookmarksAndFavoritesScreenContent(
  onBack: () -> Unit,
  onOpenPdf: (url: String, title: String) -> Unit,
  savedBookmarks: List<BookmarkItem>
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = "🔖 සුරැකි සටහන් (Bookmarks)",
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White
            )
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color(0xFF0F172A)
        )
      )
    }
  ) { padding ->
    if (savedBookmarks.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(padding),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "තවමත් සුරැකි සටහන් කිසිවක් නැත.",
          color = Color(0xFF64748B),
          fontSize = 14.sp
        )
      }
    } else {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(padding)
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        items(savedBookmarks.size) { idx ->
          val item = savedBookmarks[idx]
          Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
              containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = item.title,
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp,
                  color = Color(0xFF0F172A)
                )
                Text(
                  text = "${item.subject} • ${item.grade} ශ්‍රේණිය • ${item.type}",
                  fontSize = 11.sp,
                  color = Color(0xFF64748B)
                )
              }
              if (!item.pdfUri.isNullOrBlank()) {
                Button(
                  onClick = { onOpenPdf(item.pdfUri, item.title) },
                  colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2563EB)
                  ),
                  shape = RoundedCornerShape(8.dp)
                ) {
                  Text("කියවන්න", fontSize = 11.sp)
                }
              }
            }
          }
        }
      }
    }
  }
}
