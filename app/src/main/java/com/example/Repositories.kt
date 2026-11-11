package com.example

object TrilingualGlossaryRepository {
    val allGlossaryTerms: List<GlossaryTermItem> by lazy {
        listOf(
            GlossaryTermItem(
                id = "term_1",
                subject = "විද්‍යාව (Science)",
                englishTerm = "Photosynthesis",
                sinhalaTerm = "ප්‍රභාසංස්ලේෂණය",
                tamilTerm = "ஒளிச்சேர்க்கை",
                definitionSinhala = "ශාක විසින් සූර්යාලෝකය, ජලය සහ කාබන්ඩයොක්සයිඩ් භාවිතයෙන් ග්ලූකෝස් ආහාර නිෂ්පාදනය කිරීමේ ක්‍රියාවලිය.",
                examTip = "විභාගයේදී පත්‍රවල හරිතලව (Chloroplast) සහ හිරු එළිය අත්‍යවශ්‍ය සාධක ලෙස සලකුණු කරන්න."
            ),
            GlossaryTermItem(
                id = "term_2",
                subject = "විද්‍යාව (Science)",
                englishTerm = "Respiration",
                sinhalaTerm = "ස්වසනය",
                tamilTerm = "சுவாசம்",
                definitionSinhala = "සෛල තුළ ග්ලූකෝස් ඔක්සිජන් සමඟ ප්‍රතික්‍රියා කර ශක්තිය (ATP) නිදහස් කිරීමේ ජෛව රසායනික ක්‍රියාවලිය.",
                examTip = "වායුගෝලීය සහ අවායුගෝලීය ස්වසනය අතර වෙනස පැහැදිලි කිරීමට නිතර අසයි."
            ),
            GlossaryTermItem(
                id = "term_3",
                subject = "ගණිතය (Mathematics)",
                englishTerm = "Quadratic Equation",
                sinhalaTerm = "වර්ගජ සමීකරණය",
                tamilTerm = "இருபடிச் சமன்பாடு",
                definitionSinhala = "ax² + bx + c = 0 (a ≠ 0) ආකාරයෙන් ප්‍රකාශ කළ හැකි දෙවන මාත්‍රාවේ වීජීය සමීකරණය.",
                examTip = "සූත්‍රය: x = [-b ± √(b² - 4ac)] / 2a භාවිතයෙන් විසඳීම පුරුදු වන්න."
            ),
            GlossaryTermItem(
                id = "term_4",
                subject = "ගණිතය (Mathematics)",
                englishTerm = "Trigonometry",
                sinhalaTerm = "ත්‍රිකෝණමිතිය",
                tamilTerm = "திரிகோணமிதி",
                definitionSinhala = "ඍජුකෝණී ත්‍රිකෝණයක පාද සහ කෝණ අතර සම්බන්ධතාව අධ්‍යයනය කරන ගණිත ක්ෂේත්‍රය.",
                examTip = "sinθ, cosθ, tanθ අනුපාත මතක තබා ගැනීමට SOH CAH TOA ක්‍රමය යොදාගන්න."
            ),
            GlossaryTermItem(
                id = "term_5",
                subject = "ICT (තොරතුරු තාක්ෂණය)",
                englishTerm = "Algorithm",
                sinhalaTerm = "ඇල්ගොරිතමය",
                tamilTerm = "நெறிமுறை",
                definitionSinhala = "යම් ගැටලුවක් විසඳීම සඳහා අනුගමනය කළ යුතු පියවරෙන් පියවර තාර්කික උපදෙස් සමුදාය.",
                examTip = "ගැලීම් සටහන් (Flowcharts) සහ ව්‍යාජ කේත (Pseudocode) මඟින් ඇල්ගොරිතම නිරූපණය කෙරේ."
            ),
            GlossaryTermItem(
                id = "term_6",
                subject = "ICT (තොරතුරු තාක්ෂණය)",
                englishTerm = "Central Processing Unit",
                sinhalaTerm = "මධ්‍යම සැකසුම් ඒකකය (CPU)",
                tamilTerm = "மத்திய செயலாக்க அலகு",
                definitionSinhala = "පරිගණක පද්ධතියේ ප්‍රධාන ම මොළය ලෙස ක්‍රියාකරන දත්ත සැකසුම් සහ පාලන ඒකකය.",
                examTip = "ALU (Arithmetic Logic Unit) සහ CU (Control Unit) කොටස් 2 පිළිබඳ පැහැදිලි කරන්න."
            ),
            GlossaryTermItem(
                id = "term_7",
                subject = "වාණිජ (Commerce)",
                englishTerm = "Asset",
                sinhalaTerm = "වත්කම්",
                tamilTerm = "சொத்து",
                definitionSinhala = "ව්‍යාපාරයකට හිමි අනාගතයේදී ආර්ථික ප්‍රතිලාභ ලබාදෙන සම්පත්.",
                examTip = "ජංගම වත්කම් (Current Assets) සහ ජංගම නොවන වත්කම් (Non-current Assets) ලෙස වෙන්කෙරේ."
            ),
            GlossaryTermItem(
                id = "term_8",
                subject = "වාණිජ (Commerce)",
                englishTerm = "Liability",
                sinhalaTerm = "වගකීම්",
                tamilTerm = "பொறுப்பு",
                definitionSinhala = "අතීත සිදුවීම් නිසා ව්‍යාපාරය විසින් බාහිර පාර්ශ්වයන් වෙත ගෙවීමට ඇති වගකීම්.",
                examTip = "ගිණුම්කරණ සමීකරණය: වත්කම් = හිමිකම + වගකීම්"
            ),
            GlossaryTermItem(
                id = "term_9",
                subject = "ඉතිහාසය (History)",
                englishTerm = "Monarchy",
                sinhalaTerm = "රාජාණ්ඩුව",
                tamilTerm = "முடியாட்சி",
                definitionSinhala = "එක් රජෙකු හෝ රැජිනක් විසින් පාලනය කරනු ලබන රාජ්‍ය පාලන ක්‍රමය.",
                examTip = "අනුරාධපුර සහ පොළොන්නරු යුගවල රාජ්‍ය පාලන ව්‍යුහය උදාහරණ ලෙස දක්වන්න."
            ),
            GlossaryTermItem(
                id = "term_10",
                subject = "භූගෝල විද්‍යාව (Geography)",
                englishTerm = "Topography",
                sinhalaTerm = "ස්ථාන විවරණය / භූ විෂමතාව",
                tamilTerm = "நிலப்பரப்பியல்",
                definitionSinhala = "පෘථිවි පෘෂ්ඨයේ ඇති කඳු, තැන්න, නිම්න ආදී ස්වාභාවික සහ මානව නිර්මිත ලක්ෂණවල හැඩය.",
                examTip = "සමෝච්ච රේඛා (Contour lines) මඟින් භූ විෂමතාව නිරූපණය කෙරේ."
            )
        )
    }

