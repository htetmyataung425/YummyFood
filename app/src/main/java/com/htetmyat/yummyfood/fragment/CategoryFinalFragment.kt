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
import kotlinx.android.synthetic.main.fragment_categoryfinal.*

class CategoryFinalFragment : Fragment()
{
    lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_categoryfinal, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        categoryLoading()
        observeViewModel()
    }

    private fun categoryLoading()
    {
        viewModel.getLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                categoryProgress.visibility = View.VISIBLE
            }
            else
            {
                categoryProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getDetailsResult().observe(viewLifecycleOwner,
            Observer { var mealData = it.meals

                txtfinaldetailsname.text = mealData[0].strMeal
                txtfinaldetailsitemname.text = mealData[0].strCategory
                txtfinaldetailscountry.text = mealData[0].strArea
                Glide.with(this).load(mealData[0].strMealThumb).into(imgfinaldetailsimage)
                txtfinaldetailsinstruction.text = mealData[0].strInstructions

                btnFinalIngredient.setOnClickListener {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Ingredients")
                    val ingredients = arrayOf(mealData[0].strIngredient1,mealData[0].strIngredient2,mealData[0].strIngredient3,mealData[0].strIngredient4,mealData[0].strIngredient5,mealData[0].strIngredient6,mealData[0].strIngredient7,mealData[0].strIngredient8,mealData[0].strIngredient9,mealData[0].strIngredient10,mealData[0].strIngredient11,mealData[0].strIngredient12,mealData[0].strIngredient13,mealData[0].strIngredient14,mealData[0].strIngredient15)
                    //,mealData[0].strIngredient16,mealData[0].strIngredient17,mealData[0].strIngredient18,mealData[0].strIngredient19,mealData[0].strIngredient20
                    builder.setItems(ingredients)  {dialog, which ->}
                    val dialog = builder.create()
                    dialog.show()
                }

                btnFinalRecipe.setOnClickListener{
                    if(mealData[0].strSource.toString().isEmpty())
                    {
                        Toast.makeText(context,"This recipe link is empty.",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(mealData[0].strSource.toString())))
                    }
                }

                btnFinalYoutube.setOnClickListener{
                    if(mealData[0].strYoutube.isEmpty())
                    {
                        Toast.makeText(context,"This youtube link is empty.",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mealData[0].strYoutube)))
                    }
                }
            }
        )
    }

    override fun onResume()
    {
        super.onResume()
        var argumements = arguments?.let { CategoryFinalFragmentArgs.fromBundle(it) }
        var accept: String = argumements?.itemid.toString()
        viewModel.loadDetails(accept)
    }
}
