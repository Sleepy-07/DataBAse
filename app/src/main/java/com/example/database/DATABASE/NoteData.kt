package com.example.database.DATABASE


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    val Title: String? = null,
    val Desc: String? = null,
)
