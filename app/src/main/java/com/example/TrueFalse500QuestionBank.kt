package com.example

import androidx.compose.ui.graphics.Color

enum class TrueFalseSubject(
  val id: String,
  val displayName: String,
  val iconEmoji: String,
  val primaryColor: Color
) {
  SCIENCE("sci", "විද්‍යාව (Science)", "🧬", Color(0xFF10B981)),
  MATHEMATICS("maths", "ගණිතය (Mathematics)", "📐", Color(0xFF3B82F6)),
  HISTORY("hist", "ඉතිහාසය (History)", "🏛️", Color(0xFFF59E0B)),
  SINHALA("sin", "සිංහල (Sinhala)", "📖", Color(0xFF8B5CF6)),
  BUDDHISM("bud", "බුද්ධ ධර්මය (Buddhism)", "☸️", Color(0xFFEC4899)),
  ENGLISH("eng", "ඉංග්‍රීසි (English)", "🔤", Color(0xFF06B6D4)),
  ICT("ict", "තොරතුරු තාක්ෂණය (ICT)", "💻", Color(0xFF6366F1)),
  GEOGRAPHY("geo", "භූගෝල විද්‍යාව (Geography)", "🌍", Color(0xFF14B8A6)),
  CIVICS("civ", "පුරවැසි අධ්‍යාපනය (Civics)", "⚖️", Color(0xFFE11D48)),
  COMMERCE("comm", "ව්‍යාපාර හා ගිණුම්කරණය", "📊", Color(0xFFD97706)),
  HEALTH("health", "සෞඛ්‍ය හා ශාරීරික අධ්‍යාපනය", "🏃", Color(0xFF10B981)),
  AGRI("agri", "කෘෂිකර්ම විද්‍යාව", "🌾", Color(0xFF059669)),
  ART("art", "චිත්‍ර කලාව", "🎨", Color(0xFFF43F5E)),
  MUSIC("music", "සංගීතය", "🎸", Color(0xFF7C3AED)),
  DANCING("dance", "නර්තනය", "💃", Color(0xFFEC4899));

  companion object {
    fun fromDisplayName(name: String): TrueFalseSubject? {
      return values().find {
        it.displayName == name || it.displayName.contains(name, ignoreCase = true) || name.contains(it.displayName, ignoreCase = true)
      }
    }
  }
}

data class TrueFalseQuestionItem(
  val id: String = "",
  val number: Int = 1,
  val grade: Int = 10,
  val subject: TrueFalseSubject = TrueFalseSubject.SCIENCE,
  val unitName: String = "",
  val statement: String = "",
  val isTrue: Boolean = true,
  val justification: String = "",
  val examTrapOrFact: String = ""
)

data class TrueFalseTopicContent(
  val unitName: String = "",
  val statement: String = "",
  val isTrue: Boolean = true,
  val justification: String = "",
  val examTrap: String = "",
  val extraInfo: String = ""
)

data class SubjectFactPoint(
  val number: Int = 1,
  val title: String = "",
  val detail: String = "",
  val examHighlight: String = ""
)

data class SubjectMasterCategory(
  val categoryNumber: Int = 1,
  val titleSinhala: String = "",
  val summary: String = "",
  val icon: String = "📚",
  val color: Color = Color(0xFF2563EB),
  val points: List<SubjectFactPoint> = emptyList()
)

object SubjectMasterRepository {
  fun getCategoriesForSubject(subjectName: String): List<SubjectMasterCategory> {
    return (1..20).map { catNum ->
      SubjectMasterCategory(
        categoryNumber = catNum,
        titleSinhala = "$subjectName - ඒකකය $catNum",
        summary = "$subjectName විෂය නිර්දේශයේ ප්‍රධාන පාඩම් ඒකකය $catNum",
        icon = "📖",
        color = Color(0xFF2563EB),
        points = (1..25).map { p ->
          SubjectFactPoint(
            number = p,
            title = "ඒකක $catNum - ප්‍රධාන කරුණ $p",
            detail = "$subjectName හි විභාග මූලධර්මය සහ සිද්ධාන්ත පැහැදිලි කිරීම $p",
            examHighlight = "විභාග ඉලක්කගත විශේෂ කරුණකි."
          )
        }
      )
    }
  }
}

