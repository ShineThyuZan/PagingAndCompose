package klt.mdy.offlinesupportwithpaging.domain

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.AppApiRepository
import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.model.test.DataObjVos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class TestApiUseCase @Inject constructor(
    private val apiRepo: AppApiRepository,
) {
    suspend operator fun invoke(): Flow<Resource<List<DataObjVos>>> {
        // call api in this under scope
        val res = apiRepo.testApi()

        var languageVoList: Flow<Resource<List<DataObjVos>>> = emptyFlow()

        res.collect {
            when (it) {
                is RemoteResource.ErrorEvent -> {
                }
                is RemoteResource.LoadingEvent -> {
                    languageVoList = flow {
                        emit(Resource.Loading())
                    }
                }
                is RemoteResource.SuccessEvent -> {

                    val data = Resource.Success(
                        data = it.data?.data!!.memes.map { testData ->
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