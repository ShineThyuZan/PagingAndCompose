package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

import androidx.datastore.core.DataStore
import klt.mdy.offlinesupportwithpaging.di.network.QualifiedAnnotation
import klt.mdy.offlinesupportwithpaging.model.user.FriendStatus
import klt.mdy.offlinesupportwithpaging.model.user.GenderStatus
import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoVo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


open class UserInfoDataStoreImpl @Inject constructor(
    private val ds: DataStore<ProfileInfoVo>,
    @QualifiedAnnotation.Io private val io: CoroutineDispatcher,
) : UserInfoDataStore {

    override suspend fun updateUserInfo(info: ProfileInfoVo) {
        withContext(io) {
            ds.updateData {
                it.copy(
                    userId = info.userId,
                    username = info.username,
                    accountId = info.accountId,
                    countryId = info.countryId,
                    phone = info.phone,
                    avatar = info.avatar,
                    wallpaper = info.wallpaper,
                    bio = info.bio,
                    dob = info.dob,
                    gender = info.gender,
                    email = info.email,
                    isEnabledTwoFactor = info.isEnabledTwoFactor,
                    isLockedProfile = info.isLockedProfile,
                    friendCount = info.friendCount,
                    friendStatus = info.friendStatus
                )
            }
        }
    }

    override suspend fun clearUserInfo() {
        withContext(io) {
            ds.updateData {
                it.copy(
                    userId = -1,
                    username = "",
                    accountId = "",
                    countryId = -1,
                    phone = "",
                    avatar = "",
                    wallpaper = "",
                    bio = "",
                    dob = "",
                    gender = GenderStatus.EMPTY.status,
                    email = "",
                    isEnabledTwoFactor = false,
                    isLockedProfile = false,
                    friendCount = -1,
                    friendStatus = FriendStatus.NOT_FRIEND.status
                )
            }
        }
    }


    override suspend fun getUserInfo(): Flow<ProfileInfoVo> {
        return ds.data
    }

}