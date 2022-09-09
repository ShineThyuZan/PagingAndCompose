package klt.mdy.offlinesupportwithpaging.model.chat

import kotlinx.serialization.Serializable

@Serializable
data class ProfileInfoVo(
    val userId: Long = -1,
    val accountId: String = "",
    val username: String = "",
    val countryId: Int = -1,
    val phone: String = "",
    val avatar: String = "",
    val wallpaper: String = "",
    val bio: String = "",
    val dob: String = "",
    val dobLong: Long = -1L,
    val gender: Int = GenderStatus.EMPTY.status,
    val email: String = "",
    val isEnabledTwoFactor: Boolean = false,
    val isLockedProfile: Boolean = false,
    val friendCount: Long = 0,
    val friendStatus: Int = FriendStatus.NOT_FRIEND.status,
)