    fun getGroupNumber(item: GlossaryTermItem): Int {
        val hash = Math.abs(item.id.hashCode())
        return (hash % 10) + 1
    }

    fun getItemIndexInSubject(item: GlossaryTermItem): Int {
        val hash = Math.abs(item.id.hashCode())
        return (hash % 20) + 1
    }
}

object TimedPastPaperRepository {
    fun getQuestionsForExam(selectedSubject: String, selectedYear: String): List<PastPaperQuestion> {
        val yearInt = selectedYear.toIntOrNull() ?: 2023
        val cleanSubject = selectedSubject.trim()

        return (1..60).map { qNum ->
            when {
                cleanSubject.contains("ගණිත") || cleanSubject.contains("Math") ->
                    MathPastPaper60QuestionsProvider.buildMathQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("පුරවැසි") || cleanSubject.contains("Civic") ->
                    CivicsPastPaper60QuestionsProvider.buildCivicsQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("වාණිජ") || cleanSubject.contains("Commerce") ->
                    CommercePastPaper60QuestionsProvider.buildCommerceQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("ඉංග්‍රීසි") || cleanSubject.contains("English") ->
                    EnglishPastPaper60QuestionsProvider.buildEnglishQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("ඉතිහාස") || cleanSubject.contains("History") ->
                    HistoryPastPaper60QuestionsProvider.buildHistoryQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("චිත්‍ර") || cleanSubject.contains("Art") ->
                    ArtPastPaper60QuestionsProvider.buildArtQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("බුද්ධ") || cleanSubject.contains("Buddhism") ->
                    BuddhismPastPaper60QuestionsProvider.buildBuddhismQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("සෞඛ්‍ය") || cleanSubject.contains("Health") ->
                    HealthPastPaper60QuestionsProvider.buildHealthQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("නර්තන") || cleanSubject.contains("Dance") ->
                    DancingPastPaper60QuestionsProvider.buildDancingQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("කෘෂි") || cleanSubject.contains("Agri") ->
                    AgriculturePastPaper60QuestionsProvider.buildAgricultureQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("සංගීත") || cleanSubject.contains("Music") ->
                    MusicPastPaper60QuestionsProvider.buildMusicQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("භූගෝල") || cleanSubject.contains("Geog") ->
                    GeographyPastPaper60QuestionsProvider.buildGeographyQuestion(yearInt, qNum, selectedYear, cleanSubject)
                cleanSubject.contains("ICT") || cleanSubject.contains("තොරතුරු") ->
                    IctPastPaper60QuestionsProvider.buildIctQuestion(yearInt, qNum, selectedYear, cleanSubject)
                else ->
                    PastPaperQuestion(
                        id = qNum,
                        questionText = "[$selectedYear O/L - Q$qNum] $cleanSubject අදාළ ප්‍රශ්නය $qNum: වඩාත්ම නිවැරදි පිළිතුර තෝරන්න.",
                        options = listOf("පළමු පිළිතුර", "දෙවන පිළිතුර", "තුන්වන පිළිතුර", "සිව්වන පිළිතුර"),
                        correctOptionIndex = (qNum % 4),
                        explanationSinhala = "විභාග ප්‍රමිතියට අනුව සකසන ලද නිවැරදි විග්‍රහය.",
                        topicCategory = "General",
                        year = selectedYear,
                        subject = cleanSubject
                    )
            }
        }
    }
}

