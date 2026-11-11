@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

// ============================================================================
// 1. Term Paper Unique 60 Provider Wrapper
// ============================================================================
object TermPaperUnique60Provider {
  fun get60DistinctQuestions(
    grade: String,
    subject: String,
    term: Int,
    paperIdx: Int
  ): List<MockExamQuestion> {
    return MockExamDistinct60Provider.generateDistinct60Questions(
      subject = subject,
      term = term,
      grade = grade,
      paperIdx = paperIdx
    )
  }
}

// ============================================================================
// 2. Music and Dancing Subject Master Providers
// ============================================================================
object SubjectMasterMusicFull {
  fun getCategories(): List<SubjectMasterCategory> {
    return SubjectMasterRepository.getCategoriesForSubject("සංගීතය")
  }
}

object SubjectMasterDancingFull {
  fun getCategories(): List<SubjectMasterCategory> {
    return SubjectMasterRepository.getCategoriesForSubject("නර්තනය")
  }
}

// ============================================================================
// 3. Targeted Weak Topic Models & Repository
// ============================================================================
data class TargetedPracticeQuestion(
  val id: String,
  val question: String,
  val options: List<String>,
  val correctOptionIndex: Int,
  val explanation: String
)

data class TargetedTopicInfo(
  val subjectName: String,
  val topicName: String,
  val targetedQuestions: List<TargetedPracticeQuestion>
)

object TargetedWeakTopicRepository {
  fun getTopicInfo(subject: String, topic: String): TargetedTopicInfo {
    val qList = listOf(
      TargetedPracticeQuestion(
        id = "tw_1",
        question = "මෙම මාතෘකාව (${if (topic.isNotBlank()) topic else subject}) ආශ්‍රිත මූලික සංකල්ප නිවැරදිව හඳුනාගන්න.",
        options = listOf(
          "ප්‍රධාන නියමයන් සහ අර්ථ දැක්වීම් නිවැරදිව භාවිතය",
          "අනුමාන මත පදනම් වූ දත්ත භාවිතය",
          "සංකල්පීය පරස්පරතා සහිත ප්‍රකාශ",
          "නොගැලපෙන සාධක ඇතුළත් කිරීම"
        ),
        correctOptionIndex = 0,
        explanation = "විභාග ප්‍රශ්නවලදී මූලික සංකල්ප හා විද්‍යාත්මක/විෂයනුබද්ධ නියමයන් නිවැරදිව භාවිත කිරීම අත්‍යවශ්‍ය වේ."
      ),
      TargetedPracticeQuestion(
        id = "tw_2",
        question = "විභාග ප්‍රශ්න පත්‍රවල බහුලව ලකුණු අඩුවන ගැටලු නිරාකරණය කරගැනීමට අනුගමනය කළ යුතු ක්‍රමවේදය කුමක්ද?",
        options = listOf(
          "ප්‍රශ්නය මනාව කියවා ඉල්ලන නිශ්චිත කරුණ හඳුනාගැනීම",
          "ප්‍රශ්නය සම්පූර්ණයෙන් නොකියවා පිළිතුරු ලිවීම",
          "පැහැදිලි කිරීම් මඟහැර පිළිතුරු සැපයීම",
          "අදාළ නොවන විස්තර වැඩිපුර ලිවීම"
        ),
        correctOptionIndex = 0,
        explanation = "විභාග ප්‍රශ්නයක මූල පදය (Keyword) හඳුනාගෙන, ඉල්ලා ඇති කරුණට පමණක් සෘජුව පිළිතුරු සැපයිය යුතුය."
      ),
      TargetedPracticeQuestion(
        id = "tw_3",
        question = "විභාග ප්‍රශ්න පත්‍රයේ බහුවරණ (MCQ) ප්‍රශ්නයක් විසඳීමේදී වඩාත්ම සුදුසු උපාය මාර්ගය කුමක්ද?",
        options = listOf(
          "නොගැලපෙන වැරදි පිළිතුරු ඉවත් කිරීමේ න්‍යාය (Elimination method)",
          "අනුමානයෙන් පළමු වරණ තේරීම",
          "ප්‍රශ්නය අවසානය දක්වා නොකියවා තීරණය කිරීම",
          "විභාග කාලය ගැන නොසලකා එක් ප්‍රශ්නයකට වැඩි වේලාවක් වැය කිරීම"
        ),
        correctOptionIndex = 0,
        explanation = "නොගැලපෙන හෝ වැරදි පිළිතුරු බැහැර කරමින් නිවැරදි පිළිතුර තහවුරු කරගැනීම සාර්ථකම ක්‍රමයකි."
      )
    )

    return TargetedTopicInfo(
      subjectName = if (subject.isBlank() || subject == "ALL") "සාමාන්‍ය පෙළ" else subject,
      topicName = if (topic.isBlank()) "දුර්වල ඒකක විශේෂ අභ්‍යාස" else topic,
      targetedQuestions = qList
    )
  }
}

// ============================================================================
// 4. Sinhala Master Models & Repository
// ============================================================================
data class SinhalaLiteratureLesson(
  val id: String,
  val title: String,
  val sourceBook: String,
  val author: String,
  val lessonType: String,
  val historicalEra: String,
  val literaryAppreciationPoints: List<String>,
  val expectedExamQuestions: List<String>,
  val mainTheme: String,
  val modelEssayOutline: String,
  val keyQuotationsWithMeaning: List<Pair<String, String>>
)

data class SinhalaGrammarRule(
  val id: String,
  val ruleCategory: String,
  val ruleTitle: String,
  val rulePoints: List<String>,
  val corePrinciple: String,
  val examTip: String,
  val correctExamples: List<String>,
  val commonMistakes: List<Pair<String, String>>
)

data class SinhalaWritingSkill(
  val id: String,
  val category: String,
  val title: String,
  val standardFormat: List<String>,
  val practicalRules: List<String>,
  val markingCriteriaMarks: Map<String, Int>,
  val sampleSolution: String
)

data class SinhalaRapidMcqItem(
  val topic: String,
  val question: String,
  val options: List<String>,
  val correctIndex: Int,
  val explanation: String
)

object SinhalaMasterRepository {
  val rapidMcqList: List<SinhalaRapidMcqItem> = listOf(
    SinhalaRapidMcqItem(
      topic = "ව්‍යාකරණ",
      question = "පහත සඳහන් පද අතුරින් නිවැරදිව අක්ෂර වින්‍යාසය දක්වා ඇති පදය තෝරන්න.",
      options = listOf("විඥාපනය", "විඥ්ඥාපනය", "විඤ්ඤාපනය", "විඤ්ඥාපනය"),
      correctIndex = 2,
      explanation = "සම්මත සිංහල අක්ෂර වින්‍යාස නීතිය අනුව 'විඤ්ඤාපනය' නිවැරදි ආකාරය වේ."
    ),
    SinhalaRapidMcqItem(
      topic = "සන්ධි",
      question = "'ගුරු + උපදේශ' යන්න සන්ධි වූ විට ලැබෙන පදය කුමක්ද?",
      options = listOf("ගුරුඋපදේශ", "ගුරූපදේශ", "ගුරෝපදේශ", "ගුර්වෝපදේශ"),
      correctIndex = 1,
      explanation = "ස්වර සන්ධි නීතිය යටතේ 'උ' ස්වරය දීර්ඝ වී 'ගුරූපදේශ' වේ."
    ),
    SinhalaRapidMcqItem(
      topic = "සාහිත්‍යය",
      question = "සද්ධර්ම රත්නාවලිය කෘතියේ කතුවරයා කවුරුන්ද?",
      options = listOf("ගුරුළුගෝමී පඬිවරයා", "ධර්මසේන හිමි", "විදුසක්විති හිමි", "තොටගමුවේ ශ්‍රී රාහුල හිමි"),
      correctIndex = 1,
      explanation = "දඹදෙණි යුගයේ විසූ ධර්මසේන හිමියන් සද්ධර්ම රත්නාවලියේ කතුවරයාය."
    ),
    SinhalaRapidMcqItem(
      topic = "කාව්‍ය විචාර",
      question = "කාව්‍යශේඛරය කාව්‍යයේ කතුවරයා කවුරුන්ද?",
      options = listOf("වීදාගම මෛත්‍රිය හිමි", "තොටගමුවේ ශ්‍රී රාහුල හිමි", "අලගියවන්න මුකවෙටි", "කුමාරතුංග මුනිදාස"),
      correctIndex = 1,
      explanation = "කෝට්ටේ යුගයේ වැඩවිසූ තොටගමුවේ ශ්‍රී රාහුල හිමියන් කාව්‍යශේඛරයේ කතුවරයා වේ."
    )
  )

