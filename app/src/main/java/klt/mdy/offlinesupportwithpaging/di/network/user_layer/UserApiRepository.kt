package klt.mdy.offlinesupportwithpaging.di.network.user_layer

import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoDTO
import kotlinx.coroutines.flow.Flow

interface UserApiRepository {
    suspend fun getProfileInfo(
        userId: Long,
        locale: Int
    ): Flow<RemoteResource<ProfileInfoDTO>>
}