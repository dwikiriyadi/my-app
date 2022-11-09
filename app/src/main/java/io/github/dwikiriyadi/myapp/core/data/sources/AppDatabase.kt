package io.github.dwikiriyadi.myapp.core.data.sources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.dwikiriyadi.myapp.core.common.Converters
import io.github.dwikiriyadi.myapp.core.data.sources.dao.PageDao
import io.github.dwikiriyadi.myapp.core.data.sources.dao.SectionDao
import io.github.dwikiriyadi.myapp.core.objects.entity.Page
import io.github.dwikiriyadi.myapp.core.objects.entity.Section

@Database(entities = [Section::class, Page::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sectionDao(): SectionDao
    abstract fun pageDao(): PageDao

    companion object {
        private const val DATABASE_NAME = "database-note"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}