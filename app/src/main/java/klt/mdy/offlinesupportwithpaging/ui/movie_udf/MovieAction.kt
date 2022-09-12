package klt.mdy.offlinesupportwithpaging.ui.movie_udf

import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity

sealed class MovieAction {
    // detail
    object ClickMore : MovieAction()
    object ClickDownload : MovieAction()

    // movie
    data class ClickMovieItem(val movieEntity: MovieEntity) : MovieAction()

   }
