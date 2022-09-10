package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

import javax.inject.Inject

data class MainUseCase @Inject constructor(

    val getUserFromDb: GetUserUseCase,
)