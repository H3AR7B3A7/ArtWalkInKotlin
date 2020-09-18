package be.dog.d.steven.artwalkinkotlin.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.dog.d.steven.artwalkinkotlin.data.local.dao.ArtWorkDAO
import be.dog.d.steven.artwalkinkotlin.model.ArtWork

@Database(entities = [ArtWork::class], version = DatabaseMigrations.DB_VERSION)
abstract  class ArtWorkDB: RoomDatabase(){

    abstract fun getPostsDao(): ArtWorkDAO

    companion object {
        const val DB_NAME = "artwork_database"

        @Volatile
        private var INSTANCE: ArtWorkDB? = null

        fun getInstance(context: Context): ArtWorkDB {
            val tempInstance= INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtWorkDB::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}