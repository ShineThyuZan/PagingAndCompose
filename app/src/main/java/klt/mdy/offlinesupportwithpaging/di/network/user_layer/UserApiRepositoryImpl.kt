package klt.mdy.offlinesupportwithpaging.di.network.user_layer

import klt.mdy.offlinesupportwithpaging.di.network.QualifiedAnnotation
import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.UserService
import klt.mdy.offlinesupportwithpaging.di.network.safeApiCall
import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserApiRepositoryImpl @Inject constructor(
    private val api: UserService,
    @QualifiedAnnotation.Io private val io: CoroutineDispatcher
) : UserApiRepository {
    override suspend fun getProfileInfo(
        userId: Long,
        locale: Int
    ): Flow<RemoteResource<ProfileInfoDTO>> {
        return flow {
            emit(
                safeApiCall {
                    api.getProfileInfo(
                        userId = userId,
                        locale = locale
                    )
                }
            )
        }.flowOn(io)
    }

}