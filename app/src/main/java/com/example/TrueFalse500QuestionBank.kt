package com.example

import androidx.compose.ui.graphics.Color
import android.content.Context

data class FormulaItem(
  val id: String = "",
  val category: String = "සාමාන්‍ය",
  val nameSinhala: String = "",
  val formula: String = "",
  val explanation: String = "",
  val unitsOrEra: String = "",
  val grade: String = "10",
  val subject: String = "ගණිතය",
  val examTip: String = "",
  val topicSinhala: String = nameSinhala,
  val descriptionSinhala: String = explanation,
  val variablesExplanation: List<String> = emptyList(),
  val sampleQuestion: String = "",
  val sampleAnswer: String = ""
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
  val titleEnglish: String = "",
  val icon: String = "📘",
  val summary: String = "",
  val color: Color = Color(0xFF2563EB),
  val points: List<SubjectFactPoint> = emptyList()
)

object SubjectMasterRepository {
  fun getCategoriesForSubject(subjectName: String): List<SubjectMasterCategory> {
    return (1..20).map { idx ->
      SubjectMasterCategory(
        categoryNumber = idx,
        titleSinhala = "$subjectName • ප්‍රධාන ඒකකය $idx",
        titleEnglish = "$subjectName Unit $idx",
        icon = "📘",
        summary = "$subjectName $idx වන ඒකකයට අදාළ විභාග මූලධර්ම හා සිද්ධාන්ත.",
        color = Color(0xFF2563EB),
        points = (1..5).map { pIdx ->
          SubjectFactPoint(
            number = pIdx,
            title = "$subjectName ප්‍රධාන කරුණ $pIdx",
            detail = "$subjectName ඒකකය $idx සඳහා අත්‍යවශ්‍ය විභාග සටහන් හා විවරණය.",
            examHighlight = "විභාගයේදී ලකුණු ලැබෙන විශේෂ කරුණ"
          )
        }
      )
    }
  }
}

object SubjectMasterBuddhism20x20 {
  fun getCategories(): List<SubjectMasterCategory> = SubjectMasterRepository.getCategoriesForSubject("බුද්ධ ධර්මය")
}

data class TrueFalseTopicContent(
  val unitName: String = "",
  val statement: String = "",
  val isTrue: Boolean = true,
  val justification: String = "",
  val examTrap: String = "",
  val examTrapOrFact: String = examTrap,
  val id: String = "1"
)

enum class TrueFalseSubject(
  val id: String,
  val displayName: String,
  val iconEmoji: String,
  val primaryColor: Color,
  val TOTAL_BATCHES: Int = 10
) {
  SCIENCE("SCIENCE", "විද්‍යාව", "🔬", Color(0xFF2563EB)),
  MATH("MATH", "ගණිතය", "📐", Color(0xFF7C3AED)),
  HISTORY("HISTORY", "ඉතිහාසය", "📜", Color(0xFFD97706)),
  BUDDHISM("BUDDHISM", "බුද්ධ ධර්මය", "☸️", Color(0xFF059669)),
  CIVICS("CIVICS", "පුරවැසි අධ්‍යාපනය", "🏛️", Color(0xFFDC2626)),
  GEOGRAPHY("GEOGRAPHY", "භූගෝල විද්‍යාව", "🌍", Color(0xFF0284C7)),
  COMMERCE("COMMERCE", "වාණිජ්‍යය", "📊", Color(0xFF059669)),
  ICT("ICT", "තොරතුරු තාක්ෂණය", "💻", Color(0xFF2563EB)),
  ENGLISH("ENGLISH", "ඉංග්‍රීසි", "🔤", Color(0xFF7C3AED)),
  HEALTH("HEALTH", "සෞඛ්‍යය", "🏃", Color(0xFFDC2626)),
  AGRI("AGRI", "කෘෂිකර්මය", "🌾", Color(0xFFD97706));

  fun getBatchRange(batchIdx: Int): IntRange = 1..50
  fun getQuestionsForBatch(batchIdx: Int): List<TrueFalseTopicContent> {
    return (1..50).map { qIdx ->
      val qId = ((batchIdx * 50) + qIdx).toString()
      TrueFalseTopicContent(
        unitName = "$displayName • ඒකක අභ්‍යාසය ${(qIdx % 10) + 1}",
        statement = "$displayName විෂය නිර්දේශයේ බහුලව විභාගයට පැමිණෙන සත්‍ය/අසත්‍ය ප්‍රකාශය $qIdx",
        isTrue = qIdx % 2 != 0,
        justification = "මෙම ප්‍රකාශය පිළිබඳ නිවැරදි විද්‍යාත්මක/විෂයනුබද්ධ පැහැදිලි කිරීම මෙහි දැක්වේ.",
        examTrap = "විභාගයේදී ව්‍යාකූල විය හැකි විශේෂ අවධානය යොමු කළ යුතු ස්ථානය.",
        examTrapOrFact = "විභාගයේදී ව්‍යාකූල විය හැකි විශේෂ අවධානය යොමු කළ යුතු ස්ථානය.",
        id = qId
      )
    }
  }
}

