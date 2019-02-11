package id.ac.akakom.mobile.note.ui.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.ac.akakom.mobile.note.data.model.Page
import id.ac.akakom.mobile.note.data.repository.PageRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PageViewModel internal constructor(private val pageRepository: PageRepository) : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    fun all(id: Long) = pageRepository.all(id)

    fun delete(page: Page) = scope.launch(Dispatchers.IO) { pageRepository.delete(page) }

}
