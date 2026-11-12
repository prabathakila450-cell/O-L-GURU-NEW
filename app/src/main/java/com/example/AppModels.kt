package com.example

import androidx.compose.ui.graphics.Color
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserAccount(
  val id: String = "",
  val fullName: String = "",
  val usernameOrPhone: String = "",
  val password: String = "",
  var isApproved: Boolean = false,
  val registerDate: String = "2026-08-11",
  var slipImageUri: String? = null,
  var paymentStatus: String = "Pending Approval",
  var requestedGradePackage: String = "10 සහ 11 ශ්‍රේණි (O/L Combo Pack)",
  var approvedGrades: List<String> = listOf("10", "11", "10 ශ්‍රේණිය", "11 ශ්‍රේණිය"),
  var boundDeviceId: String? = null,
  var boundDeviceName: String? = null,
  var boundDate: String? = null,
  var approvalTimestamp: Long? = null,
  var requestDate: String? = null,
  var requestTimestamp: Long? = null
)

data class SubjectItem(
  val id: String = "",
  val name: String = "",
  val nameSinhala: String = "",
  val chaptersCount: Int = 0,
  val color: Color = Color(0xFF2563EB),
  val icon: androidx.compose.ui.graphics.vector.ImageVector = androidx.compose.material.icons.Icons.Default.MenuBook
)

data class ShortNoteItem(
  val id: String = "",
  val subject: String = "",
  val title: String = "",
  val topicSinhala: String = "",
  val readTime: String = "5 mins",
  val isPopular: Boolean = false,
  val pdfUri: String? = null,
  val fileName: String? = null,
  val isPasswordProtected: Boolean = false,
  val password: String? = "1234"
)

data class QuestionPaperItem(
  val id: String = "",
  val subject: String = "",
  val titleSinhala: String = "",
  val year: String = "2023",
  val term: String = "1",
  val marks: String = "100",
  val pdfUri: String? = null,
  val fileName: String? = null,
  val isPasswordProtected: Boolean = false,
  val password: String? = "1234"
)

data class VideoLessonItem(
  val id: String = "",
  val subject: String = "",
  val titleSinhala: String = "",
  val duration: String = "20 mins",
  val tutorName: String = "",
  val viewsCount: String = "1K",
  val isHd: Boolean = true,
  val videoUri: String? = null
)

data class BookmarkItem(
  val id: String = "",
  val title: String = "",
  val subject: String = "",
  val grade: String = "10",
  val type: String = "Note",
  val pdfUri: String? = null
)

data class GlossaryTermItem(
  val id: String = "",
  val subject: String = "",
  val englishTerm: String = "",
  val sinhalaTerm: String = "",
  val tamilTerm: String = "",
  val definitionSinhala: String = "",
  val examTip: String = ""
)

data class MockExamQuestion(
  val id: Any = 0,
  val questionText: String = "",
  val options: List<String> = emptyList(),
  val correctOption: Int = 0,
  val correctOptionIndex: Int = correctOption,
  val explanation: String = "",
  val explanationSinhala: String = explanation,
  val topicName: String = "General",
  val topicCategory: String = topicName,
  val subject: String = "විද්‍යාව",
  val grade: String = "10"
)

data class MockExamPaper(
  val id: String = "",
  val titleSinhala: String = "",
  val subject: String = "",
  val grade: String = "10",
  val term: Int = 1,
  val paperNumber: Int = 1,
  val durationMinutes: Int = 60,
  val totalQuestions: Int = 60,
  val syllabusCoverageSummary: String = "",
  val questions: List<MockExamQuestion> = emptyList(),
  val pdfUri: String? = null
)

data class PastPaperQuestion(
  val id: Any = 0,
  val questionText: String = "",
  val options: List<String> = emptyList(),
  val correctOptionIndex: Int = 0,
  val correctOption: Int = correctOptionIndex,
  val explanationSinhala: String = "",
  val explanation: String = explanationSinhala,
  val topicCategory: String = "General",
  val topicName: String = topicCategory,
  val year: String = "2023",
  val subject: String = "විද්‍යාව",
  val grade: String = "11"
)

data class DialogueLine(
  val speaker: String = "",
  val textEnglish: String = "",
  val textSinhala: String = "",
  val audioUri: String? = null
) {
  val sinhalaText: String get() = textSinhala
  val englishText: String get() = textEnglish
}

data class DialogueConversation(
  val id: String = "",
  val title: String = "",
  val topicSinhala: String = title,
  val difficulty: String = "Medium",
  val situation: String = "",
  val lines: List<DialogueLine> = emptyList(),
  val dialogueLines: List<DialogueLine> = lines
)

