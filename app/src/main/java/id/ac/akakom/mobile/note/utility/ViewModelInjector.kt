package id.ac.akakom.mobile.note.utility

import android.content.Context
import id.ac.akakom.mobile.note.data.AppDatabase
import id.ac.akakom.mobile.note.data.repository.*
import id.ac.akakom.mobile.note.ui.page.PageViewModelFactory
import id.ac.akakom.mobile.note.ui.section.SectionViewModelFactory
import id.ac.akakom.mobile.note.ui.write.WriteViewModelFactory

object ViewModelInjector {

    private fun getSectionRepository(context: Context): SectionRepository {
        return SectionRepository.getInstance(AppDatabase.getInstance(context).sectionDao())
    }

    private fun getPageRepository(context: Context): PageRepository {
        return PageRepository.getInstance(AppDatabase.getInstance(context).pageDao())
    }

    fun provideSectionViewModelFactory(context: Context): SectionViewModelFactory {
        val repository = getSectionRepository(context)
        return SectionViewModelFactory(repository)
    }

    fun providePageViewModelFactory(context: Context): PageViewModelFactory {
        val repository = getPageRepository(context)
        return PageViewModelFactory(repository)
    }

    fun provideWriteViewModelFactory(context: Context): WriteViewModelFactory {
        val repository = getPageRepository(context)
        return WriteViewModelFactory(repository)
    }
}