package com.einvopos.ratemevrmuseum

import android.app.Application
import com.einvopos.ratemevrmuseum.data.AppDatabase
import com.einvopos.ratemevrmuseum.ui.home.HomeViewModelFactory
import com.einvopos.ratemevrmuseum.ui.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * this is the base application class for android, here we instanciate kodein
 * more info here https://kodein.org/Kodein-DI/?6.3/android
 */
class BaseApplication : Application(), KodeinAware {
    override val kodein= Kodein.lazy {
        import(androidXModule(this@BaseApplication))
        bind() from singleton { AppDatabase(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

    }
}