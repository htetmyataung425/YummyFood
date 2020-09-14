package com.htetmyat.yummyfood.fragment
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_countryfinal.*
import kotlinx.android.synthetic.main.fragment_letterfinal.*


class CountryFinalFragment : Fragment()
{
    lateinit var viewModel: ViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_countryfinal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        countryLoading()
        observeViewModel()
    }

    private fun countryLoading()
    {
        viewModel.getLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                countryProgress.visibility = View.VISIBLE
            }
            else
            {
                countryProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getDetailsResult().observe(viewLifecycleOwner, Observer {
            var countryData = it.meals

            txtCountryFinalName.text = countryData[0].strMeal
            txtCountryFinalItemName.text = countryData[0].strCategory
            txtCountryFinalCountry.text = countryData[0].strArea
            Glide.with(this).load(countryData[0].strMealThumb).into(imgCountryFinalImage)
            txtCountryFinalInstruction.text = countryData[0].strInstructions

            btnCountryFinalIngredient.setOnClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Ingredients")
                val ingredients = arrayOf(countryData[0].strIngredient1,countryData[0].strIngredient2,countryData[0].strIngredient3,countryData[0].strIngredient4,countryData[0].strIngredient5,countryData[0].strIngredient6,countryData[0].strIngredient7,countryData[0].strIngredient8,countryData[0].strIngredient9,countryData[0].strIngredient10,countryData[0].strIngredient11,countryData[0].strIngredient12,countryData[0].strIngredient13,countryData[0].strIngredient4,countryData[0].strIngredient15)
                builder.setItems(ingredients){dialog,which ->}
                val dialog = builder.create()
                dialog.show()
            }

            btnCountryFinalRecipe.setOnClickListener{
                if(countryData[0].strSource.toString().isEmpty())
                {
                    Toast.makeText(context,"This recipe link is empty.", Toast.LENGTH_LONG).show()
                }
                else
                {
                    startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(countryData[0].strSource.toString())))
                }
            }

            btnCountryFinalYoutube.setOnClickListener{
                if(countryData[0].strYoutube.isEmpty())
                {
                    Toast.makeText(context,"This youtube link is empty.",Toast.LENGTH_LONG).show()
                }
                else
                {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(countryData[0].strYoutube)))
                }
            }
        })
    }

    override fun onResume()
    {
        super.onResume()
        var argument = arguments?.let { CountryFinalFragmentArgs.fromBundle(it) }
        var accept = argument?.countryid
        viewModel.loadDetails(accept!!)

    }

}