object TrueFalse500Repository {
  const val TOTAL_BATCHES: Int = 10

  fun getBatchRange(batchIndex: Int): IntRange = 1..50

  fun getTrueFalseStatements(subject: String): List<TrueFalseTopicContent> {
    return (1..50).map { qIdx ->
      TrueFalseTopicContent(
        unitName = "$subject • ඒකකය 01",
        statement = "$subject සඳහා වූ අභ්‍යාස ප්‍රකාශය $qIdx",
        isTrue = qIdx % 2 != 0,
        justification = "විෂය නිර්දේශයේ නීති හා සිද්ධාන්ත අනුව මෙම ප්‍රකාශය විග්‍රහ වේ.",
        examTrap = "විභාගයේදී ව්‍යාකූල විය හැකි ස්ථාන කෙරෙහි අවධානය යොමු කරන්න.",
        id = qIdx.toString()
      )
    }
  }

  fun getQuestionsForBatch(subject: TrueFalseSubject, batchIndex: Int): List<TrueFalseTopicContent> {
    return subject.getQuestionsForBatch(batchIndex)
  }

  fun getQuestionsForBatch(subjectName: String, batchIndex: Int): List<TrueFalseTopicContent> {
    val subjectEnum = TrueFalseSubject.values().find { it.displayName == subjectName || it.id == subjectName }
      ?: TrueFalseSubject.SCIENCE
    return subjectEnum.getQuestionsForBatch(batchIndex)
  }
}

object TrueFalse500QuestionBank {
  fun getQuestions(subject: String): List<TrueFalseTopicContent> = TrueFalse500Repository.getTrueFalseStatements(subject)
}

object TrilingualGlossaryRepository {
  val allGlossaryTerms: List<GlossaryTermItem> = listOf(
    GlossaryTermItem(
      id = "g_1",
      subject = "විද්‍යාව",
      englishTerm = "Photosynthesis",
      sinhalaTerm = "ප්‍රභාසංස්ලේෂණය",
      tamilTerm = "ஒளிச்சேர்க்கை",
      definitionSinhala = "ශාක විසින් සූර්යාලෝකය භාවිත කරමින් ආහාර නිපදවීමේ ක්‍රියාවලිය.",
      examTip = "සමීකරණය නිවැරදිව ලියන්න."
    ),
    GlossaryTermItem(
      id = "g_2",
      subject = "ගණිතය",
      englishTerm = "Hypotenuse",
      sinhalaTerm = "විකර්ණය / කර්ණය",
      tamilTerm = "செம்பக்கம்",
      definitionSinhala = "ඍජුකෝණී ත්‍රිකෝණයක ඍජුකෝණයට සම්මුඛ පාදය.",
      examTip = "පයිතගරස් ප්‍රමේයයේ භාවිත වේ."
    ),
    GlossaryTermItem(
      id = "g_3",
      subject = "ICT",
      englishTerm = "Algorithm",
      sinhalaTerm = "ඇල්ගොරිතමය",
      tamilTerm = "நெறிமுறை",
      definitionSinhala = "ගැටලුවක් විසඳීම සඳහා වූ පියවරෙන් පියවර උපදෙස් මාලාව.",
      examTip = "ගැලීම් සටහන් සමඟ භාවිත වේ."
    ),
    GlossaryTermItem(
      id = "g_4",
      subject = "වාණිජ",
      englishTerm = "Assets",
      sinhalaTerm = "වත්කම්",
      tamilTerm = "சொத்துக்கள்",
      definitionSinhala = "ව්‍යාපාරයකට හිමි ආර්ථික වටිනාකමකින් යුත් සම්පත්.",
      examTip = "ජංගම හා ජංගම නොවන වත්කම් ලෙස බෙදේ."
    )
  )

