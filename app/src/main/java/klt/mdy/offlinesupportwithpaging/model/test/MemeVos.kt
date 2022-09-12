package klt.mdy.offlinesupportwithpaging.model.test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MeMeListTable")
data class MemeVos(
    @PrimaryKey val id: String,
    val name: String,
    val url: String,
    val width: Int,
    val height: Int,
    val box_count: Int
)