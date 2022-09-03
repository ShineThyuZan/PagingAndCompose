package klt.mdy.offlinesupportwithpaging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import klt.mdy.offlinesupportwithpaging.domain.Repository
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieAction
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {
    val movies = repository.getMovies().cachedIn(viewModelScope)

    private val _movieEvent = MutableSharedFlow<MovieEvent>()
    val movieEvent : SharedFlow<MovieEvent> get() = _movieEvent

    // action
    fun onMovieAction(action : MovieAction){
        when(action){
            is MovieAction.ClickMovieItem ->{
                viewModelScope.launch {
                    _movieEvent.emit(MovieEvent.NavigateToMovieDetail( action.movieEntity))
                    _movieEvent.emit(MovieEvent.ShowSnack(message = action.movieEntity.toString()))
                }
            }
        }
    }
}