package klt.mdy.offlinesupportwithpaging.di.network.api_layer

import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.model.test.MemeVos
import klt.mdy.offlinesupportwithpaging.model.test.TestDTO
import kotlinx.coroutines.flow.Flow

interface AppApiRepository {
    suspend fun memeApi(
    ): Flow<RemoteResource<TestDTO>>

    suspend fun saveMemeList(memeList: List<MemeVos>)

    suspend fun retrieveMeMeList(): Flow<List<MemeVos>>

}
