package klt.mdy.offlinesupportwithpaging.component.movie

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import klt.mdy.offlinesupportwithpaging.component.*
import klt.mdy.offlinesupportwithpaging.graph.AppDestination
import klt.mdy.offlinesupportwithpaging.graph.ArgsConstants
import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity
import klt.mdy.offlinesupportwithpaging.model.movie.MovieParcel
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel
import klt.mdy.offlinesupportwithpaging.ui.movie_udf.MovieAction
import klt.mdy.offlinesupportwithpaging.ui.movie_udf.MovieEvent
import kotlinx.coroutines.flow.collectLatest


@Composable
fun MovieScreen(
    vm: MainViewModel,
    navController: NavController
) {
    MoviesContent(
        navController = navController,
        vm = vm
    )
}

@Composable
fun MoviesContent(
    navController: NavController,
    vm: MainViewModel,
) {
    val movies: LazyPagingItems<MovieEntity> = vm.movies.collectAsLazyPagingItems()
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        vm.movieEvent.collectLatest {
            when (it) {
                is MovieEvent.ShowSnack -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                }
                is MovieEvent.NavigateToMovieDetail -> {
                    val movieParcel = MovieParcel(
                        originalTitle = it.movieEntity.originalTitle,
                        movieTitle = it.movieEntity.movieTitle,
                        coverUrl = it.movieEntity.coverUrl,
                        posterUrl = it.movieEntity.posterUrl,
                        overview = it.movieEntity.overview
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        ArgsConstants.MOVIE_VO,
                        movieParcel
                    )
                    navController.navigate(AppDestination.TestApi.route)
                    vm.getMeMEApi()
                }
                MovieEvent.ShowDownloadSheet -> {}
            }
        }
    }
    Surface {
        Scaffold(scaffoldState = scaffoldState) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp),
                content = {
                    itemsIndexed(items = movies) { index, value ->
                        MovieItem(
                            movie = value ?: MovieEntity(),
                            onItemClick = { movieEntity ->
                                vm.onMovieAction(MovieAction.ClickMovieItem(movieEntity = movieEntity))
                            })
                        if (index < movies.itemCount - 1) {
                            Spacer(modifier = Modifier.height(8.dp))
                            SheetHeader()
                        }
                    }
                    movies.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item {
                                    ShimmerView()
                                }
                            }
                            loadState.append is LoadState.Loading -> {
                                item {
                                    LoadingView()
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                val e = movies.loadState.refresh as LoadState.Error
                                item {
                                    RetryView(
                                        message = e.error.localizedMessage ?: "Error",
                                        onClickRetry = { retry() }
                                    )
                                    ShimmerView()
                                }
                            }
                            loadState.append is LoadState.Error -> {
                                val e = movies.loadState.append as LoadState.Error
                                item {
                                    RetryView(
                                        message = e.error.localizedMessage ?: "Error",
                                        onClickRetry = { retry() }
                                    )
                                }
                            }
                            loadState.append.endOfPaginationReached -> {
                                if (movies.itemCount == 0) {
                                    item {
                                        EmptyView()
                                    }
                                } else {
                                    item {
                                        EndOfPaginationView()
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}
