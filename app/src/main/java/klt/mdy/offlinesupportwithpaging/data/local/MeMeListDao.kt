package klt.mdy.offlinesupportwithpaging.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import klt.mdy.offlinesupportwithpaging.model.test.MemeVos
import kotlinx.coroutines.flow.Flow

@Dao
interface MeMeListDao {
    @Insert
    suspend fun inputMeme(meme: MemeVos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inputMemeList(meme: List<MemeVos>)

    @Query("SELECT * FROM MeMeListTable")
    fun outputMemes(): Flow<List<MemeVos>>
}