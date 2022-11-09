package io.github.dwikiriyadi.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.github.dwikiriyadi.app.core.data.repositories.PageRepository
import io.github.dwikiriyadi.app.core.data.repositories.SectionRepository
import io.github.dwikiriyadi.app.core.data.sources.AppDatabase

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