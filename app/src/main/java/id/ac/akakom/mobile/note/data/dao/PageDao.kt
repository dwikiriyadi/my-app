package id.ac.akakom.mobile.note.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.akakom.mobile.note.data.model.Page

@Dao
interface PageDao {

    @Query("SELECT * FROM pages WHERE section_id = :id")
    fun all(id: Long): LiveData<List<Page>>

    @Query("SELECT * FROM pages WHERE id = :id")
    fun get(id: Long): LiveData<Page>

    @Insert
    fun insert(page: Page): Long

    @Delete
    fun delete(page: Page)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(page: Page)
}