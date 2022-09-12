package klt.mdy.offlinesupportwithpaging.domain

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.AppApiRepository
import klt.mdy.offlinesupportwithpaging.model.test.MemeVos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class TestApiUseCase @Inject constructor(
    private val apiRepo: AppApiRepository,

    ) {
    suspend operator fun invoke(): Flow<Resource<List<MemeVos>>> {
        // call api in this under scope
        val res = apiRepo.memeApi()

        var languageVoList: Flow<Resource<List<MemeVos>>> = emptyFlow()

        res.collect {
            when (it) {
                is RemoteResource.ErrorEvent -> {
                    val list = apiRepo.retrieveMeMeList().firstOrNull()
                    languageVoList = flow {
                        emit(
                            Resource.Success(
                                data = list ?: emptyList()
                            )
                        )
                    }
                }
                is RemoteResource.LoadingEvent -> {
                    languageVoList = flow {
                        emit(Resource.Loading())
                    }
                }
                is RemoteResource.SuccessEvent -> {
                    apiRepo.saveMemeList(it.data!!.data.memes.map { memeList ->
                        memeList.toVo()
                    })
                    val data = Resource.Success(
                        data = it.data.data.memes.map { testData ->
                            testData.toVo()
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