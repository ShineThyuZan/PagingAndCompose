package klt.mdy.offlinesupportwithpaging.ui.udf

import klt.mdy.offlinesupportwithpaging.model.MovieEntity

sealed class MovieAction {
    data class ClickMovieItem(val movieEntity: MovieEntity) : MovieAction()
    object ClickMore : MovieAction()
    object ClickDownload : MovieAction()
   }
