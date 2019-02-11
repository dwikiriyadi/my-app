package id.ac.akakom.mobile.note.data.model

import androidx.room.*
import java.util.*

@Entity(tableName = "sections")
data class Section(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String,
    var created_at: Calendar = Calendar.getInstance()
)