package id.ac.akakom.mobile.note.ui.page

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import id.ac.akakom.mobile.note.data.model.Page
import id.ac.akakom.mobile.note.data.repository.PageRepository
import kotlinx.coroutines.launch

class PageViewModel @ViewModelInject constructor(
    private val pageRepository: PageRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val sectionId = MutableLiveData<Long>()

    private val _all = MutableLiveData<List<Page>>()

    val all: LiveData<List<Page>>
        get() = _all

    fun getAll(id: Long) = viewModelScope.launch {
        _all.postValue(pageRepository.all(id))
    }

    fun delete(page: Page) = viewModelScope.launch { pageRepository.delete(page) }

}
