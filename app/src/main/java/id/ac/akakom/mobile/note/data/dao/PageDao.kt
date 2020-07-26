package id.ac.akakom.mobile.note.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.akakom.mobile.note.data.model.Page

@Dao
interface PageDao {

    @Query("SELECT * FROM pages WHERE section_id = :id")
    suspend fun all(id: Long): List<Page>

    @Query("SELECT * FROM pages WHERE id = :id")
    suspend fun get(id: Long): Page

    @Insert
    suspend fun insert(page: Page): Long

    @Delete
    suspend fun delete(page: Page)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(page: Page)
}