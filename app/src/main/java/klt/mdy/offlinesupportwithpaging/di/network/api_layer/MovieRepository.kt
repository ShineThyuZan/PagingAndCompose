package klt.mdy.offlinesupportwithpaging.di.network.api_layer

import androidx.paging.PagingData
import klt.mdy.offlinesupportwithpaging.di.network.RemoteResource
import klt.mdy.offlinesupportwithpaging.model.MovieEntity
import klt.mdy.offlinesupportwithpaging.model.MoviesDTO
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    // get Movie data form api
    fun getMoviesFromApi(): Flow<PagingData<MovieEntity>>

    suspend fun getMoviesPageOne(): Flow<RemoteResource<MoviesDTO>>

    suspend fun getConversationHistories(
        page: Int,
        loadSize: Int
    ): RemoteResource<List<MovieEntity>>
}