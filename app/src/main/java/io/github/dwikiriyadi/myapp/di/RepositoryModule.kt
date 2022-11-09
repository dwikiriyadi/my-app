package io.github.dwikiriyadi.myapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.github.dwikiriyadi.myapp.core.data.repositories.PageRepository
import io.github.dwikiriyadi.myapp.core.data.repositories.SectionRepository
import io.github.dwikiriyadi.myapp.core.data.sources.AppDatabase

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    fun providePageRepository(appDatabase: AppDatabase): PageRepository {
        return PageRepository.getInstance(appDatabase.pageDao())
    }

    @Provides
    fun provideSectionRepository(appDatabase: AppDatabase): SectionRepository {
        return SectionRepository.getInstance(appDatabase.sectionDao())
    }
}