package klt.mdy.offlinesupportwithpaging.ui.meme_udf

import klt.mdy.offlinesupportwithpaging.ui.movie_udf.MovieAction

sealed class MemeAction {
    // meme
    object ClickMeMeItem : MemeAction()
}