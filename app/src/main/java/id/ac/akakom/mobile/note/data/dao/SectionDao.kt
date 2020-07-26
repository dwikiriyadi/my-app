package id.ac.akakom.mobile.note.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.akakom.mobile.note.data.model.Section

@Dao
interface SectionDao {

    @Query("SELECT * FROM sections")
    suspend fun all(): List<Section>

    @Insert
    suspend fun insert(section: Section)

    @Delete
    suspend fun delete(section: Section)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(section: Section)
}