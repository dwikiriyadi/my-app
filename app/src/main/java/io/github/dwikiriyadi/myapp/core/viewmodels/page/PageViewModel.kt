package io.github.dwikiriyadi.myapp.core.viewmodels.page

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.dwikiriyadi.myapp.core.objects.entity.Page
import io.github.dwikiriyadi.myapp.core.data.repositories.PageRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val pageRepository: PageRepository,
    private val savedStateHandle: SavedStateHandle
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