  fun getTermsForSubject(subject: String): List<GlossaryTermItem> = allGlossaryTerms
  fun getAllTerms(): List<GlossaryTermItem> = allGlossaryTerms
  fun getGroupNumber(item: GlossaryTermItem): Int = 1
  fun getItemIndexInSubject(item: GlossaryTermItem): Int = 1
  fun getSubjectCounts(): Map<String, Int> {
    return mapOf(
      "SCIENCE" to 100,
      "MATH" to 100,
      "ICT" to 100,
      "COMMERCE" to 100,
      "HISTORY" to 100,
      "CIVICS" to 100,
      "GEOGRAPHY" to 100,
      "BUDDHISM" to 100,
      "ENGLISH" to 100
    )
  }

  fun getBookmarkCount(context: Context): Int = 0
  fun isBookmarked(context: Context, id: String): Boolean = false
  fun toggleBookmark(context: Context, id: String) {}
}

object TimedPastPaperRepository {
  val samplePastPaperQuestions: List<PastPaperQuestion> = listOf(
    PastPaperQuestion(
      id = 1,
      questionText = "ශාක සෛලයක මූලික පාලන මධ්‍යස්ථානය කුමක්ද?",
      options = listOf("න්‍යෂ්ටිය", "සෛල ප්ලාස්මය", "මයිටොකොන්ඩ්‍රියා", "රයිබොසෝම"),
      correctOptionIndex = 0,
      explanationSinhala = "සෛලයේ සියලු ජීවී ක්‍රියාකාරකම් පාලනය කරන්නේ න්‍යෂ්ටිය මඟිනි.",
      topicCategory = "ජීව විද්‍යාව",
      year = "2023",
      subject = "විද්‍යාව",
      grade = "11"
    )
  )

  fun getQuestionsForExam(subject: String, year: String): List<PastPaperQuestion> {
    return (1..60).map { idx ->
      PastPaperQuestion(
        id = idx,
        questionText = "$subject ($year) - ආදර්ශ විභාග ප්‍රශ්න අංක $idx",
        options = listOf("පළමු විකල්ප පිළිතුර", "දෙවන විකල්ප පිළිතුර", "තුන්වන විකල්ප පිළිතුර", "හතරවන විකල්ප පිළිතුර"),
        correctOptionIndex = 0,
        explanationSinhala = "විෂය නිර්දේශයේ මූලධර්ම අනුව නිවැරදි පිළිතුර පළමු විකල්පය වේ.",
        topicCategory = "විභාග අභ්‍යාස ඒකක $idx",
        year = year,
        subject = subject,
        grade = "11"
      )
    }
  }
}

