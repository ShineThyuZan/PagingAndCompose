package klt.mdy.offlinesupportwithpaging.ui.udf

import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity
import klt.mdy.offlinesupportwithpaging.model.test.DataObjVos

sealed class MovieEvent {
    data class ShowSnack(val message: String) : MovieEvent()
    data class NavigateToMovieDetail(val movieEntity: MovieEntity) : MovieEvent()
    object NavigateToUserProfile : MovieEvent()

    object ShowDownloadSheet : MovieEvent()

    val languages: Resource<List<DataObjVos>> = Resource.Loading()
}
