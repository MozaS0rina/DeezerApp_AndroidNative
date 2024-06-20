package com.example.Deezer.screens.album_screen.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LikedTrackEntity::class], version = 1)
abstract class LikedTrackDatabase : RoomDatabase() {

    abstract fun getLikedTrackDao(): LikedTrackDao

    companion object {
        @Volatile//alte threaduri pot sa vada si datele dar si efectele secundare
        private var instance: LikedTrackDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): LikedTrackDatabase = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LikedTrackDatabase::class.java,
                "liked_track_database.db"
            ).build()
    }
}