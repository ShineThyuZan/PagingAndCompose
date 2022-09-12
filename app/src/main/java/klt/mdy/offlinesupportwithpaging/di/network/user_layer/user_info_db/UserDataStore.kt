package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

interface UserDataStore {
    suspend fun clearUserDs()
}