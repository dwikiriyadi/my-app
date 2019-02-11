package id.ac.akakom.mobile.note.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.ac.akakom.mobile.note.data.dao.PageDao
import id.ac.akakom.mobile.note.data.dao.SectionDao
import id.ac.akakom.mobile.note.data.model.*
import id.ac.akakom.mobile.note.utility.Converters

@Database(entities = [Section::class, Page::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun sectionDao(): SectionDao
    abstract fun pageDao(): PageDao

    companion object {
        private const val DATABASE_NAME = "database-note"

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}