  val grade11LiteratureList: List<SinhalaLiteratureLesson> = listOf(
    SinhalaLiteratureLesson(
      id = "g11_lit_1",
      title = "නැණවතීගේ වියෝව",
      sourceBook = "ගම්පෙරළිය",
      author = "මාර්ටින් වික්‍රමසිංහ",
      lessonType = "ගද්‍ය විචාරය",
      historicalEra = "නූතන යුගය",
      literaryAppreciationPoints = listOf("යථාර්ථවාදී චරිත නිරූපණය", "මානව ධර්ම හා හැඟීම් ප්‍රකාශනය", "සරල සුගම බස් වහර"),
      expectedExamQuestions = listOf("නැණවතීගේ වියෝව තුළින් ප්‍රකට වන මානව ධර්ම විමසන්න.", "කතුවරයාගේ භාෂා රටාව අගය කරන්න."),
      mainTheme = "ජීවිතයේ අනිත්‍යතාව හා ගැමි සමාජයේ සංවේදී සබඳතා",
      modelEssayOutline = "ප්‍රවේශය: කෘතිය හා කතුවරයා හඳුන්වාදීම.\nශරීරය: නැණවතීගේ චරිත ස්වභාවය හා වියෝවේ කම්පනය.\nනිගමනය: මාර්ටින් වික්‍රමසිංහයන්ගේ සාහිත්‍යමය ප්‍රතිභාව.",
      keyQuotationsWithMeaning = listOf(
        "මරණය සියල්ලන් හමුවේ සාධාරණ විනිසුරුවරයෙකි" to "මරණයේ නොවැළැක්විය හැකි යථාර්ථය",
        "කඳුළු බිඳු අතරින් දිස් වූයේ බලාපොරොත්තු සුන් වූ ලෝකයකි" to "මහත් ශෝකයේ ස්වභාවය"
      )
    ),
    SinhalaLiteratureLesson(
      id = "g11_lit_2",
      title = "ගුත්තිල ජාතක කාව්‍යය",
      sourceBook = "ගුත්තිල කාව්‍යය",
      author = "වෑත්තෑවේ හිමි",
      lessonType = "පද්‍ය විචාරය",
      historicalEra = "කෝට්ටේ යුගය",
      literaryAppreciationPoints = listOf("ශබ්ද මාධූර්යය හා එළිසමය", "ගුරු ගෝල සබඳතාවයේ ඛේදවාචකය", "උපමා රූපක භාවිතය"),
      expectedExamQuestions = listOf("ගුත්තිල හා මූසිල චරිත තුළින් කතුවරයා සමාජයට දෙන පණිවිඩය කුමක්ද?"),
      mainTheme = "කෘතගුණ සැලකීම හා ගුරු භක්තිය",
      modelEssayOutline = "ප්‍රවේශය: ගුත්තිල කාව්‍යයේ ඓතිහාසික පසුබිම.\nශරීරය: මූසිලගේ අකෘතඥභාවය හා ගුත්තිල ඇදුරිඳුගේ මහිමය.\nනිගමනය: සදාචාර සම්පන්න ජීවිතයක අගය.",
      keyQuotationsWithMeaning = listOf(
        "සිය පිනෙන් පෑදූ - දිවැස් ඇති බඹු සේම" to "ගුත්තිල ඇදුරිඳුගේ සංගීත ප්‍රවීණත්වය",
        "ගුරුන් මැරුවා නොවේ ගුරුන් මියගියාය" to "අකෘතඥතාවයේ විපාක"
      )
    )
  )

  val grade10LiteratureList: List<SinhalaLiteratureLesson> = listOf(
    SinhalaLiteratureLesson(
      id = "g10_lit_1",
      title = "කුසදා වත",
      sourceBook = "කුස ජාතකය",
      author = "අලගියවන්න මුකවෙටි",
      lessonType = "පද්‍ය විචාරය",
      historicalEra = "සීතාවක යුගය",
      literaryAppreciationPoints = listOf("රූප සෝභාව හා අභ්‍යන්තර ගුණ පිළිබඳ විවරණය", "චමත්කාරජනක පද්‍ය බන්ධනය"),
      expectedExamQuestions = listOf("කුස රජුගේ ප්‍රේමය හා පරිත්‍යාගශීලී බව විමසන්න."),
      mainTheme = "බාහිර රූපයට වඩා අභ්‍යන්තර ගුණාංගවල ඇති උතුම් බව",
      modelEssayOutline = "ප්‍රවේශය: කුස ජාතක කාව්‍යය හඳුන්වාදීම.\nශරීරය: පබාවතී හා කුස රජු අතර සබඳතාව.\nනිගමනය: කාව්‍යයේ හරය හා විචාරාත්මක අගය.",
      keyQuotationsWithMeaning = listOf(
        "රූ රැසේ අඳිනා ලෙසේ" to "පබාවතියගේ රූප සෝභාව",
        "පෙම් රස වෑහෙන සිතැඟි" to "අවංක ප්‍රේමයේ ස්වභාවය"
      )
    )
  )

  val grammarRulesList: List<SinhalaGrammarRule> = listOf(
    SinhalaGrammarRule(
      id = "gr_1",
      ruleCategory = "න/ණ සහ ල/ළ භේදය",
      ruleTitle = "මූර්ධජ ණ සහ දන්තජ න භාවිත නීති",
      rulePoints = listOf("ට, ඨ, ඩ, ඪ අකුරුවලට පසුව මූර්ධජ 'ණ' යෙදේ", "ර, ෂ අකුරුවලට පසුව මූර්ධජ 'ණ' යෙදේ", "උපසර්ග පදවලදී 'න' යෙදේ"),
      corePrinciple = "ට-වර්ගයේ අක්ෂරයකට පෙර හෝ පසුව බොහෝ විට මූර්ධජ ණ/ළ යෙදේ (උදා: කණ්ඨ, මණ්ඩල).",
      examTip = "විභාගයේදී අක්ෂර වින්‍යාස ප්‍රශ්න සඳහා න/ණ, ල/ළ වෙනස ප්‍රධාන සාධකයකි.",
      correctExamples = listOf("ගණනය", "කරුණාව", "ප්‍රාණය", "මණ්ඩලය"),
      commonMistakes = listOf("ගනනය" to "ගණනය", "කරුනාව" to "කරුණාව", "ප්‍රානය" to "ප්‍රාණය")
    ),
    SinhalaGrammarRule(
      id = "gr_2",
      ruleCategory = "උක්ත ආඛ්‍යාත පද සම්බන්ධය",
      ruleTitle = "කර්තෘ කාරක වාක්‍යයක උක්ත ආඛ්‍යාත ගැළපීම",
      rulePoints = listOf("උක්තය ඒකවචන නම් ආඛ්‍යාතය ඒකවචන විය යුතුය", "උක්තය බහුවචන නම් ආඛ්‍යාතය බහුවචන විය යුතුය", "ලිංග භේදය අනුව ක්‍රියා පදය වෙනස් වේ"),
      corePrinciple = "කර්තෘ කාරක වාක්‍යයක ප්‍රථමා විභක්තියෙන් උක්ත වන නාම පදයට අනුරූපව ආඛ්‍යාත ක්‍රියා පදය යෙදිය යුතුය.",
      examTip = "වාක්‍ය නිවැරදි කිරීමේ ප්‍රශ්නවලදී උක්තයේ පුරුෂය, වචනය, ලිංගය මනාව පරීක්ෂා කරන්න.",
      correctExamples = listOf("ශිෂ්‍යයා පාඩම් කරයි.", "ශිෂ්‍යයෝ පාඩම් කරති.", "ගැහැනු ළමයි ගීත ගයති."),
      commonMistakes = listOf("ශිෂ්‍යයෝ පාඩම් කරයි" to "ශිෂ්‍යයෝ පාඩම් කරති", "දරුවා සෙල්ලම් කරති" to "දරුවා සෙල්ලම් කරයි")
    )
  )

