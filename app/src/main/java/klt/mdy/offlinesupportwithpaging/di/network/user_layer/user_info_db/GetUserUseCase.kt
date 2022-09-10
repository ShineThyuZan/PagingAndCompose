package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val ds: UserInfoDataStore,
) {
    suspend operator fun invoke(): Flow<ProfileInfoVo> {
        return ds.getUserInfo().map { it }
    }
}