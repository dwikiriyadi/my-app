package io.github.dwikiriyadi.myapp.core.data.sources.dao

import androidx.room.*
import io.github.dwikiriyadi.myapp.core.objects.entity.Section

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