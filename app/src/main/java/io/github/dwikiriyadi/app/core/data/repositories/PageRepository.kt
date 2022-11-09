package io.github.dwikiriyadi.app.core.data.repositories

import io.github.dwikiriyadi.app.core.data.sources.dao.PageDao
import io.github.dwikiriyadi.app.core.objects.entity.Page

class PageRepository private constructor(private val pageDao: PageDao){

    suspend fun all(id: Long) = pageDao.all(id)

    suspend fun get(id: Long) = pageDao.get(id)

    suspend fun insert(page: Page) = pageDao.insert(page)

    suspend fun delete(page: Page) = pageDao.delete(page)

    suspend fun update(page: Page) = pageDao.update(page)

    companion object {
        @Volatile private var instance: PageRepository? = null

        fun getInstance(pageDao: PageDao) = instance ?: synchronized(this) {
            instance ?: PageRepository(pageDao).also { instance = it }
        }
    }
}