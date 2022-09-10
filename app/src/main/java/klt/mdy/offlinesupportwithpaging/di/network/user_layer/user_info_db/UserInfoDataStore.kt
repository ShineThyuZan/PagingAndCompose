package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoVo
import kotlinx.coroutines.flow.Flow

interface UserInfoDataStore {
    suspend fun updateUserInfo(info: ProfileInfoVo)
    suspend fun clearUserInfo()
    suspend fun getUserInfo() : Flow<ProfileInfoVo>
}