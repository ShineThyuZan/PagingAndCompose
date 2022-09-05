package klt.mdy.offlinesupportwithpaging.di.network

import kotlinx.coroutines.flow.Flow

interface AuthDataStore {

    suspend fun putAuthFlag(isLoggedIn: Boolean)
    suspend fun pullAuthFlag(): Flow<Boolean>

    suspend fun putLanguage(status: Int)
    suspend fun pullLanguage(): Flow<Int>

    suspend fun removeAuthFlag()
    suspend fun removeLanguage()

    suspend fun putUserId(id: Long)
    suspend fun pullUserId(): Flow<Long?>

    suspend fun clearAuthDs()

}