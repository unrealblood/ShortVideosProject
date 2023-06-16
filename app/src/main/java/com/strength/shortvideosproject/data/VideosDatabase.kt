package com.strength.shortvideosproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseVideo::class], version = 1)
abstract class VideosDatabase: RoomDatabase() {
    abstract val databaseDao : DatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE : VideosDatabase? = null

        fun getDatabase(context : Context) : VideosDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, VideosDatabase::class.java,  "videos_database")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}