object StructuredEssayDynamicContentProvider {
    fun buildSubQuestions(grade: String, subject: String, unit: String, type: String, qIndex: Int, setNumber: Int): List<StructuredSubQuestion> {
        return listOf(
            StructuredSubQuestion(
                subLabel = "(a)",
                promptSinhala = "$unit සම්බන්ධයෙන් $subject ප්‍රශ්න කොටස ($qIndex)",
                marks = 4,
                answerKeySinhala = "නිවැරදි පිළිතුර සහ පැහැදිලි කිරීම.",
                markingSchemePoints = listOf("පළමු ලකුණ: නිවැරදි කරුණ", "දෙවන ලකුණ: නිවැරදි විග්‍රහය")
            ),
            StructuredSubQuestion(
                subLabel = "(b)",
                promptSinhala = "$unit හි ප්‍රයෝගික යෙදීම් පිළිබඳ ප්‍රශ්නය",
                marks = 6,
                answerKeySinhala = "ප්‍රයෝගික උදාහරණ සහිත පිළිතුර.",
                markingSchemePoints = listOf("ලකුණු 3: නිරීක්ෂණ පැහැදිලි කිරීම", "ලකුණු 3: නිගමනය")
            )
        )
    }

    fun getTitleAndScenario(grade: String, subject: String, unit: String, type: String, qIndex: Int, setNumber: Int): Pair<String, String> {
        val title = "$subject - $unit (කට්ටලය $setNumber, ප්‍රශ්නය $qIndex)"
        val scenario = "$grade ශ්‍රේණිය $subject $unit පාඩමට අදාළ $type ප්‍රශ්න සාකච්ඡාව."
        return Pair(title, scenario)
    }
}