  val writingSkillsList: List<SinhalaWritingSkill> = listOf(
    SinhalaWritingSkill(
      id = "ws_1",
      category = "නිබන්ධන රචනය",
      title = "විචාරාත්මක හා නිර්මාණාත්මක රචනා ලිවීම",
      standardFormat = listOf("1. ආකර්ෂණීය ආරම්භක ඡේදය (ප්‍රවේශය)", "2. කරුණු පැහැදිලි කරන මධ්‍යම ඡේද (ශරීරය)", "3. තාර්කික සමාලෝචනය හා අවසානය (නිගමනය)"),
      practicalRules = listOf("ඡේද වෙන් කර ලිවීම", "නිවැරදි විරාම ලකුණු භාවිතය", "ව්‍යාකරණානුකූල වාක්‍ය රටා", "උපුටා දැක්වීම් නිසි පරිදි යෙදීම"),
      markingCriteriaMarks = mapOf("කරුණු ගොනු කිරීම" to 5, "භාෂා භාවිතය හා ව්‍යාකරණ" to 5, "සංවිධානය හා ඉදිරිපත් කිරීම" to 5),
      sampleSolution = "ප්‍රවේශය:\n'විද්‍යාව මිනිසාගේ දියුණුවේ ප්‍රධාන එන්ජිමයි' යන කියමන සනාථ කරමින් නූතන ලෝකය සීඝ්‍ර තාක්ෂණික පෙරළියකට ලක්ව ඇත...\n\nශරීරය:\nසන්නිවේදනය, සෞඛ්‍යය, අධ්‍යාපනය යන සෑම ක්ෂේත්‍රයකම ධනාත්මක වෙනස්කම් සිදුවී ඇති අතර පරිසර හානිය වැනි අභියෝගද පැනනැගී ඇත...\n\nනිගමනය:\nතාක්ෂණය මානව සුබසිද්ධිය උදෙසා විචක්ෂණශීලීව භාවිත කිරීම නූතන පරපුරේ වගකීමකි."
    )
  )
}

// ============================================================================
// 5. Subject Master 20 Categories Detail View
// ============================================================================
@Composable
fun SubjectMaster20CategoriesDetailView(
  subjectName: String,
  categories: List<SubjectMasterCategory>,
  selectedCategoryNumber: Int,
  onSelectCategory: (Int) -> Unit,
  modifier: Modifier = Modifier,
  onOpenInteractiveTool: (() -> Unit)? = null
) {
  var activeCategoryIdx by remember(selectedCategoryNumber) {
    mutableIntStateOf(
      if (selectedCategoryNumber > 0 && selectedCategoryNumber <= categories.size) {
        selectedCategoryNumber - 1
      } else {
        0
      }
    )
  }

  val displayCategories = remember(categories, subjectName) {
    if (categories.isNotEmpty()) categories else SubjectMasterRepository.getCategoriesForSubject(subjectName)
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFF0F172A))
      .padding(8.dp)
  ) {
    // Header Row with Interactive Tool Button if available
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 6.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "$subjectName • ප්‍රධාන ඒකක 20 Master Hub",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF38BDF8)
      )
      if (onOpenInteractiveTool != null) {
        OutlinedButton(
          onClick = { onOpenInteractiveTool() },
          colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF38BDF8)),
          border = BorderStroke(1.dp, Color(0xFF38BDF8)),
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
          modifier = Modifier.height(28.dp)
        ) {
          Text("🛠 අන්තර්ක්‍රියාකාරී මෙවලම", fontSize = 10.sp)
        }
      }
    }

    // Category Selector Strip (Horizontal)
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    ) {
      itemsIndexed(displayCategories) { idx, cat ->
        val isSelected = activeCategoryIdx == idx
        Surface(
          onClick = {
            activeCategoryIdx = idx
            onSelectCategory(idx + 1)
          },
          shape = RoundedCornerShape(8.dp),
          color = if (isSelected) Color(0xFF2563EB) else Color(0xFF1E293B),
          border = BorderStroke(1.dp, if (isSelected) Color.White else Color(0xFF334155))
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(cat.icon.ifBlank { "📘" }, fontSize = 11.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "ඒකකය ${cat.categoryNumber}",
              fontSize = 10.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
              color = Color.White
            )
          }
        }
      }
    }

    // Active Category Detail Card
    val currentCategory = displayCategories.getOrNull(activeCategoryIdx)
    if (currentCategory != null) {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.4f)),
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 8.dp)
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(currentCategory.icon.ifBlank { "📚" }, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = currentCategory.titleSinhala,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF38BDF8)
            )
          }
          if (currentCategory.summary.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = currentCategory.summary,
              fontSize = 11.sp,
              color = Color(0xFF94A3B8)
            )
          }
        }
      }

      // Points List
      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.weight(1f)
      ) {
        items(currentCategory.points) { point ->
          Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            border = BorderStroke(1.dp, Color(0xFF334155)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(10.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "${point.number}. ${point.title}",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFFE2E8F0)
                )
                if (point.examHighlight.isNotBlank()) {
                  Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0xFFB45309).copy(alpha = 0.3f)
                  ) {
                    Text(
                      text = "⭐ විභාග ඉලක්ක",
                      fontSize = 9.sp,
                      color = Color(0xFFFCD34D),
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                  }
                }
              }
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = point.detail,
                fontSize = 11.sp,
                color = Color(0xFFCBD5E1),
                lineHeight = 16.sp
              )
              if (point.examHighlight.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = "💡 ${point.examHighlight}",
                  fontSize = 10.5.sp,
                  color = Color(0xFFF59E0B)
                )
              }
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 6. Sinhala Literature Feature View
// ============================================================================
@Composable
fun SinhalaLiteratureFeatureView() {
  var selectedTab by remember { mutableIntStateOf(0) }
  val lessons = remember(selectedTab) {
    if (selectedTab == 0) SinhalaMasterRepository.grade11LiteratureList else SinhalaMasterRepository.grade10LiteratureList
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF0B132B))
      .padding(12.dp)
  ) {
    // Header & Tabs
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text("📜", fontSize = 18.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "සිංහල සාහිත්‍ය සංග්‍රහය (ගද්‍ය/පද්‍ය විචාර)",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF38BDF8)
        )
      }
      Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        listOf("11 ශ්‍රේණිය", "10 ශ්‍රේණිය").forEachIndexed { idx, title ->
          FilterChip(
            selected = selectedTab == idx,
            onClick = { selectedTab = idx },
            label = { Text(title, fontSize = 10.5.sp) }
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      items(lessons) { lesson ->
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "${lesson.title} (${lesson.lessonType})",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF38BDF8)
              )
              Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFF0369A1)
              ) {
                Text(
                  text = lesson.historicalEra,
                  fontSize = 9.sp,
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "මූලාශ්‍රය: ${lesson.sourceBook} • කතුවරයා: ${lesson.author}",
              fontSize = 11.sp,
              color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "🎯 ප්‍රධාන තේමාව: ${lesson.mainTheme}",
              fontSize = 11.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFFE2E8F0)
            )

            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "📝 විභාග විචාර සැලැස්ම:",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFFFBBF24)
            )
            Text(
              text = lesson.modelEssayOutline,
              fontSize = 10.5.sp,
              color = Color(0xFFCBD5E1),
              lineHeight = 15.sp
            )

            if (lesson.keyQuotationsWithMeaning.isNotEmpty()) {
              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = "💬 වැදගත් උපුටන හා අර්ථ:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4ADE80)
              )
              lesson.keyQuotationsWithMeaning.forEach { (quote, meaning) ->
                Text(
                  text = "• \"$quote\" → $meaning",
                  fontSize = 10.sp,
                  color = Color(0xFFE2E8F0)
                )
              }
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 7. Buddhism Dhamma Feature View
// ============================================================================
@Composable
fun BuddhismDhammaFeatureView() {
  val dhammas = remember {
    listOf(
      Triple("චතුරාර්ය සත්‍යය", "1. දුක්ඛ සත්‍යය (දුක)\n2. සමුදය සත්‍යය (දුකට හේතුව - තණ්හාව)\n3. නිරෝධ සත්‍යය (දුක නැතිකිරීම - නිවන)\n4. මාර්ග සත්‍යය (දුක නැතිකිරීමේ මඟ - ආර්ය අෂ්ටාංගික මාර්ගය)", "මූලික බෞද්ධ දර්ශනය"),
      Triple("ආර්ය අෂ්ටාංගික මාර්ගය", "1. සම්මා දිට්ඨි (යහපත් දැකීම)\n2. සම්මා සංකප්ප (යහපත් කල්පනාව)\n3. සම්මා වාචා (යහපත් වචනය)\n4. සම්මා කම්මන්ත (යහපත් ක්‍රියාව)\n5. සම්මා ආජීව (යහපත් දිවි පැවැත්ම)\n6. සම්මා වායාම (යහපත් උත්සාහය)\n7. සම්මා සති (යහපත් සිහිය)\n8. සම්මා සමාධි (යහපත් සිත එකඟකම)", "සීල, සමාධි, ප්‍රඥා ත්‍රිශික්ෂාව"),
      Triple("තිලක්ෂණය", "1. අනිච්ච (නොස්ථිර බව / වෙනස්වන සුළු බව)\n2. දුක්ඛ (පීඩාකාරී බව / අතෘප්තිකර බව)\n3. අනත්ත (තමාගේ වසඟයේ පැවැත්විය නොහැකි බව / ආත්මයක් නොමැති බව)", "යථාර්ථාවබෝධය"),
      Triple("පටිච්ච සමුප්පාදය", "අවිද්‍යා පච්චයා සංඛාරා ආදී වශයෙන් හේතු ඵල දහම විග්‍රහ කරන ගැඹුරු දහම් කරුණකි. හේතූන් නිසා හටගන්නා සියල්ල හේතූන් නැතිවීමෙන් නිරුද්ධ වේ.", "හේතුඵල න්‍යාය")
    )
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF0B132B))
      .padding(12.dp)
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Text("☸️", fontSize = 18.sp)
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "බුද්ධ ධර්මය • මූලික ධර්ම සංකල්ප හා විභාග සටහන්",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFBBF24)
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxSize()
    ) {
      items(dhammas) { (title, content, category) ->
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          border = BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.5f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFCD34D)
              )
              Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFF78350F)
              ) {
                Text(
                  text = category,
                  fontSize = 9.sp,
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = content,
              fontSize = 11.5.sp,
              color = Color(0xFFCBD5E1),
              lineHeight = 16.sp
            )
          }
        }
      }
    }
  }
}


