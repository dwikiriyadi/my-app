package id.ac.akakom.mobile.note.ui.write

import androidx.lifecycle.ViewModel;
import id.ac.akakom.mobile.note.data.model.Page
import id.ac.akakom.mobile.note.data.repository.PageRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WriteViewModel internal constructor(private val pageRepository: PageRepository) : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    fun get(id: Long) = pageRepository.get(id)

    fun insert(page: Page) = scope.launch(Dispatchers.IO) {
        pageRepository.insert(page)
    }

    fun delete(page: Page) = scope.launch(Dispatchers.IO) { pageRepository.delete(page) }

    fun update(page: Page) = scope.launch(Dispatchers.IO) { pageRepository.update(page) }

}
