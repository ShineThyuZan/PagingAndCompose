package klt.mdy.offlinesupportwithpaging.di.network.api_layer

import klt.mdy.offlinesupportwithpaging.di.network.QualifiedAnnotation
import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.di.network.safeApiCall
import klt.mdy.offlinesupportwithpaging.model.TestDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppApiRepositoryImpl @Inject constructor(
    private val api: AppApiService,
    @QualifiedAnnotation.Io private val io: CoroutineDispatcher,
) : AppApiRepository {
    override suspend fun testApi(): Flow<RemoteResource<TestDTO>> {
        return flow {
            emit(
                safeApiCall {
                    api.requestCountry()
                }
            )
        }.flowOn(io)
    }
}