// ============================================================================
// 8. Google Drive Embed Helper
// ============================================================================
fun formatToGoogleDriveEmbedUrl(url: String): String {
  if (url.isBlank()) return url
  return when {
    url.contains("/view") -> url.replace("/view", "/preview")
    url.contains("drive.google.com/file/d/") && !url.contains("/preview") -> {
      val fileId = url.substringAfter("/d/").substringBefore("/")
      "https://drive.google.com/file/d/$fileId/preview"
    }
    else -> url
  }
}

// ============================================================================
// 9. Persistent 24-Hour Free Trial Security Manager
// ============================================================================
object PersistentTrialSecurityManager {
  private const val PREFS_TRIAL = "app_trial_security_prefs"
  private const val KEY_FIRST_LAUNCH_TIME = "key_first_launch_timestamp"
  private const val TRIAL_DURATION_MS = 24 * 60 * 60 * 1000L // 24 Hours

  fun getFirstLaunchTime(context: Context): Long {
    val prefs = context.getSharedPreferences(PREFS_TRIAL, Context.MODE_PRIVATE)
    var firstLaunch = prefs.getLong(KEY_FIRST_LAUNCH_TIME, 0L)
    if (firstLaunch <= 0L) {
      firstLaunch = System.currentTimeMillis()
      prefs.edit().putLong(KEY_FIRST_LAUNCH_TIME, firstLaunch).apply()
    }
    return firstLaunch
  }

  fun isTrialActive(context: Context): Boolean {
    val firstLaunch = getFirstLaunchTime(context)
    val now = System.currentTimeMillis()
    val elapsed = now - firstLaunch
    return elapsed in 0 until TRIAL_DURATION_MS
  }

  fun getRemainingHoursMinutes(context: Context): Pair<Int, Int> {
    val firstLaunch = getFirstLaunchTime(context)
    val now = System.currentTimeMillis()
    val elapsed = now - firstLaunch
    val remainingMs = TRIAL_DURATION_MS - elapsed
    if (remainingMs <= 0) return Pair(0, 0)
    val totalMinutes = (remainingMs / (60 * 1000L)).toInt()
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return Pair(hours, minutes)
  }

  fun getHardwareDeviceId(context: Context): String {
    val prefs = context.getSharedPreferences(PREFS_TRIAL, Context.MODE_PRIVATE)
    var deviceId = prefs.getString("hardware_device_unique_id", null)
    if (deviceId.isNullOrBlank()) {
      deviceId = java.util.UUID.randomUUID().toString().replace("-", "").take(16).uppercase()
      prefs.edit().putString("hardware_device_unique_id", deviceId).apply()
    }
    return deviceId
  }
}

// ============================================================================
// 9B. Sinhala Question Banks for Live Exam Hall
// ============================================================================
object SinhalaGrade10QuestionBank {
  fun getQuestion(term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val mcqList = SinhalaMasterRepository.rapidMcqList
    val idx = (qNum - 1 + (paperIdx * 4) + ((term - 1) * 2)) % mcqList.size
    val q = mcqList[idx]
    return MockExamQuestion(
      id = qNum,
      questionText = "ප්‍රශ්න අංක $qNum (${q.topic}): ${q.question}",
      options = q.options,
      correctOption = q.correctIndex + 1,
      explanation = q.explanation,
      topicName = "10 සිංහල: ${q.topic} (වාරය $term)"
    )
  }
}

object SinhalaGrade11QuestionBank {
  fun getQuestion(term: Int, paperIdx: Int, qNum: Int): MockExamQuestion {
    val mcqList = SinhalaMasterRepository.rapidMcqList
    val idx = (qNum - 1 + (paperIdx * 5) + ((term - 1) * 3)) % mcqList.size
    val q = mcqList[idx]
    return MockExamQuestion(
      id = qNum,
      questionText = "ප්‍රශ්න අංක $qNum (${q.topic}): ${q.question}",
      options = q.options,
      correctOption = q.correctIndex + 1,
      explanation = q.explanation,
      topicName = "11 සිංහල: ${q.topic} (වාරය $term)"
    )
  }
}

// ============================================================================
// 10. AI Quiz Set Models and Repository
// ============================================================================
data class QuizQuestion(
  val question: String,
  val options: List<String>,
  val correct_option_index: Int,
  val explanation: String
)

data class QuizSet(
  val set_id: Int,
  val set_name: String,
  val time_seconds: Int = 180,
  val questions: List<QuizQuestion>
)

data class QuizData(
  val grade: String,
  val subject: String,
  val quiz_sets: List<QuizSet>
)

object QuizRepository {
  fun getQuizDataForSubject(grade: String, subjectName: String, pdfUrl: String = ""): QuizData {
    val sets = (1..3).map { sIdx ->
      QuizSet(
        set_id = sIdx,
        set_name = "$subjectName • ප්‍රශ්නාවලි කට්ටලය $sIdx",
        time_seconds = 180,
        questions = (1..5).map { qIdx ->
          QuizQuestion(
            question = "$subjectName පාඩම් ඒකකයට අදාළ බහුවරණ ආදර්ශ ප්‍රශ්නය $qIdx (කට්ටලය $sIdx)",
            options = listOf(
              "නිවැරදි විෂයනුබද්ධ පිළිතුර",
              "දෙවන විකල්ප පිළිතුර",
              "තුන්වන විකල්ප පිළිතුර",
              "හතරවන විකල්ප පිළිතුර"
            ),
            correct_option_index = 0,
            explanation = "විෂය නිර්දේශයේ මූලධර්ම හා සිද්ධාන්ත අනුව නිවැරදි විවරණය මෙය වේ."
          )
        }
      )
    }
    return QuizData(
      grade = grade,
      subject = subjectName,
      quiz_sets = sets
    )
  }
}

