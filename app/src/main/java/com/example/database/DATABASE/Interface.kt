package com.example.database.DATABASE

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Interface {

    @Insert
    fun InsertTODO(todo : NoteData)

    @Query("SELECT * FROM NoteData")
    fun GetList() :List<NoteData>

    @Update
    fun Update(todo : NoteData)

    @Delete
    fun Delete(todo :NoteData)
}