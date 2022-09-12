package klt.mdy.offlinesupportwithpaging.component.userprofile

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.di.network.user_layer.UserApiRepository
import klt.mdy.offlinesupportwithpaging.model.user.LanguageVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AllLanguagesUseCase @Inject constructor(
    private val apiRepo: UserApiRepository,
) {
    suspend operator fun invoke(): Flow<Resource<List<LanguageVo>>> {
        val res = apiRepo.languages(locale = 0)
        var languageVoList: Flow<Resource<List<LanguageVo>>> = emptyFlow()

        res.collect {
            when (it) {
                is RemoteResource.ErrorEvent -> {}
                is RemoteResource.LoadingEvent -> {
                    languageVoList = flow {
                        emit(Resource.Loading())
                    }
                }
                is RemoteResource.SuccessEvent -> {
                    val data = Resource.Success(
                        data = it.data?.data!!.languageList.map { languageData ->
                            languageData.toVo()
                        }
                    )
                    languageVoList = flow {
                        emit(data)
                    }
                }
            }
        }
        return languageVoList
    }
}