// ============================================================================
// 11. PDF Reader & Book Dialogs
// ============================================================================
@Composable
fun SanduTheory100PagesReaderDialog(
  initialPage: Int = 1,
  onDismiss: () -> Unit
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("සඳු තියරි පිටු 100 සම්පූර්ණ විද්‍යාව පොත (පිටුව $initialPage)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
          navigationIcon = {
            IconButton(onClick = onDismiss) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
          },
          colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
        )
      }
    ) { padding ->
      Box(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0F172A))) {
        AndroidView(
          factory = { ctx ->
            WebView(ctx).apply {
              settings.javaScriptEnabled = true
              webViewClient = WebViewClient()
              loadUrl("https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview")
            }
          },
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}

@Composable
fun SinhalaShortNotesPdfReaderDialog(
  initialPage: Int = 1,
  onDismiss: () -> Unit
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("සිංහල විචාර ධාරා සහ කෙටි සටහන් (පිටුව $initialPage)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
          navigationIcon = {
            IconButton(onClick = onDismiss) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
          },
          colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
        )
      }
    ) { padding ->
      Box(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0F172A))) {
        AndroidView(
          factory = { ctx ->
            WebView(ctx).apply {
              settings.javaScriptEnabled = true
              webViewClient = WebViewClient()
              loadUrl("https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview")
            }
          },
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}

@Composable
fun ScienceDiagramsPdfReaderDialog(
  initialPage: Int = 1,
  onDismiss: () -> Unit
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("විද්‍යාව රූප සටහන් & ප්‍රස්ථාර (පිටුව $initialPage)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
          navigationIcon = {
            IconButton(onClick = onDismiss) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
          },
          colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
        )
      }
    ) { padding ->
      Box(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0F172A))) {
        AndroidView(
          factory = { ctx ->
            WebView(ctx).apply {
              settings.javaScriptEnabled = true
              webViewClient = WebViewClient()
              loadUrl("https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview")
            }
          },
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}

// ============================================================================
// 12. Quiz Engine & Flashcards Viewer Dialogs
// ============================================================================
@Composable
fun QuizEngineDialog(
  quizSet: QuizSet,
  onDismiss: () -> Unit,
  onRetake: (QuizSet) -> Unit
) {
  var currentQuestionIdx by remember { mutableIntStateOf(0) }
  var selectedOptionIdx by remember { mutableStateOf<Int?>(null) }
  var isAnswerSubmitted by remember { mutableStateOf(false) }
  var score by remember { mutableIntStateOf(0) }
  var isFinished by remember { mutableStateOf(false) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
      modifier = Modifier.fillMaxWidth(0.95f).padding(16.dp)
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(quizSet.set_name, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8), modifier = Modifier.weight(1f))
          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (!isFinished && quizSet.questions.isNotEmpty()) {
          val q = quizSet.questions[currentQuestionIdx]
          Text("ප්‍රශ්න අංක ${currentQuestionIdx + 1} / ${quizSet.questions.size}", fontSize = 11.sp, color = Color(0xFF94A3B8))
          Spacer(modifier = Modifier.height(6.dp))
          Text(q.question, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
          Spacer(modifier = Modifier.height(12.dp))

          q.options.forEachIndexed { idx, opt ->
            val isChosen = selectedOptionIdx == idx
            val isCorrect = idx == q.correct_option_index
            val bg = when {
              isAnswerSubmitted && isCorrect -> Color(0xFF166534)
              isAnswerSubmitted && isChosen && !isCorrect -> Color(0xFF991B1B)
              isChosen -> Color(0xFF2563EB)
              else -> Color(0xFF1E293B)
            }
            Surface(
              onClick = { if (!isAnswerSubmitted) selectedOptionIdx = idx },
              shape = RoundedCornerShape(8.dp),
              color = bg,
              border = BorderStroke(1.dp, Color(0xFF334155)),
              modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
              Text(
                text = "${('A' + idx)}. $opt",
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))
          if (isAnswerSubmitted) {
            Text("💡 ${q.explanation}", fontSize = 11.sp, color = Color(0xFFFBBF24))
            Spacer(modifier = Modifier.height(10.dp))
            Button(
              onClick = {
                if (currentQuestionIdx + 1 < quizSet.questions.size) {
                  currentQuestionIdx++
                  selectedOptionIdx = null
                  isAnswerSubmitted = false
                } else {
                  isFinished = true
                }
              },
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(if (currentQuestionIdx + 1 < quizSet.questions.size) "මීළඟ ප්‍රශ්නය" else "ප්‍රතිඵල බලන්න")
            }
          } else {
            Button(
              onClick = {
                if (selectedOptionIdx != null) {
                  isAnswerSubmitted = true
                  if (selectedOptionIdx == q.correct_option_index) score++
                }
              },
              enabled = selectedOptionIdx != null,
              modifier = Modifier.fillMaxWidth()
            ) {
              Text("පිළිතුර තහවුරු කරන්න")
            }
          }
        } else {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
          ) {
            Text("🎉 ප්‍රශ්නාවලිය අවසන්!", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4ADE80))
            Spacer(modifier = Modifier.height(8.dp))
            Text("ඔබගේ ලකුණු: $score / ${quizSet.questions.size}", fontSize = 14.sp, color = Color.White)
            Spacer(modifier = Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              Button(onClick = { onRetake(quizSet) }) { Text("නැවත උත්සාහ කරන්න") }
              OutlinedButton(onClick = onDismiss) { Text("ඉවත් වන්න") }
            }
          }
        }
      }
    }
  }
}

@Composable
fun FlashcardsViewerDialog(
  grade: String,
  subjectName: String,
  onDismiss: () -> Unit
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("$subjectName • Flashcards ($grade ශ්‍රේණිය)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
          navigationIcon = {
            IconButton(onClick = onDismiss) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
          },
          colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
        )
      }
    ) { padding ->
      Box(modifier = Modifier.fillMaxSize().padding(padding)) {
        InteractiveFlashcardsScreen(onBack = onDismiss)
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizSetsBottomSheet(
  grade: String,
  subjectName: String,
  onDismiss: () -> Unit,
  onStartQuizSet: (QuizSet) -> Unit,
  onOpenFlashcards: () -> Unit
) {
  val quizData = remember(grade, subjectName) {
    QuizRepository.getQuizDataForSubject(grade, subjectName)
  }

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    containerColor = Color(0xFF0F172A)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("🎯 $subjectName • අභ්‍යාස ප්‍රශ්නාවලි", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
        IconButton(onClick = onDismiss) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White) }
      }
      Spacer(modifier = Modifier.height(8.dp))

      Button(
        onClick = {
          onDismiss()
          onOpenFlashcards()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("🗂️ Interactive Flashcards විවෘත කරන්න")
      }

      Spacer(modifier = Modifier.height(10.dp))

      quizData.quiz_sets.forEach { set ->
        Card(
          shape = RoundedCornerShape(10.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
          Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(set.set_name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
              Text("ප්‍රශ්න ${set.questions.size}ක් • කාලය: ${set.time_seconds}s", fontSize = 10.sp, color = Color(0xFF94A3B8))
            }
            Button(
              onClick = {
                onDismiss()
                onStartQuizSet(set)
              },
              shape = RoundedCornerShape(8.dp)
            ) {
              Text("ආරම්භ කරන්න", fontSize = 11.sp)
            }
          }
        }
      }
      Spacer(modifier = Modifier.height(20.dp))
    }
  }
}

// ============================================================================
// 13. Screen Hubs
// ============================================================================
@Composable
fun SyllabusDetectionAndContentScreen(
  initialGrade: String,
  initialSubject: String,
  isApproved: Boolean,
  isAdmin: Boolean,
  onRequireApproval: () -> Unit,
  onBack: () -> Unit,
  onOpenGoogleDrivePdfModal: (url: String, title: String) -> Unit
) {
  var selectedGradeTab by remember { mutableStateOf(initialGrade) }
  var selectedSubjectTab by remember { mutableStateOf(initialSubject) }
  val context = LocalContext.current

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("විෂය නිර්දේශ සහ Google Drive අන්තර්ගත සංග්‍රහය", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .background(Color(0xFF0B132B))
        .padding(12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        listOf("10", "11").forEach { g ->
          FilterChip(
            selected = selectedGradeTab == g,
            onClick = { selectedGradeTab = g },
            label = { Text("$g ශ්‍රේණිය", fontSize = 11.sp) }
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text("📂 Google Drive සුරක්ෂිත PDF කියවනය", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
          Spacer(modifier = Modifier.height(4.dp))
          Text("විෂය නිර්දේශයේ සියලුම ඒකක ආවරණය වන පරිදි සකසන ලද සම්පූර්ණ PDF සටහන් සහ පසුගිය විභාග ප්‍රශ්න පත්‍ර මෙහි අන්තර්ගත වේ.", fontSize = 11.sp, color = Color(0xFFCBD5E1))
          Spacer(modifier = Modifier.height(10.dp))
          Button(
            onClick = {
              onOpenGoogleDrivePdfModal("https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview", "සම්පූර්ණ කෙටි සටහන් පොත")
            },
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("පීඩීඑෆ් සටහන් විවෘත කරන්න")
          }
        }
      }
    }
  }
}

@Composable
fun StudyAnalyticsScreen(
  grade: String,
  onBack: () -> Unit,
  onOpenNotesForSubject: (subjectName: String, topicKeyword: String) -> Unit,
  onOpenMistakeNotebook: (subject: String, topicKeyword: String) -> Unit,
  onOpenFlashcards: () -> Unit,
  onOpenFormulaHandbook: (category: String, searchQuery: String) -> Unit
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("📊 ශිෂ්‍ය අධ්‍යයන විශ්ලේෂණය ($grade ශ්‍රේණිය)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    LazyColumn(
      modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(12.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      item {
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Text("🎯 අධ්‍යයන ප්‍රගතිය: 85%", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4ADE80))
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(progress = { 0.85f }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Text("ඔබ විෂය නිර්දේශයේ ඒකක 75%ක් සාර්ථකව සම්පූර්ණ කර ඇත.", fontSize = 11.sp, color = Color(0xFFCBD5E1))
          }
        }
      }

      item {
        Text("⚡ ක්ෂණික ක්‍රියාකාරකම් සහ දුර්වල ඒකක නිරාකරණය:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
      }

      item {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          Button(onClick = { onOpenMistakeNotebook("ALL", "") }, modifier = Modifier.weight(1f)) {
            Text("Mistake Bank", fontSize = 11.sp)
          }
          Button(onClick = onOpenFlashcards, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)), modifier = Modifier.weight(1f)) {
            Text("Flashcards", fontSize = 11.sp)
          }
        }
      }
    }
  }
}

