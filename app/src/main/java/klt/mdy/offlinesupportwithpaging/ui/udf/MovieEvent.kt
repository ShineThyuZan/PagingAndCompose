package klt.mdy.offlinesupportwithpaging.ui.udf

import klt.mdy.offlinesupportwithpaging.model.MovieEntity

sealed class MovieEvent {
    data class ShowSnack(val message: String) : MovieEvent()
    data class NavigateToMovieDetail(val movieEntity: MovieEntity) : MovieEvent()
    object ShowDownloadSheet : MovieEvent()
}
