package id.ac.akakom.mobile.note.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.akakom.mobile.note.data.model.Section

@Dao
interface SectionDao {

    @Query("SELECT * FROM sections")
    fun all(): LiveData<List<Section>>

    @Insert
    fun insert(section: Section)

    @Delete
    fun delete(section: Section)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(section: Section)
}