package io.github.dwikiriyadi.myapp.core.viewmodels.section

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.dwikiriyadi.myapp.core.objects.entity.Section
import io.github.dwikiriyadi.myapp.core.data.repositories.SectionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    private val sectionRepository: SectionRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _all = MutableLiveData<List<Section>>()

    val all: LiveData<List<Section>>
        get() = _all

    fun getAll() = viewModelScope.launch {
        _all.postValue(sectionRepository.all().filter { (it.title ?: "").isNotEmpty() })
    }

    fun insert(section: Section) =
        viewModelScope.launch { sectionRepository.insert(section) }


    fun delete(section: Section) =
        viewModelScope.launch { sectionRepository.delete(section) }


    fun update(section: Section) =
        viewModelScope.launch { sectionRepository.update(section) }

}
