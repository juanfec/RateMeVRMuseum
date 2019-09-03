package com.einvopos.ratemevrmuseum.ui.exhibit

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.einvopos.e_invopos.utils.Coroutines
import com.einvopos.e_invopos.utils.snackbar

import com.einvopos.ratemevrmuseum.R
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import com.einvopos.ratemevrmuseum.data.models.Rate
import com.einvopos.ratemevrmuseum.ui.home.HomeDirections
import com.einvopos.ratemevrmuseum.ui.home.HomeViewModel
import com.einvopos.ratemevrmuseum.ui.home.HomeViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.exhibit_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class Exhibit : Fragment(), KodeinAware {
    override val kodein by kodein()
    //the right way is to use its own viewModel
    private val factory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
    private lateinit var exhibit : Exhibit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.exhibit_fragment, container, false)
    }

    /**
     * here we load de data, normaly this should be done with databindng
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        Coroutines.io {
            exhibit = viewModel.getExhibit(ExhibitArgs.fromBundle(arguments!!).id)
            //we need to get back to the main thread to performe the image loading
            Coroutines.main {  Picasso.get().load(exhibit.imgUrl).into(imageView)
                exhibit_title.text = exhibit.name
                exhibit_description.text = exhibit.description
            }
            //now we check for the rate
            var rate = viewModel.getRating(exhibit.id)
            Coroutines.main {
                if(rate!=null){
                    ratingBar.rating = rate.rating!!.toFloat()
                    save_rating.isEnabled = false
                }
            }

        }
        //normaly this should be handled by the viewmodel
        save_rating.setOnClickListener {
            Coroutines.io {
                viewModel.saveRate(Rate(exhibit.id,ratingBar.rating.toDouble()))
                Coroutines.main {
                    view?.snackbar("rate saved")
                    Navigation.findNavController(activity!!, R.id.nav_main_host_fragment).navigateUp()
                }
            }
        }

    }

}
