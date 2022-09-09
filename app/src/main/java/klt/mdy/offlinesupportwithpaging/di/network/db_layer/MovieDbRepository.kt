package klt.mdy.offlinesupportwithpaging.di.network.db_layer

import androidx.paging.PagingData
import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieDbRepository {
    fun getMovies(): Flow<PagingData<MovieEntity>>
}