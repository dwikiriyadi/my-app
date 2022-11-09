package io.github.dwikiriyadi.app.core.objects.entity

import androidx.room.*
import io.github.dwikiriyadi.app.core.objects.results.SectionResult
import java.util.*

@Entity(tableName = "sections")
data class Section(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String?,
    var created_at: Calendar = Calendar.getInstance()
) {
    fun toResult() = SectionResult(id, title ?: "")
}