data class SentencePuzzleItem(
  val id: String = "",
  val category: String = "",
  val targetSentenceEnglish: String = "",
  val sinhalaMeaning: String = "",
  val jumbledWords: List<String> = emptyList(),
  val explanationSinhala: String = ""
) {
  val scrambledWords: List<String> get() = jumbledWords
  val correctSentence: String get() = targetSentenceEnglish
  val hint: String get() = sinhalaMeaning
}

enum class EnglishGradeLevel(val label: String) {
  GRADE_10("10 ශ්‍රේණිය"),
  GRADE_11("11 ශ්‍රේණිය"),
  ALL("10 සහ 11 ශ්‍රේණි")
}

enum class EnglishSkillCategory(
  val displayName: String,
  val iconEmoji: String,
  val primaryColor: Color,
  val bgLightColor: Color
) {
  CONDITIONALS("Conditionals (තර්ක වාක්‍ය)", "🔀", Color(0xFF2563EB), Color(0xFFEFF6FF)),
  REPORTED_SPEECH("Reported Speech (වක්‍ර ප්‍රකාශ)", "💬", Color(0xFF7C3AED), Color(0xFFF5F3FF)),
  PASSIVE_VOICE("Passive Voice (කර්මකාරක)", "⚙️", Color(0xFF059669), Color(0xFFECFDF5)),
  RELATIVE_CLAUSES("Relative Clauses (සම්බන්ධක)", "🔗", Color(0xFFD97706), Color(0xFFFFFBEB)),
  PREPOSITIONS("Prepositions (නිපාත පද)", "📍", Color(0xFFDC2626), Color(0xFFFEF2F2)),
  SUBJECT_VERB_AGREEMENT("Subject-Verb Agreement", "⚖️", Color(0xFF0284C7), Color(0xFFF0F9FF)),
  ARTICLE_DETERMINERS("Articles & Determiners", "🔤", Color(0xFF4F46E5), Color(0xFFEEF2FF)),
  TENSES_TEMPLATES("Tenses & Time Expressions", "⏳", Color(0xFF059669), Color(0xFFECFDF5)),
  TENSES("Tenses & Time Clauses", "⏳", Color(0xFF059669), Color(0xFFECFDF5)),
  VOCABULARY_SYNONYMS("Vocabulary & Synonyms", "📚", Color(0xFFB45309), Color(0xFFFFFBEB)),
  VOCABULARY("Vocabulary & Synonyms", "📚", Color(0xFFB45309), Color(0xFFFFFBEB)),
  PRONOUN_REF("Pronoun References", "👥", Color(0xFF6D28D9), Color(0xFFF5F3FF)),
  CONJUNCTIONS("Conjunctions & Connectors", "🪢", Color(0xFF0369A1), Color(0xFFF0F9FF)),
  MODAL_VERBS("Modal Verbs (ආකෘතිමය ක්‍රියා)", "🔑", Color(0xFFBE185D), Color(0xFFFDF2F8)),
  MODALS("Modal Auxiliaries", "🔑", Color(0xFFBE185D), Color(0xFFFDF2F8)),
  QUESTION_TAGS("Question Tags", "❓", Color(0xFF047857), Color(0xFFECFDF5)),
  ERROR_CORRECTION("Error Correction", "✏️", Color(0xFFB91C1C), Color(0xFFFEF2F2)),
  READING_COMPREHENSION("Comprehension Tips", "📖", Color(0xFF1E40AF), Color(0xFFEFF6FF))
}

data class EnglishShortNoteQuestionItem(
  val id: String = "",
  val number: Int = 1,
  val gradeLevel: EnglishGradeLevel = EnglishGradeLevel.GRADE_10,
  val category: EnglishSkillCategory = EnglishSkillCategory.CONDITIONALS,
  val topicTitle: String = "",
  val questionPrompt: String = "",
  val questionSinhalaGuidance: String = "",
  val options: List<String> = emptyList(),
  val correctOptionIndex: Int = 0,
  val explanationSinhala: String = "",
  val grammarRuleOrFormula: String = "",
  val examTip: String = ""
)

data class SpokenSentencePractice(
  val id: String = "",
  val englishText: String = "",
  val phoneticGuide: String = "",
  val sinhalaMeaning: String = "",
  val difficulty: String = "Easy"
)

data class EnglishGrammarRuleSummary(
  val id: String = "",
  val title: String = "",
  val category: EnglishSkillCategory = EnglishSkillCategory.CONDITIONALS,
  val gradeLevel: EnglishGradeLevel = EnglishGradeLevel.ALL,
  val formula: String = "",
  val explanationSinhala: String = "",
  val examples: List<String> = emptyList(),
  val commonMistake: String = "",
  val badgeColor: Color = Color(0xFF2563EB)
)

enum class EyeCareThemeMode {
  LIGHT,
  NORMAL,
  DEFAULT,
  SEPIA,
  DARK,
  NIGHT,
  OLED,
  BLUE_LIGHT,
  BLUE_LIGHT_SHIELD,
  AMBER
}
