package klt.mdy.offlinesupportwithpaging.di.network.api_layer

import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.model.test.TestDTO
import retrofit2.Response
import retrofit2.http.GET

interface AppApiService {
    @GET(Endpoints.TEST_URL)
    suspend fun requestCountry(): Response<TestDTO>
}