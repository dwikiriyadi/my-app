package io.github.dwikiriyadi.app.core.viewmodels.write

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.dwikiriyadi.app.core.data.repositories.PageRepository
import io.github.dwikiriyadi.app.core.objects.entity.Page
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val pageRepository: PageRepository,
    private val savedStateHandle: SavedStateHandle
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
