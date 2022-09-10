package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import klt.mdy.offlinesupportwithpaging.di.network.QualifiedAnnotation
import javax.inject.Inject

open class UserDataStoreImpl @Inject constructor(
    @QualifiedAnnotation.UserPref private val ds: DataStore<Preferences>
) : UserDataStore {

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("com.galaxytechno.user.access_token")
        val REFRESH_TOKEN = stringPreferencesKey("com.galaxytechno.user.refresh_token")
    }

    override suspend fun clearUserDs() {
        ds.edit {
            it.clear()
        }
    }
}
