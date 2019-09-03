package com.einvopos.ratemevrmuseum.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.einvopos.e_invopos.utils.Coroutines
import com.einvopos.e_invopos.utils.lazyDeferred
import com.einvopos.ratemevrmuseum.data.AppDatabase
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import com.einvopos.ratemevrmuseum.data.models.Rate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * viwemodel that takes care of retrieving data for the view
 */
class HomeViewModel(var appDatabase: AppDatabase) : ViewModel() {

    val items by lazyDeferred {
        getItems()
    }

    suspend fun getItems(): LiveData<MutableList<Exhibit>> {
        return withContext(Dispatchers.IO){
            appDatabase.getExhibitDao().getExhibits()
        }
    }

    suspend fun saveExhibits(list:  List<Exhibit>){
        appDatabase.getExhibitDao().insertOrUpdate(list)
    }

    suspend fun getExhibit(id: Long): Exhibit{
        return appDatabase.getExhibitDao().findById(id)
    }

    suspend fun getRating(exhibitId: Long): Rate?{
        return appDatabase.getRateDao().getRate(exhibitId)
    }

    suspend fun saveRate(rate: Rate) {
        appDatabase.getRateDao().insertOrUpdate(listOf(rate))
    }


}
