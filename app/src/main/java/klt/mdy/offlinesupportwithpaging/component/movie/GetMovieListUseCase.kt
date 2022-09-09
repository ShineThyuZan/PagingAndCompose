package klt.mdy.offlinesupportwithpaging.component.movie

import androidx.paging.PagingData
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.MovieRepository
import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<MovieEntity>> {
        return movieRepository.getMoviesFromApi()
    }
}