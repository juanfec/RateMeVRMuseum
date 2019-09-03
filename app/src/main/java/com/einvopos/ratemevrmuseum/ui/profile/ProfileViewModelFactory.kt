package com.einvopos.ratemevrmuseum.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.einvopos.ratemevrmuseum.data.AppDatabase
import com.einvopos.ratemevrmuseum.ui.home.HomeViewModel

/**
 * viewmodel factory since a viewmodel can have arguments in a constructor
 */
@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(val database: AppDatabase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(database) as T
    }
}