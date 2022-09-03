package klt.mdy.offlinesupportwithpaging.graph

object Routes {
    const val ROOT_ROUTE = "root"
    const val MOVIE_ROUTE = "movie"
}

object ArgsConstants {
    const val MOVIE_VO = "movieVo"
}

sealed class AppDestination(val route: String) {
    object Splash : AppDestination(route = "splash_screen")
    object Movie : AppDestination(route = "movie_screen")
    object MovieDetail : AppDestination(route = "movie_detail_screen")
}