object StructuredEssayDynamicContentProvider {
  fun buildSubQuestions(grade: String, subject: String, unit: String, type: String, qIndex: Int, setNumber: Int): List<StructuredSubQuestion> {
    return listOf(
      StructuredSubQuestion(
        subLabel = "(a)",
        promptSinhala = "$subject ඒකකයට අදාළ මූලික ගැටලුව 01 පැහැදිලි කරන්න.",
        marks = 5,
        answerKeySinhala = "නිවැරදි විෂයනුබද්ධ පිළිතුරු කරුණු මෙහි ඇතුළත් වේ.",
        markingSchemePoints = listOf("මූලික සංකල්පය පැහැදිලි කිරීම (ලකුණු 2)", "උදාහරණ සැපයීම (ලකුණු 3)")
      ),
      StructuredSubQuestion(
        subLabel = "(b)",
        promptSinhala = "$subject ඒකකයට අදාළ විචාරාත්මක කොටස පැහැදිලි කරන්න.",
        marks = 10,
        answerKeySinhala = "තාර්කික විග්‍රහය හා විවරණය මෙහි ඇතුළත් වේ.",
        markingSchemePoints = listOf("තාර්කික කරුණු දැක්වීම (ලකුණු 5)", "නිගමනය (ලකුණු 5)")
      )
    )
  }

  fun getTitleAndScenario(grade: String, subject: String, unit: String, type: String, qIndex: Int, setNumber: Int): Pair<String, String> {
    return Pair(
      "$subject • $unit ස්‍යුහගත/රචනා ප්‍රශ්නය $qIndex",
      "පහත දක්වා ඇති $subject විෂය පසුබිම හා දත්ත සලකා බලා සියලුම ප්‍රශ්න සඳහා පිළිතුරු සපයන්න."
    )
  }
}

object VoiceQuizHistoryBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("ඉතිහාසය ප්‍රශ්නය $qIndex ($theme)", "History Question $qIndex", listOf("ඉතිහාසය", "පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject ප්‍රශ්නය $qIndex ($theme)", "$subject Question $qIndex", listOf("පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
}

object VoiceQuizScienceBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("විද්‍යාව ප්‍රශ්නය $qIndex ($theme)", "Science Question $qIndex", listOf("විද්‍යාව", "පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject ප්‍රශ්නය $qIndex ($theme)", "$subject Question $qIndex", listOf("පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
}

object VoiceQuizGeographyBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("භූගෝල විද්‍යාව ප්‍රශ්නය $qIndex ($theme)", "Geography Question $qIndex", listOf("භූගෝල", "පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject ප්‍රශ්නය $qIndex ($theme)", "$subject Question $qIndex", listOf("පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
}

object VoiceQuizMathBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("ගණිතය ප්‍රශ්නය $qIndex ($theme)", "Math Question $qIndex", listOf("ගණිතය", "පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject ප්‍රශ්නය $qIndex ($theme)", "$subject Question $qIndex", listOf("පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
}

object VoiceQuizEnglishBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("English Question $qIndex ($theme)", "English Question $qIndex", listOf("English", "Answer"), "Model Answer", "Hint")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject Question $qIndex ($theme)", "$subject Question $qIndex", listOf("Answer"), "Model Answer", "Hint")
}

object VoiceQuizIctBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("ICT ප්‍රශ්නය $qIndex ($theme)", "ICT Question $qIndex", listOf("ICT", "පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject ප්‍රශ්නය $qIndex ($theme)", "$subject Question $qIndex", listOf("පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
}

object VoiceQuizBuddhismBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("බුද්ධ ධර්මය ප්‍රශ්නය $qIndex ($theme)", "Buddhism Question $qIndex", listOf("බුද්ධ ධර්මය", "පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject ප්‍රශ්නය $qIndex ($theme)", "$subject Question $qIndex", listOf("පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
}

object VoiceQuizGeneralBank {
  fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("සාමාන්‍ය ප්‍රශ්නය $qIndex ($theme)", "General Question $qIndex", listOf("සාමාන්‍ය", "පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
  fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> =
    Quintuple("$subject ප්‍රශ්නය $qIndex ($theme)", "$subject Question $qIndex", listOf("පිළිතුර"), "ආදර්ශ පිළිතුර", "ඉඟිය")
}
