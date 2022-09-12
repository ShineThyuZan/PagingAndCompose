package klt.mdy.offlinesupportwithpaging.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity
import klt.mdy.offlinesupportwithpaging.model.movie.RemoteKeyEntity
import klt.mdy.offlinesupportwithpaging.model.test.MemeVos

@Database(
    entities = [MovieEntity::class, RemoteKeyEntity::class, MemeVos::class],
    version = 5
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun MeMeListDao(): MeMeListDao
}