package klt.mdy.offlinesupportwithpaging.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import klt.mdy.offlinesupportwithpaging.model.movie.MovieEntity
import klt.mdy.offlinesupportwithpaging.model.movie.RemoteKeyEntity

@Database(
    entities = [MovieEntity::class, RemoteKeyEntity::class],
    version = 4
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}