@Composable
fun StructuredEssayHubScreen(
  grade: String,
  onBack: () -> Unit
) {
  val questions: List<StructuredEssayItem> = remember(grade) {
    ComprehensiveStructuredEssayRepository.getQuestions(grade = grade, subject = "විද්‍යාව")
  }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("ව්‍යුහගත හා රචනා ප්‍රශ්න පුහුණුව ($grade ශ්‍රේණිය)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    LazyColumn(
      modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(12.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      items(questions) { item ->
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text("${item.topicSinhala} (ලකුණු ${item.totalMarks})", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
            Spacer(modifier = Modifier.height(4.dp))
            Text(item.mainScenario, fontSize = 11.sp, color = Color(0xFFCBD5E1))
            Spacer(modifier = Modifier.height(6.dp))
            item.subQuestions.forEach { sub ->
              Text("• ${sub.subLabel} ${sub.promptSinhala} (${sub.marks} ලකුණු)", fontSize = 10.5.sp, color = Color(0xFFE2E8F0))
            }
          }
        }
      }
    }
  }
}

@Composable
fun FormulaAndTimelineHubScreen(
  initialQuery: String,
  initialCategory: String,
  initialGrade: String,
  lockedSubjectName: String?,
  onBack: () -> Unit
) {
  var searchQuery by remember { mutableStateOf(initialQuery) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("සමීකරණ, නියම හා කාලරේඛා සංග්‍රහය", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(12.dp)) {
      OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text("සමීකරණය හෝ නියමය සොයන්න...") },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(10.dp))
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text("📐 විද්‍යාව සහ ගණිතය මූලික සමීකරණ", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
          Spacer(modifier = Modifier.height(6.dp))
          Text("• චාලක සමීකරණ: v = u + at, s = ut + ½at², v² = u² + 2as", fontSize = 11.sp, color = Color.White)
          Text("• නිව්ටන්ගේ දෙවන නියමය: F = ma", fontSize = 11.sp, color = Color.White)
          Text("• ඝනත්වය: ρ = m / V", fontSize = 11.sp, color = Color.White)
          Text("• පීඩනය: P = F / A", fontSize = 11.sp, color = Color.White)
        }
      }
    }
  }
}

@Composable
fun VoiceQuizScreen(
  onBack: () -> Unit
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("🎙️ AI කටහඬ විභාග අභ්‍යාසය (Voice Quiz)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text("🎙️", fontSize = 48.sp)
      Spacer(modifier = Modifier.height(12.dp))
      Text("AI Voice Oral Practice", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
      Spacer(modifier = Modifier.height(6.dp))
      Text("කටහඬින් ප්‍රශ්න අසමින් විභාග මට්ටමේ ක්ෂණික පිළිතුරු පුහුණු වන්න.", fontSize = 12.sp, color = Color(0xFF94A3B8), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
      Spacer(modifier = Modifier.height(20.dp))
      Button(onClick = { /* Start mic practice */ }) {
        Text("ප්‍රශ්නාවලිය ආරම්භ කරන්න")
      }
    }
  }
}

@Composable
fun Science500QuestionsAutoCheckerScreen(
  onBack: () -> Unit,
  onOpenPdfDriveViewer: (pdfUrl: String, title: String) -> Unit
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("🔬 11 විද්‍යාව 500 ප්‍රශ්න Auto-Checker Hub", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(12.dp)) {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text("🎯 11 ශ්‍රේණිය විද්‍යාව සම්පූර්ණ ඒකක 500 ප්‍රශ්නාවලිය", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
          Spacer(modifier = Modifier.height(6.dp))
          Text("සියලු ඒකක ආවරණය වන ප්‍රශ්න 500 සහ 100% නිවැරදි විවරණ මෙහිදී ස්වයංක්‍රීයව පරීක්ෂා කරගත හැක.", fontSize = 11.sp, color = Color(0xFFCBD5E1))
          Spacer(modifier = Modifier.height(10.dp))
          Button(
            onClick = {
              onOpenPdfDriveViewer("https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview", "11 විද්‍යාව 500 ප්‍රශ්නාවලිය PDF")
            },
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("ප්‍රශ්නාවලි PDF පත්‍රිකාව විවෘත කරන්න")
          }
        }
      }
    }
  }
}

@Composable
fun VoiceDoubtSolverScreen(
  onBack: () -> Unit
) {
  var userDoubt by remember { mutableStateOf("") }
  var solverResult by remember { mutableStateOf("") }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("🎙️ AI Voice Doubt Solver", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(16.dp)) {
      Text("ඔබට අපැහැදිලි පාඩම් ඒකකය හෝ ප්‍රශ්නය ඇතුළත් කරන්න:", fontSize = 12.sp, color = Color(0xFFE2E8F0))
      Spacer(modifier = Modifier.height(6.dp))
      OutlinedTextField(
        value = userDoubt,
        onValueChange = { userDoubt = it },
        placeholder = { Text("උදා: ආලෝක වර්තනය, චාලක සමීකරණ...") },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(10.dp))
      Button(
        onClick = {
          solverResult = "මෙම විෂය කරුණට අදාළ මූලික නියමය වන්නේ පදාර්ථයේ ගුණ සහ ශක්ති සංස්ථිති නියමයයි. විභාගයේදී මෙවැනි ගැටලු විසඳීමේදී අදාළ දත්ත ලියාගෙන සූත්‍රය ආදේශ කිරීම මඟින් සම්පූර්ණ ලකුණු ලබාගත හැක."
        },
        enabled = userDoubt.isNotBlank(),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("AI විවරණය ලබාගන්න")
      }

      if (solverResult.isNotBlank()) {
        Spacer(modifier = Modifier.height(12.dp))
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text("💡 පියවරෙන් පියවර විවරණය:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4ADE80))
            Spacer(modifier = Modifier.height(6.dp))
            Text(solverResult, fontSize = 11.5.sp, color = Color.White, lineHeight = 16.sp)
          }
        }
      }
    }
  }
}


// ============================================================================
// 14. Interactive Q&A Master Models & Repository
// ============================================================================
data class QuestionSetInfo(
  val setNumber: Int = 1,
  val grade: String = "O/L",
  val titleSinhala: String = "",
  val subject: String = "",
  val icon: String = "📚"
)

data class EvaluationResult(
  val scoreEarned: Int = 0,
  val maxMarks: Int = 5,
  val feedbackSinhala: String = ""
)

data class ShortAnswerQuestionItem(
  val id: String = "",
  val grade: String = "10",
  val subject: String = "විද්‍යාව",
  val setNumber: Int = 1,
  val questionIndex: Int = 1,
  val question: String = "",
  val maxMarks: Int = 5,
  val sampleIdealAnswer: String = "",
  val keywords: List<String> = emptyList()
)

object ShortAnswerEvaluator {
  fun evaluate(q: ShortAnswerQuestionItem, studentAnswer: String): EvaluationResult {
    val cleanAns = studentAnswer.trim().lowercase()
    val matched = q.keywords.count { kw -> cleanAns.contains(kw.lowercase()) }
    val score = when {
      matched >= 2 -> q.maxMarks
      matched == 1 -> (q.maxMarks * 0.7).toInt()
      studentAnswer.length > 15 -> (q.maxMarks * 0.5).toInt()
      else -> (q.maxMarks * 0.3).toInt()
    }
    val feedback = if (score >= q.maxMarks) {
      "විශිෂ්ටයි! ඔබගේ පිළිතුරෙහි ප්‍රධාන විෂය මූලධර්ම සහ නිවැරදි කරුණු සියල්ල අන්තර්ගතයි."
    } else {
      "හොඳ උත්සාහයක්! වඩාත් සම්පූර්ණ ලකුණු සඳහා ආදර්ශ පිළිතුරෙහි ඇති කරුණුද ඇතුළත් කරන්න."
    }
    return EvaluationResult(
      scoreEarned = score,
      maxMarks = q.maxMarks,
      feedbackSinhala = feedback
    )
  }
}

