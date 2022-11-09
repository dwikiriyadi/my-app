package io.github.dwikiriyadi.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.dwikiriyadi.app.core.data.sources.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {
    @Provides
    fun proviceDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}