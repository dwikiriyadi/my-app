package id.ac.akakom.mobile.note.ui.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.akakom.mobile.note.data.repository.PageRepository

class PageViewModelFactory(private val repository: PageRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = PageViewModel(repository) as T
}