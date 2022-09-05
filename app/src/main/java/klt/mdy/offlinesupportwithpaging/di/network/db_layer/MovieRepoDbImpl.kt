package klt.mdy.offlinesupportwithpaging.di.network.db_layer

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import klt.mdy.offlinesupportwithpaging.data.local.MovieDatabase
import klt.mdy.offlinesupportwithpaging.di.network.Constants
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.MovieApiService
import klt.mdy.offlinesupportwithpaging.model.MovieEntity
import klt.mdy.offlinesupportwithpaging.paging.OfflineResourceUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepoDbImpl @Inject constructor(
    private val api: MovieApiService,
    private val db: MovieDatabase
) : MovieDbRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMovies(): Flow<PagingData<MovieEntity>> {
        val pagingSourceFactory = { db.movieDao().getAllMovies() }
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEM_PER_PAGE),
            remoteMediator = OfflineResourceUseCase(
                api = api,
                db = db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}