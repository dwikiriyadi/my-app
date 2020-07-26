package id.ac.akakom.mobile.note.ui.write

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import id.ac.akakom.mobile.note.data.model.Page
import id.ac.akakom.mobile.note.data.repository.PageRepository
import kotlinx.coroutines.launch
import java.util.*

class WriteViewModel @ViewModelInject constructor(
    private val pageRepository: PageRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _page = MutableLiveData<Page>()
    val page: LiveData<Page>
        get() = _page

    fun get(id: Long) {
        viewModelScope.launch {
            _page.postValue(pageRepository.get(id))
        }
    }

    fun insert(page: Page) = viewModelScope.launch {
        pageRepository.insert(page)
    }

    fun delete(page: Page) = viewModelScope.launch { pageRepository.delete(page) }

    fun update(page: Page) = viewModelScope.launch {
        page.updated_at = Calendar.getInstance()
        pageRepository.update(page)
    }

}