object TrueFalse500Repository {
  const val TOTAL_QUESTIONS_PER_SUBJECT = 500
  const val BATCH_SIZE = 30
  const val TOTAL_BATCHES = 17

  private val MASK_30 = booleanArrayOf(
    true, false, true, true, false, false, true, false, true, false,
    false, true, true, false, false, true, false, true, true, false,
    true, false, false, true, true, false, false, true, false, true
  )

  private val MASK_20 = booleanArrayOf(
    true, false, true, false, false, true, true, false, true, false,
    false, true, true, false, false, true, true, false, false, true
  )

  fun isQuestionTrue(batchIndex: Int, topicIndex: Int): Boolean {
    return if (batchIndex < 16) {
      val offset = (batchIndex * 7 + 3) % 30
      MASK_30[(topicIndex + offset) % 30]
    } else {
      val offset = (batchIndex * 3 + 1) % 20
      MASK_20[(topicIndex + offset) % 20]
    }
  }

  fun getBatchRange(batchIndex: Int): IntRange {
    val start = batchIndex * BATCH_SIZE + 1
    val end = minOf((batchIndex + 1) * BATCH_SIZE, TOTAL_QUESTIONS_PER_SUBJECT)
    return start..end
  }

  fun getQuestionsForBatch(subjectName: String, batchIndex: Int): List<TrueFalseQuestionItem> {
    val grade = 10
    val tfSubject = TrueFalseSubject.fromDisplayName(subjectName) ?: TrueFalseSubject.SCIENCE
    return getQuestionsForBatch(grade, tfSubject, batchIndex)
  }

  fun getQuestionsForBatch(subject: TrueFalseSubject, batchIndex: Int): List<TrueFalseQuestionItem> {
    return getQuestionsForBatch(10, subject, batchIndex)
  }

  fun getQuestionsForBatch(grade: Int, subject: TrueFalseSubject, batchIndex: Int): List<TrueFalseQuestionItem> {
    val all = getAllQuestions(grade, subject)
    val range = getBatchRange(batchIndex)
    return all.subList((range.first - 1).coerceAtLeast(0), minOf(range.last, all.size))
  }

  private val cache = mutableMapOf<String, List<TrueFalseQuestionItem>>()

  fun getAllQuestions(grade: Int, subject: TrueFalseSubject): List<TrueFalseQuestionItem> {
    val key = "${grade}_${subject.id}"
    return cache.getOrPut(key) {
      generate500Questions(grade, subject)
    }
  }

  private fun generate500Questions(grade: Int, subject: TrueFalseSubject): List<TrueFalseQuestionItem> {
    val categories = getCategoriesForSubject(subject)
    val list = ArrayList<TrueFalseQuestionItem>(500)
    var count = 0
    categories.forEachIndexed { cIdx, cat ->
      cat.points.forEachIndexed { pIdx, pt ->
        count++
        val isTrue = isQuestionTrue(cIdx, pIdx)
        list.add(
          TrueFalseQuestionItem(
            id = "tf_${subject.id}_${count}",
            number = count,
            grade = grade,
            subject = subject,
            unitName = cat.titleSinhala,
            statement = if (isTrue) pt.detail else "විෂය කරුණ අනුව ${pt.title} හි දක්වා ඇති ප්‍රකාශය වැරදි සහගත වේ.",
            isTrue = isTrue,
            justification = pt.detail,
            examTrapOrFact = pt.examHighlight
          )
        )
      }
    }
    return list
  }

  fun getTopicsForSubject(subject: TrueFalseSubject): List<String> {
    val categories = getCategoriesForSubject(subject)
    return categories.map { it.titleSinhala }
  }

  fun getCategoriesForSubject(subject: TrueFalseSubject): List<SubjectMasterCategory> {
    return SubjectMasterRepository.getCategoriesForSubject(subject.displayName)
  }
}
