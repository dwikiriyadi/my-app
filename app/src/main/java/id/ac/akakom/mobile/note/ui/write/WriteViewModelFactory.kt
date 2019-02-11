package id.ac.akakom.mobile.note.ui.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.akakom.mobile.note.data.repository.PageRepository

class WriteViewModelFactory(private val repository: PageRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = WriteViewModel(repository) as T
}