object VoiceQuizHistoryBank {
    fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "ඉතිහාසය $grade ශ්‍රේණිය - $theme පිළිබඳ ප්‍රශ්නය ($qIndex)",
            "ithihasaya $grade sreniya $theme",
            listOf("ඉතිහාසය", "රාජධානිය", "රජු"),
            "නිවැරදි පිළිතුර",
            "ඉඟිය: ප්‍රධාන ඓතිහාසික කරුණ මතක් කරගන්න."
        )
    }
}

object VoiceQuizScienceBank {
    fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "විද්‍යාව $grade ශ්‍රේණිය - $theme පිළිබඳ ප්‍රශ්නය ($qIndex)",
            "vidyawa $grade sreniya $theme",
            listOf("විද්‍යාව", "මූලධර්මය", "පරීක්ෂණය"),
            "නිවැරදි පිළිතුර",
            "ඉඟිය: විද්‍යාත්මක සංකල්පය අවබෝධ කරගන්න."
        )
    }
}

object VoiceQuizGeographyBank {
    fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "භූගෝල විද්‍යාව $grade ශ්‍රේණිය - $theme පිළිබඳ ප්‍රශ්නය ($qIndex)",
            "bhugola vidyawa $grade sreniya $theme",
            listOf("කාලගුණය", "දේශගුණය", "සිතියම"),
            "නිවැරදි පිළිතුර",
            "ඉඟිය: භූගෝලීය පිහිටීම සිහිපත් කරගන්න."
        )
    }
}

object VoiceQuizMathBank {
    fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "ගණිතය $grade ශ්‍රේණිය - $theme පිළිබඳ ප්‍රශ්නය ($qIndex)",
            "ganithaya $grade sreniya $theme",
            listOf("ගණිතය", "අගය", "විසඳුම"),
            "නිවැරදි පිළිතුර",
            "ඉඟිය: සරල ගණනය කිරීම සිදුකරන්න."
        )
    }
}

object VoiceQuizEnglishBank {
    fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "English Grade $grade - Question for $theme ($qIndex)",
            "english grade $grade question $theme",
            listOf("english", "grammar", "word"),
            "Correct answer",
            "Hint: Check the grammar rule."
        )
    }
}

object VoiceQuizIctBank {
    fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "ICT $grade ශ්‍රේණිය - $theme පිළිබඳ ප්‍රශ්නය ($qIndex)",
            "ict $grade sreniya $theme",
            listOf("ICT", "පරිගණකය", "දත්ත"),
            "නිවැරදි පිළිතුර",
            "ඉඟිය: පරිගණක තාක්ෂණික පදය මතක් කරගන්න."
        )
    }
}

object VoiceQuizBuddhismBank {
    fun getQuestion(grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "බුද්ධ ධර්මය $grade ශ්‍රේණිය - $theme පිළිබඳ ප්‍රශ්නය ($qIndex)",
            "buddhism $grade sreniya $theme",
            listOf("බුද්ධ", "ධර්මය", "සූත්‍රය"),
            "නිවැරදි පිළිතුර",
            "ඉඟිය: ධර්ම කරුණ සිහිපත් කරගන්න."
        )
    }
}

object VoiceQuizGeneralBank {
    fun getQuestion(subject: String, grade: String, setNum: Int, qIndex: Int, theme: String): Quintuple<String, String, List<String>, String, String> {
        return Quintuple(
            "$subject $grade ශ්‍රේණිය - $theme පිළිබඳ ප්‍රශ්නය ($qIndex)",
            "$subject $grade sreniya $theme",
            listOf(subject, "ප්‍රශ්නය", "පිළිතුර"),
            "නිවැරදි පිළිතුර",
            "ඉඟිය: විෂය කරුණ අවබෝධ කරගන්න."
        )
    }
}
