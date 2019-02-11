package id.ac.akakom.mobile.note.ui.section

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.akakom.mobile.note.data.repository.SectionRepository

class SectionViewModelFactory(private val repository: SectionRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = SectionViewModel(repository) as T
}