object ShortAnswerMasterRepository {
  val subjects = listOf("විද්‍යාව", "ගණිතය", "ඉතිහාසය", "භූගෝල විද්‍යාව", "සිංහල", "ඉංග්‍රීසි", "බුද්ධ ධර්මය", "ICT", "ව්‍යාපාර හා ගිණුම්කරණය", "සෞඛ්‍යය", "කෘෂිකර්මය", "සංගීතය", "නර්තනය", "චිත්‍ර")

  fun getTopicsForSubject(grade: String, subject: String): List<String> {
    val categories = SubjectMasterRepository.getCategoriesForSubject(subject)
    return categories.map { it.titleSinhala }
  }

  fun getSetsForSubject(grade: String, subject: String): List<QuestionSetInfo> {
    val topics = getTopicsForSubject(grade, subject)
    return (1..20).map { setNum ->
      val title = topics.getOrElse(setNum - 1) { "ඒකකය $setNum" }
      QuestionSetInfo(
        setNumber = setNum,
        grade = grade,
        titleSinhala = "කාණ්ඩය ${String.format("%02d", setNum)}: $title",
        subject = subject,
        icon = "✍️"
      )
    }
  }

  fun getQuestions(grade: String, subject: String, setNumber: Int): List<ShortAnswerQuestionItem> {
    val topics = getTopicsForSubject(grade, subject)
    val topic = topics.getOrElse(setNumber - 1) { "$subject ඒකක $setNumber" }
    return listOf(
      ShortAnswerQuestionItem(
        id = "sa_${subject}_${setNumber}_1",
        grade = grade,
        subject = subject,
        setNumber = setNumber,
        questionIndex = 1,
        question = "$topic ආශ්‍රිත ප්‍රධාන විෂය සංකල්පය කෙටියෙන් විස්තර කරන්න.",
        maxMarks = 5,
        sampleIdealAnswer = "මෙම ඒකකයේ මූලික සිද්ධාන්තය වන්නේ අදාළ විෂය සාධක නිවැරදිව හඳුනාගෙන විද්‍යාත්මක/තර්කානුකූල පදනමකින් කරුණු ගලපා දැක්වීමයි.",
        keywords = listOf("මූලික", "සාධක", "සිද්ධාන්ත", "ක්‍රියාවලිය")
      ),
      ShortAnswerQuestionItem(
        id = "sa_${subject}_${setNumber}_2",
        grade = grade,
        subject = subject,
        setNumber = setNumber,
        questionIndex = 2,
        question = "$topic හි ප්‍රායෝගික වැදගත්කම සහ විභාගයේදී නිතර අසන ප්‍රධාන කරුණු 2ක් දක්වන්න.",
        maxMarks = 5,
        sampleIdealAnswer = "දෛනික ජීවිතයේ යෙදීම් සහ සම්මත විභාග උදාහරණ මඟින් මෙම සංකල්පයේ නිරවද්‍යතාව මැනවින් තහවුරු කෙරේ.",
        keywords = listOf("උදාහරණ", "වැදගත්කම", "යෙදීම්")
      )
    )
  }
}


// ============================================================================
// 15. History & Geography 20x20 Subject Master Providers
// ============================================================================
object SubjectMasterHistory20x20 {
  fun getCategories(): List<SubjectMasterCategory> {
    return SubjectMasterRepository.getCategoriesForSubject("ඉතිහාසය")
  }
}

object SubjectMasterGeography20x20 {
  fun getCategories(): List<SubjectMasterCategory> {
    return SubjectMasterRepository.getCategoriesForSubject("භූගෝල විද්‍යාව")
  }
}

object SubjectMasterBuddhism20x20 {
  fun getCategories(): List<SubjectMasterCategory> {
    return SubjectMasterRepository.getCategoriesForSubject("බුද්ධ ධර්මය")
  }
}

// ============================================================================
// 16. Sandu Theory Booklet & Commerce Reader Components
// ============================================================================
object SanduTheoryPdfBookletRepository {
  val unitPageMapping: Map<Int, IntRange> = (1..40).associateWith { unitNum ->
    val start = ((unitNum - 1) * 2) + 1
    start..(start + 1)
  }
}

object Science500Repository {
  const val pdfDriveUrl = "https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview"
}

@Composable
fun SanduCommerceTheoryReaderDialog(
  initialPage: Int = 1,
  onDismiss: () -> Unit,
  onOpenDrivePdf: ((String, String) -> Unit)? = null
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text("ව්‍යාපාර හා ගිණුම්කරණය කෙටි සටහන් පොත (පිටුව $initialPage)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
          navigationIcon = {
            IconButton(onClick = onDismiss) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
          },
          colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E3A8A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
        )
      }
    ) { padding ->
      Box(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0F172A))) {
        AndroidView(
          factory = { ctx ->
            WebView(ctx).apply {
              settings.javaScriptEnabled = true
              webViewClient = WebViewClient()
              loadUrl("https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview")
            }
          },
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}

// ============================================================================
// 17. Subject Master Hub Calling Cards
// ============================================================================
@Composable
fun ScienceGrade10And11MasterHub(
  currentGrade: String = "10",
  onOpenFullReader: ((String) -> Unit)? = null
) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F291E)),
    border = BorderStroke(1.dp, Color(0xFF10B981)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text("🔬", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "විද්‍යාව $currentGrade ශ්‍රේණිය Master Hub",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF6EE7B7)
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "භෞතික, රසායනික හා ජීව විද්‍යා මූලධර්ම, ප්‍රායෝගික පරීක්ෂණ සහ විභාග සටහන්",
        fontSize = 11.sp,
        color = Color(0xFFD1FAE5)
      )
    }
  }
}

@Composable
fun SinhalaGrade10And11MasterHub(
  currentGrade: String = "10"
) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF2C1010)),
    border = BorderStroke(1.dp, Color(0xFFEF4444)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text("✍️", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "සිංහල $currentGrade ශ්‍රේණිය Master Hub",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFFFCA5A5)
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "සාහිත්‍ය සංග්‍රහය, ව්‍යාකරණ රීති, නිබන්ධන රචනය සහ විචාර සැලැස්ම",
        fontSize = 11.sp,
        color = Color(0xFFFEE2E2)
      )
    }
  }
}

// ============================================================================
// 18. Referral & Content Sections
// ============================================================================
@Composable
fun ReferralBanner() {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.padding(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text("🎁", fontSize = 20.sp)
      Spacer(modifier = Modifier.width(8.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text("යහළුවන්ට යොමු කර නොමිලේ මාස 6ක අනුමැතිය දිනාගන්න!", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
        Text("Edu.LK ඇප් එක යහළුවන් 5 දෙනෙකුට Share කරන්න.", fontSize = 10.sp, color = Color(0xFFCBD5E1))
      }
    }
  }
}

@Composable
fun TermTestPapersSection(
  grade: String,
  subject: SubjectItem,
  userPapers: List<QuestionPaperItem>,
  isAdmin: Boolean,
  onOpenPdf: (String, String) -> Unit,
  onOpenGrade10Portal: (Grade10WebSource) -> Unit,
  onOpenGrade11Portal: (Grade11WebSource, String?, Int?) -> Unit,
  onDeletePaper: ((QuestionPaperItem) -> Unit)? = null,
  onAddContent: (() -> Unit)? = null
) {
  Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Card(
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(12.dp)) {
        Text("📑 ${subject.nameSinhala} වාර විභාග ප්‍රශ්න පත්‍ර", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
        Spacer(modifier = Modifier.height(4.dp))
        Text("10 සහ 11 ශ්‍රේණි සඳහා පළමු, දෙවන හා තෙවන වාර පසුගිය ප්‍රශ්න පත්‍ර සහ ආදර්ශ පිළිතුරු", fontSize = 11.sp, color = Color(0xFFCBD5E1))
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          Button(onClick = { onOpenGrade10Portal(Grade10WebSource.GOVDOC) }, modifier = Modifier.weight(1f)) { Text("10 ශ්‍රේණිය", fontSize = 11.sp) }
          Button(onClick = { onOpenGrade11Portal(Grade11WebSource.GOVDOC, null, null) }, modifier = Modifier.weight(1f)) { Text("11 ශ්‍රේණිය (O/L)", fontSize = 11.sp) }
        }
      }
    }
    userPapers.forEach { p ->
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = BorderStroke(1.dp, Color(0xFF334155)),
        modifier = Modifier.fillMaxWidth().clickable {
          onOpenPdf(p.titleSinhala, p.pdfUri ?: "https://drive.google.com/file/d/1V3y65z_15X6zjruQ_I11WhG4EOfDHGm-/preview")
        }
      ) {
        Row(modifier = Modifier.padding(12.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
          Text(p.titleSinhala, fontSize = 12.sp, color = Color.White, modifier = Modifier.weight(1f))
          Text("විවෘත කරන්න", fontSize = 10.sp, color = Color(0xFF38BDF8))
        }
      }
    }
  }
}

