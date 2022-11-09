package io.github.dwikiriyadi.myapp.core.data.repositories

import io.github.dwikiriyadi.myapp.core.data.sources.dao.SectionDao
import io.github.dwikiriyadi.myapp.core.objects.entity.Section

class SectionRepository private constructor(private val sectionDao: SectionDao){

    suspend fun all() = sectionDao.all()

    suspend fun insert(section: Section) = sectionDao.insert(section)

    suspend fun delete(section: Section) = sectionDao.delete(section)

    suspend fun update(section: Section) = sectionDao.update(section)

    companion object {
        @Volatile private var instance: SectionRepository? = null

        fun getInstance(sectionDao: SectionDao) = instance ?: synchronized(this) {
            instance ?: SectionRepository(sectionDao).also { instance = it }
        }
    }
}