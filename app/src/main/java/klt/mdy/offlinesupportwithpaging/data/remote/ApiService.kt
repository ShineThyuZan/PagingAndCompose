package klt.mdy.offlinesupportwithpaging.data.remote

import klt.mdy.offlinesupportwithpaging.common.Constants
import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.model.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Endpoints.UPCOMING)
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String = "cdbea55de27a909b4aaa2cfc02eabb75",
        @Query("page") page: Int,
        @Query("load_size") loadSize: Int = Constants.LOAD_SIZE,
    ): MoviesDTO
}