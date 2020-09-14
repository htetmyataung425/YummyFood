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
import com.htetmyat.yummyfood.adapter.IngredientsListAdapter
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_ingredientsfinal.*

class IngredientsFinalFragment : Fragment()
{
    lateinit var viewModel : ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_ingredientsfinal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(ViewModel::class.java)

        ingredientsLoading()
        observeViewModel()
    }

    private fun ingredientsLoading()
    {
        viewModel.getLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                ingredientsProgress.visibility = View.VISIBLE
            }
            else
            {
                ingredientsProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getDetailsResult().observe(viewLifecycleOwner, Observer {
            var ingredientsdata = it.meals

            txtIngredientsFinalName.text = ingredientsdata[0].strMeal
            txtIngredientsFinalItemName.text = ingredientsdata[0].strCategory
            txtIngredientsFinalCountry.text = ingredientsdata[0].strArea
            Glide.with(this).load(ingredientsdata[0].strMealThumb).into(imgIngredientsFinalImage)
            txtIngredientsFinalInstruction.text = ingredientsdata[0].strInstructions

            btnIngredientsFinalIngredient.setOnClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Ingredients")
                val ingredients = arrayOf(ingredientsdata[0].strIngredient1,ingredientsdata[0].strIngredient2,ingredientsdata[0].strIngredient3,ingredientsdata[0].strIngredient4,ingredientsdata[0].strIngredient5,ingredientsdata[0].strIngredient6,ingredientsdata[0].strIngredient7,ingredientsdata[0].strIngredient8,ingredientsdata[0].strIngredient9,ingredientsdata[0].strIngredient10,ingredientsdata[0].strIngredient11,ingredientsdata[0].strIngredient12,ingredientsdata[0].strIngredient13,ingredientsdata[0].strIngredient4,ingredientsdata[0].strIngredient15)
                builder.setItems(ingredients){dialog,which ->}
                val dialog = builder.create()
                dialog.show()
            }

            btnIngredientsFinalRecipe.setOnClickListener{
                if(ingredientsdata[0].strSource.toString().isEmpty())
                {
                    Toast.makeText(context,"This recipe link is empty.",Toast.LENGTH_LONG).show()
                }
                else
                {
                    startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(ingredientsdata[0].strSource.toString())))
                }
            }

            btnIngredientsFinalYoutube.setOnClickListener{
                if(ingredientsdata[0].strYoutube.isEmpty())
                {
                    Toast.makeText(context,"This youtube link is empty.", Toast.LENGTH_LONG).show()
                }
                else
                {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ingredientsdata[0].strYoutube)))
                }
            }

        })
    }
    override fun onResume()
    {
        super.onResume()
        var arguments = arguments?.let { IngredientsFinalFragmentArgs.fromBundle(it) }
        var accept  = arguments?.ingredientsfinalid.toString()
        viewModel.loadDetails(accept!!)
    }
}