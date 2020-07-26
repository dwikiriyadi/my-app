package id.ac.akakom.mobile.note.ui.section

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import id.ac.akakom.mobile.note.data.model.Section
import id.ac.akakom.mobile.note.data.repository.SectionRepository
import kotlinx.coroutines.launch

class SectionViewModel @ViewModelInject constructor(
    private val sectionRepository: SectionRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _all = MutableLiveData<List<Section>>()

    val all: LiveData<List<Section>>
        get() = _all

    fun getAll() = viewModelScope.launch {
        _all.postValue(sectionRepository.all().filter { it.title.isNotEmpty() })
    }

    fun insert(section: Section) =
        viewModelScope.launch { sectionRepository.insert(section) }


    fun delete(section: Section) =
        viewModelScope.launch { sectionRepository.delete(section) }


    fun update(section: Section) =
        viewModelScope.launch { sectionRepository.update(section) }

}
