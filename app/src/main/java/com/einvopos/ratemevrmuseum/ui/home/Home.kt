package com.einvopos.ratemevrmuseum.ui.home

import android.graphics.Path
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.einvopos.e_invopos.utils.Coroutines
import com.einvopos.e_invopos.utils.snackbar

import com.einvopos.ratemevrmuseum.R
import com.einvopos.ratemevrmuseum.data.AppDatabase
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import kotlinx.android.synthetic.main.home_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.android.kodein

/**
 * home fragment where the exhibits are displayed
 */
class Home : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    /**
     * in the onViewCreated we get our viewmodel with the help of the factory provided by kodein
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        bindUi()
        initializingData()
    }

    //thread to insert data usually this goes in the database creation but im short in time
    fun initializingData(){
        Coroutines.io {
            if (viewModel.items.await().value!=null){
                if(viewModel.items.await().value!!.size<=0){
                    val exhibit1 = Exhibit("Lion",1668,"description of a lion","https://images-na.ssl-images-amazon.com/images/I/61RuSdkVbKL._SY679_.jpg")
                    val exhibit2 = Exhibit("Ancient Bust",0,"description of an Ancient Bust","http://www.sothebys.com/content/dam/stb/lots/L17/L17260/805L17260_994D9.jpg.webrend.1280.1280.jpeg")
                    val exhibit3 = Exhibit("Bust",2000,"description of a Bust","https://cdn.shopify.com/s/files/1/0468/9773/products/20131108_100847_-_Copy.jpg?v=1523643508")
                    val list = listOf(exhibit1,exhibit2,exhibit3)
                    viewModel.saveExhibits(list)
                }
            }
        }
    }

    /**
     * here we bind thorought livedata the list of exhibits with our recyclerview
     */
    fun bindUi() = Coroutines.main{
        val liveItems = viewModel.items.await()
        liveItems.observe(this, Observer {
            initRecyclerView(it)
            //this is to make sure i have something to display (this is not the right way)
            if (it.size<=0){
                initializingData()
            }
        })
    }

    /**
     * we initialized the recyclerview
     */
    fun initRecyclerView(listItems: MutableList<Exhibit>){
        exhibits_recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerViewExhibitsAdapter(listItems) {
                activity?.let { it1 ->
                    val directions = HomeDirections.actionHomeToExhibit(it.id)
                    Navigation.findNavController(activity!!, R.id.nav_main_host_fragment).navigate(directions)
                }
            }
        }
    }

}
