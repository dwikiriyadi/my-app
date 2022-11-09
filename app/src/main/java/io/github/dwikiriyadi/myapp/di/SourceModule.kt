package io.github.dwikiriyadi.myapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.dwikiriyadi.myapp.core.data.sources.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {
    @Provides
    fun proviceDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}