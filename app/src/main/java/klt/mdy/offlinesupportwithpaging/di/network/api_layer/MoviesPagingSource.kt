package klt.mdy.offlinesupportwithpaging.di.network.api_layer

import androidx.paging.PagingSource
import androidx.paging.PagingState
import klt.mdy.offlinesupportwithpaging.model.MovieEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val apiService: MovieApiService
) : PagingSource<Int, MovieEntity>() {

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {

        val currentPage = params.key ?: 1

        return try {
            val response = apiService.fetchMovies(
                page = currentPage,
            )
            val pageResponse = response.body()?.results?.map {
                it.toVo()
            }
            val endOfPaginationReached = pageResponse.isNullOrEmpty()
            if (endOfPaginationReached) {
                LoadResult.Page(
                    data = pageResponse.orEmpty(),
                    prevKey = null, //nextPage ( we use forward only )prevPage,
                    nextKey = null
                )
            } else {
                LoadResult.Page(
                    data = pageResponse.orEmpty(),
                    prevKey = null, //nextPage ( we use forward only )prevPage,
                    nextKey = currentPage + 1
                )
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}