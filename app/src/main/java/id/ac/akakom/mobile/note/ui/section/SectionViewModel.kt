package id.ac.akakom.mobile.note.ui.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.ac.akakom.mobile.note.data.model.Section
import id.ac.akakom.mobile.note.data.repository.SectionRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SectionViewModel internal constructor(private val sectionRepository: SectionRepository) : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    val all: LiveData<List<Section>> = Transformations.map(sectionRepository.all()) { sections ->
        sections.filter { it.title.isNotEmpty() }
    }

    fun insert(section: Section) = scope.launch(Dispatchers.IO) { sectionRepository.insert(section) }

    fun delete(section: Section) = scope.launch(Dispatchers.IO) { sectionRepository.delete(section) }

    fun update(section: Section) = scope.launch(Dispatchers.IO) { sectionRepository.update(section) }
}
