package id.ac.akakom.mobile.note.data.repository

import id.ac.akakom.mobile.note.data.dao.SectionDao
import id.ac.akakom.mobile.note.data.model.Section

class SectionRepository private constructor(private val sectionDao: SectionDao){

    fun all() = sectionDao.all()

    fun insert(section: Section) = sectionDao.insert(section)

    fun delete(section: Section) = sectionDao.delete(section)

    fun update(section: Section) = sectionDao.update(section)


    companion object {
        @Volatile private var instance: SectionRepository? = null

        fun getInstance(sectionDao: SectionDao) = instance ?: synchronized(this) {
            instance ?: SectionRepository(sectionDao).also { instance = it }
        }
    }
}