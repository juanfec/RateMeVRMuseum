package com.einvopos.ratemevrmuseum.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.einvopos.ratemevrmuseum.data.AppDatabase

/**
 * viewmodel factory since a viewmodel can have arguments in a constructor
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(val database: AppDatabase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(database) as T
    }
}