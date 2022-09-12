package klt.mdy.offlinesupportwithpaging.di.network

import android.content.Context
import androidx.room.Room
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import klt.mdy.offlinesupportwithpaging.BuildConfig
import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.data.local.MovieDatabase
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.AppApiService
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.MovieApiService
import klt.mdy.offlinesupportwithpaging.di.network.api_layer.UserService
import klt.mdy.offlinesupportwithpaging.di.network.db_layer.MovieDbRepository
import klt.mdy.offlinesupportwithpaging.di.network.db_layer.MovieRepoDbImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthApiService(
        @QualifiedAnnotation.AppRetrofit appRetrofit: Retrofit
    ): AppApiService {
        return appRetrofit.create(AppApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesApiService(
        @QualifiedAnnotation.MoviesRetrofit moviesRetrofit: Retrofit
    ): MovieApiService {
        return moviesRetrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApiService(
        @QualifiedAnnotation.UserRetrofit userRetrofit: Retrofit
    ): UserService {
        return userRetrofit.create(UserService::class.java)
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    // db
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            Constants.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesRepository(
        api: MovieApiService,
        db: MovieDatabase
    ): MovieDbRepository {
        return MovieRepoDbImpl(
            api = api,
            db = db
        )
    }


////////////////////////////////////////////////////////////////////////////////////////////////////

    // meme base url
    @Provides
    @Singleton
    @QualifiedAnnotation.AppRetrofit
    fun provideAppRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.RESTFUL_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    // movie base url
    @Provides
    @Singleton
    @QualifiedAnnotation.MoviesRetrofit
    fun provideMoviesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.MOVIE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    // chat app base url
    @Provides
    @Singleton
    @QualifiedAnnotation.UserRetrofit
    fun provideUserRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.USER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            //this is for logging profiler
            OkHttpClient.Builder()
                .addInterceptor(OkHttpProfilerInterceptor())
                .addNetworkInterceptor(OkHttpProfilerInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

        } else OkHttpClient
            .Builder()
            .build()
    }
}

