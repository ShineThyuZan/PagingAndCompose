package klt.mdy.offlinesupportwithpaging.graph

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import klt.mdy.offlinesupportwithpaging.component.movie.MovieScreen
import klt.mdy.offlinesupportwithpaging.component.movie_detail.MovieDetailScreen
import klt.mdy.offlinesupportwithpaging.model.MovieParcel
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel
import timber.log.Timber

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.movieNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = AppDestination.Movie.route,
        route = Routes.MOVIE_ROUTE
    ) {

        composable(route = AppDestination.Movie.route) {
            val parentEntry = remember {
                navController.getBackStackEntry(Routes.MOVIE_ROUTE)
            }
            val movieViewModel = hiltViewModel<MainViewModel>(parentEntry)

            MovieScreen(navController = navController, vm = movieViewModel)
        }

        composable(
            route = AppDestination.MovieDetail.route,
        ) {
            val parentEntry = remember { navController.getBackStackEntry(Routes.MOVIE_ROUTE) }
            val vm = hiltViewModel<MainViewModel>(parentEntry)
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<MovieParcel>(
                    ArgsConstants.MOVIE_VO
                )
            MovieDetailScreen(
                navController = navController,
                movieParcel = result ?: MovieParcel(),
                vm = vm
            )
        }

    }
}