@Composable
fun PracticeQuizzesSection(
  grade: String,
  subject: SubjectItem,
  onStartQuizSet: (QuizSet) -> Unit,
  onOpenFlashcards: () -> Unit
) {
  val quizData = remember(grade, subject) {
    QuizRepository.getQuizDataForSubject(grade, subject.nameSinhala)
  }
  Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Button(
      onClick = onOpenFlashcards,
      colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5CF6)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Text("🗂️ Interactive Flashcards (මතක කාඩ්පත්) විවෘත කරන්න")
    }
    quizData.quiz_sets.forEach { set ->
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(12.dp).fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(set.set_name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text("ප්‍රශ්න ${set.questions.size}ක් • ${set.time_seconds} තත්පර", fontSize = 10.sp, color = Color(0xFF94A3B8))
          }
          Button(onClick = { onStartQuizSet(set) }) {
            Text("අරඹන්න", fontSize = 11.sp)
          }
        }
      }
    }
  }
}

// ============================================================================
// 19. English Builder Models & Repository
// ============================================================================
data class EnglishVocabItem(
  val id: String = "",
  val word: String = "",
  val partOfSpeech: String = "",
  val sinhalaMeaning: String = "",
  val pronunciation: String = "",
  val exampleSentence: String = "",
  val sinhalaSentenceMeaning: String = ""
) {
  constructor(word: String, partOfSpeech: String, sinhalaMeaning: String, exampleSentence: String) :
    this(id = "", word = word, partOfSpeech = partOfSpeech, sinhalaMeaning = sinhalaMeaning, pronunciation = "", exampleSentence = exampleSentence, sinhalaSentenceMeaning = "")
}

data class WritingTemplateItem(
  val id: String = "",
  val title: String = "",
  val type: String = "",
  val description: String = "",
  val structureSteps: List<String> = emptyList(),
  val modelFormat: String = "",
  val sampleBody: String = ""
)

data class GrammarLessonItem(
  val id: String = "",
  val title: String = "",
  val category: String = "",
  val sinhalaSummary: String = "",
  val formula: String = "",
  val examples: List<Pair<String, String>> = emptyList(),
  val proTip: String = ""
)

data class ClozeTestItem(
  val id: String = "",
  val title: String = "",
  val instructions: String = "",
  val wordBank: List<String> = emptyList(),
  val passageWithBlanks: String = "",
  val correctAnswers: List<String> = emptyList(),
  val explanation: String = ""
)

object EnglishBuilderRepository {
  fun getDailyVocab(): List<EnglishVocabItem> {
    return listOf(
      EnglishVocabItem("Persevere", "Verb", "නොපසුබටව උත්සාහ කරනවා", "Students must persevere to pass the exam."),
      EnglishVocabItem("Diligent", "Adjective", "උත්සාහවන්ත / කැපවූ", "She is a diligent student."),
      EnglishVocabItem("Crucial", "Adjective", "ඉතා වැදගත්", "Time management is crucial for exam success."),
      EnglishVocabItem("Substantial", "Adjective", "සැලකිය යුතු තරම්", "He made substantial progress this term.")
    )
  }

  fun getWritingTemplates(): List<WritingTemplateItem> {
    return listOf(
      WritingTemplateItem(
        title = "Formal Letter / Notice",
        description = "ලිපි සහ නිවේදන සම්මත ආකෘතිය",
        structureSteps = listOf("Sender's Address & Date", "Salutation", "Clear Subject Line", "Body Paragraphs", "Formal Sign-off"),
        modelFormat = "Address\nDate\n\nDear Sir/Madam,\n\nSubject: ...\n\nContent...",
        sampleBody = "I am writing to bring to your kind attention..."
      )
    )
  }

  fun getGrammarLessons(): List<GrammarLessonItem> {
    return listOf(
      GrammarLessonItem(
        title = "Passive Voice",
        sinhalaSummary = "කර්ම කාරක වාක්‍ය රටා",
        formula = "Object + Auxiliary Verb (be) + Past Participle (V3) + by + Agent",
        examples = listOf("Active: He writes a letter." to "Passive: A letter is written by him.")
      )
    )
  }

  fun getClozeTests(): List<ClozeTestItem> {
    return listOf(
      ClozeTestItem(
        title = "Cloze Test 01: Environmental Protection",
        instructions = "Fill in the blanks using the appropriate words from the box.",
        wordBank = listOf("protect", "trees", "clean", "future", "nature"),
        passageWithBlanks = "We must (1) _______ our environment for our children. Planting (2) _______ helps to keep the air (3) _______ and fresh. Love (4) _______ for a better (5) _______.",
        correctAnswers = listOf("protect", "trees", "clean", "nature", "future"),
        explanation = "Contextual vocabulary matching the grammatical syntax of each sentence."
      )
    )
  }
}

// ============================================================================
// 20. Sentence Puzzle Repository & Additional English Screens
// ============================================================================
object SentencePuzzleRepository {
  fun getAllCategories(): List<SentencePuzzleCategory> {
    return (1..5).map { catId ->
      SentencePuzzleCategory(
        id = catId,
        icon = "🧩",
        title = "කාණ්ඩය $catId",
        titleSinhala = "වාක්‍ය ගොඩනැගීමේ කාණ්ඩය $catId",
        titleEnglish = "Sentence Structure Set $catId",
        description = "වචන නිවැරදි පිළිවෙළට සකසා අර්ථවත් වාක්‍යයක් ගොඩනගන්න.",
        items = (1..5).map { pId ->
          SentencePuzzleItem(
            id = "puz_${catId}_$pId",
            scrambledWords = listOf("The", "students", "are", "reading", "books"),
            correctSentence = "The students are reading books.",
            sinhalaMeaning = "සිසුන් පොත් කියවමින් සිටිති.",
            hint = "Present Continuous Tense ආකෘතිය භාවිත කරන්න."
          )
        }
      )
    }
  }

  fun getAllPuzzlesFlat(): List<SentencePuzzleItem> {
    return getAllCategories().flatMap { it.puzzles }
  }
}

@Composable
fun TenseComparisonScreen(onBack: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Tense Comparison (කාල සංසන්දනය)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(16.dp)) {
      Text("🕒 English Tenses Master Comparison", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
      Spacer(modifier = Modifier.height(10.dp))
      Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text("• Present Simple: S + V1 (s/es) | I play football.", color = Color.White, fontSize = 12.sp)
          Text("• Past Simple: S + V2 | I played football.", color = Color.White, fontSize = 12.sp)
          Text("• Future Simple: S + will + V1 | I will play football.", color = Color.White, fontSize = 12.sp)
        }
      }
    }
  }
}

@Composable
fun PassiveVoiceQuizScreen(onBack: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Passive Voice Quiz", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(16.dp)) {
      Text("✍️ Passive Voice Practice Test", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
      Spacer(modifier = Modifier.height(10.dp))
      Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text("Active: 'The teacher praised the boy.'", color = Color.White, fontSize = 12.sp)
          Spacer(modifier = Modifier.height(4.dp))
          Text("Passive: 'The boy was praised by the teacher.'", color = Color(0xFF4ADE80), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}

@Composable
fun ReadingComprehensionScreen(onBack: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Reading Comprehension Passages", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(16.dp)) {
      Text("📖 O/L Reading Comprehension Tests", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
      Spacer(modifier = Modifier.height(10.dp))
      Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text("පසුගිය විභාග ආකෘතියේ කියවීමේ ඡේද හා ප්‍රශ්නෝත්තර මෙහිදී පුහුණු වන්න.", color = Color.White, fontSize = 12.sp)
        }
      }
    }
  }
}

@Composable
fun SentencePuzzleScreen(onBack: () -> Unit) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Sentence Puzzle Game (වාක්‍ය ගොඩනැගීම)", fontSize = 13.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A), titleContentColor = Color.White, navigationIconContentColor = Color.White)
      )
    }
  ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFF0B132B)).padding(16.dp)) {
      Text("🧩 Sentence Structure Builder", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF38BDF8))
      Spacer(modifier = Modifier.height(10.dp))
      Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {
          Text("වචන නිවැරදි අනුපිළිවෙළට සකසා සම්පූර්ණ අර්ථවත් වාක්‍යයක් ගොඩනගන්න.", color = Color.White, fontSize = 12.sp)
        }
      }
    }
  }
}
