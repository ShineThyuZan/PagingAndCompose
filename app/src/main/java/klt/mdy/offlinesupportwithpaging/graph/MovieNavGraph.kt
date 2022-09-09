package klt.mdy.offlinesupportwithpaging.graph

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import klt.mdy.offlinesupportwithpaging.component.test.MemeScreen
import klt.mdy.offlinesupportwithpaging.component.movie.MovieScreen
import klt.mdy.offlinesupportwithpaging.component.userprofile.UserInfoScreen
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel

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
            //    LanguageScreen(vm = movieViewModel, navController = navController)
        }

        composable(route = AppDestination.TestApi.route) {
            val parentEntry = remember {
                navController.getBackStackEntry(Routes.MOVIE_ROUTE)
            }
            val movieViewModel = hiltViewModel<MainViewModel>(parentEntry)

            MemeScreen(navController = navController, vm = movieViewModel)
            //    LanguageScreen(vm = movieViewModel, navController = navController)
        }


        composable(route = AppDestination.UserInfo.route) {
            val parentEntry = remember {
                navController.getBackStackEntry(Routes.MOVIE_ROUTE)
            }
            val movieViewModel = hiltViewModel<MainViewModel>(parentEntry)

            UserInfoScreen(navController = navController, vm = movieViewModel)
        }


        // this parcelable data navigation
        /*composable(
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
          }*/

    }
}