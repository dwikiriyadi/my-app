package io.github.dwikiriyadi.myapp.core.objects.entity

import androidx.room.*
import io.github.dwikiriyadi.myapp.core.objects.results.PageResult
import java.util.*

@Entity(
    tableName = "pages",
    foreignKeys = [ForeignKey(
        entity = Section::class,
        parentColumns = ["id"],
        childColumns = ["section_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("section_id")]
)
data class Page(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var section_id: Long,
    var title: String? = null,
    var content: String? = null,
    var created_at: Calendar = Calendar.getInstance(),
    var updated_at: Calendar = Calendar.getInstance()
) {
    fun toResult() = PageResult(id, section_id, title ?: "", content ?: "")
}