package id.ac.akakom.mobile.note.data.model

import androidx.room.*
import java.util.*

@Entity(
    tableName = "pages",
    foreignKeys = [ForeignKey(entity = Section::class, parentColumns = ["id"], childColumns = ["section_id"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("section_id")]
)
data class Page(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val section_id: Long,
    var title: String? = null,
    var content: String? = null,
    var created_at: Calendar = Calendar.getInstance(),
    var updated_at: Calendar = Calendar.getInstance()
)