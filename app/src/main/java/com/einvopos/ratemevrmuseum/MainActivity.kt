package com.einvopos.ratemevrmuseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.einvopos.e_invopos.utils.Coroutines
import com.einvopos.ratemevrmuseum.data.AppDatabase
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * main and only activity of the project, i am are following the single activity pattern using the navigation graph and the navigation library
 */
class MainActivity : AppCompatActivity(),KodeinAware {

    override val kodein by kodein()
    private val database: AppDatabase by instance()

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_main_host_fragment)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
        initializingData()
    }

    //thread to insert data usually this goes in the database creation but im short on time
    fun initializingData(){
        Coroutines.io {
            val exhibit1 = Exhibit("Lion",1668,"description of a lion","https://drive.google.com/open?id=1CZewTBwAGvWeBAKuhB8PITWIVULA4m0w")
            val exhibit2 = Exhibit("Bust",0,"description of a Bust","https://drive.google.com/open?id=1k6FHs8oPRYJ1S_KvVQoz2DcJqFTcdEYo")
            val exhibit3 = Exhibit("Bust",2000,"description of a Bust","https://drive.google.com/open?id=1hhQBZcB_i6Epo6X98eOhfJGkxLWlWJbO")
            val list = listOf(exhibit1,exhibit2,exhibit3)
            database.getExhibitDao().insertOrUpdate(list)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null as DrawerLayout?)
    }
}
