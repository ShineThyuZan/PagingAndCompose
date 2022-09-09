package klt.mdy.offlinesupportwithpaging.model.user

data class ProfileInfoDTO(
     val status: String,
     val messageCode: Int,
     val message : String ,
     val data : ProfileInfoData,

)
data class ProfileInfoData(
    val userId: Long,
    val username: String,
    val headUrl: String?,
    val coverImgUrl: String,
    val bio: String?,
    val birthDate: String?,
    val gender: Int?,
    val mobile: String?,
    val email: String?,
    val accountId: String,
    val locale: Int,
    val friendCount: Long,
    val friendStatus: Int,
    val isProfileLock: Boolean,
    val groupId: Long,
    val countryId: Int,
    val isTwoFactor: Boolean
) {
    fun toVo(): ProfileInfoVo {
        return ProfileInfoVo(
            userId = userId,
            accountId = accountId,
            username = username,
            avatar = headUrl ?: "",
            wallpaper = coverImgUrl,
            bio = bio ?: "",
            dob = birthDate ?: "",
            gender = gender ?: GenderStatus.EMPTY.status,
            email = email ?: "",
            isLockedProfile = isProfileLock,
            friendCount = friendCount,
            isEnabledTwoFactor = isTwoFactor,
        )
    }
}