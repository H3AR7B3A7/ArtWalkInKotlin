package be.dog.d.steven.artwalkinkotlin.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import be.dog.d.steven.artwalkinkotlin.model.ArtWork

object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
            migration12()
        )

    private fun migration12(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${ArtWork.TABLE_NAME} ADD COLUMN body TEXT")
        }
    }
}