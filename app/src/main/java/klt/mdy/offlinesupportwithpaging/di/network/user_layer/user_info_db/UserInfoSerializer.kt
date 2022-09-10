package klt.mdy.offlinesupportwithpaging.di.network.user_layer.user_info_db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import klt.mdy.offlinesupportwithpaging.model.user.ProfileInfoVo
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object UserInfoSerializer : Serializer<ProfileInfoVo> {
    override val defaultValue: ProfileInfoVo
        get() = ProfileInfoVo()

    override suspend fun readFrom(input: InputStream): ProfileInfoVo {
        return try {
            Json.decodeFromString(
                deserializer = ProfileInfoVo.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: ProfileInfoVo, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ProfileInfoVo.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }

    val Context.userInfoDataStore: DataStore<ProfileInfoVo> by dataStore(
        fileName = "userInfo.pb",
        serializer = UserInfoSerializer
    )
}