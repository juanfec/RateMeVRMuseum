package com.einvopos.ratemevrmuseum.ui.home

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
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import kotlinx.android.synthetic.main.home_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

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
    }

    /**
     * here we bind thorought livedata the list of exhibits with our recyclerview
     */
    fun bindUi() = Coroutines.main{
        val liveItems = viewModel.items.await()
        liveItems.observe(this, Observer {
            initRecyclerView(it)
        })
    }

    /**
     * we initialized the recyclerview
     */
    fun initRecyclerView(listItems: MutableList<Exhibit>){
        exhibits_recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerViewExhibitsAdapter(listItems) {
                //this handles the onclick
                activity?.let { it1 -> view?.snackbar("click") }
            }
        }
    }

}
