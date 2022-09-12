package klt.mdy.offlinesupportwithpaging.model.user
data class LanguageDTO(
     val status: String,
     val messageCode: Int,
     val message : String ,
     val data : LanguageData,
)

data class LanguageData(
    val languageList: List<Language>
)

data class Language(
    val description: String,
    val iso: String,
    val locale: Int,
    val name: String
) {
    fun toVo(): LanguageVo {
        return LanguageVo(
            name = name,
            description = description,
            locale = locale
        )
    }
}