package klt.mdy.offlinesupportwithpaging.ui.movie_udf

import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity

sealed class MovieEvent {
    object ShowDownloadSheet : MovieEvent()

    data class ShowSnack(val message: String) : MovieEvent()
    data class NavigateToMovieDetail(val movieEntity: MovieEntity) : MovieEvent()
}
