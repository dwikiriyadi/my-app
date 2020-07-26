package id.ac.akakom.mobile.note.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import id.ac.akakom.mobile.note.data.AppDatabase
import id.ac.akakom.mobile.note.data.repository.PageRepository
import id.ac.akakom.mobile.note.data.repository.SectionRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    fun providePageRepository(appDatabase: AppDatabase): PageRepository {
        return PageRepository.getInstance(appDatabase.pageDao())
    }
    @Provides
    fun provideSectionRepository(appDatabase: AppDatabase) : SectionRepository {
        return SectionRepository.getInstance(appDatabase.sectionDao())
    }
}