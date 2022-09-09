package klt.mdy.offlinesupportwithpaging.di.network.api_layer

import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.model.test.TestDTO
import kotlinx.coroutines.flow.Flow


interface AppApiRepository {
    suspend fun testApi(
    ): Flow<RemoteResource<TestDTO>>
}
