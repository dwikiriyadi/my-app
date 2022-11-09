package io.github.dwikiriyadi.app.core.data.sources.dao

import androidx.room.*
import io.github.dwikiriyadi.app.core.objects.entity.Section

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