package klt.mdy.offlinesupportwithpaging.di.network.api_layer


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.di.network.*
import klt.mdy.offlinesupportwithpaging.model.MovieEntity
import klt.mdy.offlinesupportwithpaging.model.MoviesDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApiService,
    @QualifiedAnnotation.Io private val io: CoroutineDispatcher,
) : MovieRepository {

    private val config = PagingConfig(
        pageSize = Constants.LOAD_SIZE,
        enablePlaceholders = false,
    )

    override fun getMoviesFromApi(): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = config,
            pagingSourceFactory = {
                MoviesPagingSource(
                    apiService = api
                )
            }
        ).flow
    }

    override suspend fun getMoviesPageOne(): Flow<RemoteResource<MoviesDTO>> {
        return flow {

            emit(
                safeApiCall {
                    api.fetchMoviePageOne(
                        apiKey = Endpoints.MOVIE_API_KEY
                    )
                }
            )
        }.flowOn(io)
    }

    override suspend fun getConversationHistories(
        page: Int,
        loadSize: Int
    ): RemoteResource<List<MovieEntity>> {
        val result = safeApiCall {
            api.fetchMovies(page = page)
        }
        return when (result) {
            is RemoteResource.ErrorEvent -> {
                RemoteResource.ErrorEvent(
                    errorMessage = result.message ?: "error"
                )
            }
            is RemoteResource.LoadingEvent -> {
                RemoteResource.LoadingEvent()
            }
            is RemoteResource.SuccessEvent -> {
                RemoteResource.SuccessEvent(data = result.data!!.results.map { it.toVo() })
            }
        }
    }


}