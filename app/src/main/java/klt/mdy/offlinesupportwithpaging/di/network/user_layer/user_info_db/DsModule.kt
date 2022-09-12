package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import klt.mdy.offlinesupportwithpaging.di.network.Constants
import klt.mdy.offlinesupportwithpaging.di.network.QualifiedAnnotation
import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoVo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DsModule {
    @Provides
    @Singleton
    @QualifiedAnnotation.UserPref
    fun provideUserPref(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(Constants.USER_PREF_NAME)
            }
        )
    }

    @Provides
    @Singleton
    @QualifiedAnnotation.UserProto
    fun provideUserProto(
        @ApplicationContext context: Context,
        @QualifiedAnnotation.Io io: CoroutineDispatcher
    ): DataStore<ProfileInfoVo> {
        return DataStoreFactory.create(
            produceFile = { context.dataStoreFile(fileName = Constants.USER_PROTO_NAME) },
            serializer = UserInfoSerializer,
            corruptionHandler = null,
            scope = CoroutineScope(io + SupervisorJob()),
        )
    }

    @Provides
    @Singleton
    fun provideUserDataStoreSource(
        @QualifiedAnnotation.UserPref ds: DataStore<Preferences>
    ): UserDataStore {
        return UserDataStoreImpl(ds = ds)
    }

    @Provides
    @Singleton
    fun provideUserInfoDataStoreSource(
        @QualifiedAnnotation.UserProto ds: DataStore<ProfileInfoVo>,
        @QualifiedAnnotation.Io io: CoroutineDispatcher
    ): UserInfoDataStore {
        return UserInfoDataStoreImpl(ds = ds, io = io)
    }
}