package klt.mdy.offlinesupportwithpaging.di.network.user_layer

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.di.network.Constants
import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(
    private val apiRepo: UserApiRepository,
) {
    suspend operator fun invoke(): Flow<Resource<ProfileInfoVo>> {

        val response = apiRepo.getProfileInfo(
            userId = 1001,
            locale = 0
        )
        var returnString: Flow<Resource<ProfileInfoVo>> = emptyFlow()

        response.collect {
            when (it) {
                is RemoteResource.ErrorEvent -> {}
                is RemoteResource.LoadingEvent -> {
                    returnString = flow {
                        emit(Resource.Loading())
                    }
                }
                is RemoteResource.SuccessEvent -> {
                    var success: Resource<ProfileInfoVo> = Resource.Loading()
                    when (it.data!!.status) {
                        Constants.RESPONSE_SUCCESS -> {
                            success = Resource.Success(
                                data = it.data.data.toVo()
                            )
                        }
                        Constants.RESPONSE_FAIL -> {}
                    }
                    returnString = flow {
                        emit(success)
                    }
                }
            }
        }
        return returnString
    }
}