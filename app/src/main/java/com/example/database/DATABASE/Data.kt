package com.example.database.DATABASE


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [NoteData::class], version = 2, exportSchema = true)
abstract class Data() : RoomDatabase(){
        abstract fun inter(): Interface



        companion object{
            private var data : Data?= null


            fun getInstance(context: Context) : Data{
            if(data==null){
                data= Room.databaseBuilder(context
                ,Data::class.java,
                    "Data-Base")
                    .allowMainThreadQueries()
                    .build()
            }

                return data!!
            }
        }

}