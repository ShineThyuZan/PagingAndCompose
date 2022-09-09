package klt.mdy.offlinesupportwithpaging.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.*
import klt.mdy.offlinesupportwithpaging.di.network.user_layer.UserApiRepository
import klt.mdy.offlinesupportwithpaging.di.network.user_layer.UserApiRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    //todo next repo  add in this scope

    @Provides
    @Singleton
    fun provideAuthApiRepository(
        api: AppApiService,
        @QualifiedAnnotation.Io io: CoroutineDispatcher,
    ): AppApiRepository {
        return AppApiRepositoryImpl(
            api = api,
            io = io,
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovieApiService,
        @QualifiedAnnotation.Io io: CoroutineDispatcher
    ): MovieRepository {
        return MovieRepositoryImpl(
            api = api,
            io = io
        )
    }

    @Provides
    @Singleton
    fun provideUserApiRepository(
        api: UserService,
        @QualifiedAnnotation.Io io: CoroutineDispatcher
    ): UserApiRepository {
        return UserApiRepositoryImpl(
            api = api,
            io = io
        )
    }

}