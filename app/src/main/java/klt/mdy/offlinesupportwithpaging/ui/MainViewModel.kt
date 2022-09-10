package klt.mdy.offlinesupportwithpaging.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.di.network.db_layer.MovieDbRepository
import klt.mdy.offlinesupportwithpaging.di.network.user_layer.GetProfileInfoUseCase
import klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db.MainUseCase
import klt.mdy.offlinesupportwithpaging.domain.TestApiData
import klt.mdy.offlinesupportwithpaging.domain.TestApiUseCase
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieAction
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieEvent
import klt.mdy.offlinesupportwithpaging.ui.udf.UserState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    /*  private val useCase: TestApiUseCase,
      private val movieUseCase: GetMovieListUseCase,
      repository: MovieRepository,*/

    private val useCase: TestApiUseCase,
    private val userUserCase: GetProfileInfoUseCase,
    private val mainUseCase: MainUseCase,


    repoDb: MovieDbRepository,

    ) : ViewModel() {

    // this db data form repo
    val movies = repoDb.getMovies().cachedIn(viewModelScope)

    // this data form useCase
    /*val movies = movieUseCase().cachedIn(viewModelScope)*/

    private val _testApiData: MutableState<TestApiData> = mutableStateOf(TestApiData())
    val testApiData: State<TestApiData> get() = _testApiData

    private val _movieEvent = MutableSharedFlow<MovieEvent>()
    val movieEvent: SharedFlow<MovieEvent> get() = _movieEvent

    private val _userState = mutableStateOf(UserState())
    val userState: State<UserState> get() = _userState

    init {
      //  getTestApi()
        // getUserFromApi()
        getUserFromDb()
    }

    // action
    fun onMovieAction(action: MovieAction) {
        when (action) {

            // movie list action
            is MovieAction.ClickMovieItem -> {
                viewModelScope.launch {
                    _movieEvent.emit(MovieEvent.NavigateToMovieDetail(action.movieEntity))
                    _movieEvent.emit(MovieEvent.ShowSnack(message = action.movieEntity.toString()))
                }
            }
            // detail screen action
            MovieAction.ClickDownload -> {
                viewModelScope.launch {
                    _movieEvent.emit(MovieEvent.ShowSnack(message = "Saved"))
                }
            }
            MovieAction.ClickMore -> {
                viewModelScope.launch {
                    _movieEvent.emit(
                        MovieEvent.ShowDownloadSheet
                    )
                }
            }
            MovieAction.ClickMeMeItem -> {
                viewModelScope.launch {
                    _movieEvent.emit(
                        MovieEvent.NavigateToUserProfile
                    )
                }
            }
        }
    }

    // this form db  , get api form server
   private fun getUserFromDb() {
        viewModelScope.launch {
            mainUseCase.getUserFromDb().collect {
                _userState.value = userState.value.copy(
                    form = userState.value.form.copy(
                        profileInfo = it
                    )
                )
            }
        }
        getUserFromApi()
    }


    private fun getUserFromApi() {
        viewModelScope.launch {
            userUserCase().collect {
                when (it) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val user = it.data!!
                        _userState.value = userState.value.copy(
                            form = userState.value.form.copy(
                                profileInfo = user
                            )
                        )
                        Timber.tag("profileInformation")
                            .d(userState.value.form.profileInfo.username)
                        //setUserToDb(profileInfo = user)
                    }
                }
            }
        }
    }


    //request from init
     fun getTestApi() {
        viewModelScope.launch {
            _testApiData.value = testApiData.value.copy(
                languages = Resource.Loading()
            )
            useCase.invoke().collect {
                _testApiData.value = testApiData.value.copy(
                    languages = it
                )
            }
        }
    }
}