package klt.mdy.offlinesupportwithpaging.di.network.api_layer

import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.di.network.Constants
import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoDTO
import klt.mdy.offlinesupportwithpaging.model.movie.MoviesDTO
import klt.mdy.offlinesupportwithpaging.model.user.LanguageDTO
import retrofit2.Response
import retrofit2.http.*

interface MovieApiService {

    @GET(Endpoints.MOVIES_UPCOMING)
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String = Endpoints.MOVIE_API_KEY,
        @Query("page") page: Int
    ): Response<MoviesDTO>

    @GET(Endpoints.MOVIES_POPULAR)
    suspend fun fetchMoviePageOne(
        @Query("api_key") apiKey: String = Endpoints.MOVIE_API_KEY,
        @Query("page") page: Int = 1
    ): Response<MoviesDTO>

    @GET(Endpoints.UPCOMING)
    suspend fun fetchMovie(
        @Query("api_key") apiKey: String = "cdbea55de27a909b4aaa2cfc02eabb75",
        @Query("page") page: Int,
        @Query("load_size") loadSize: Int = Constants.LOAD_SIZE,
    ): MoviesDTO
}

interface UserService {
    @FormUrlEncoded
    @POST(Endpoints.PROFILE_INFO)
    suspend fun getProfileInfo(
        @Field("userId") userId: Long,
        @Field("locale") locale: Int
    ): Response<ProfileInfoDTO>

    @GET(Endpoints.LANGUAGES)
    suspend fun requestLanguages(): Response<LanguageDTO>
}
