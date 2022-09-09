package klt.mdy.offlinesupportwithpaging.ui.udf

import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity

sealed class MovieAction {
    data class ClickMovieItem(val movieEntity: MovieEntity) : MovieAction()
    object ClickMeMeItem : MovieAction()
    object ClickMore : MovieAction()
    object ClickDownload : MovieAction()
   }
