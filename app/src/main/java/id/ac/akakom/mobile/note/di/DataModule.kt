package id.ac.akakom.mobile.note.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.ac.akakom.mobile.note.data.AppDatabase

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {
    @Provides
    fun proviceDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getInstance(context)
    }
}