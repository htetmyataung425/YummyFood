package com.htetmyat.yummyfood

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.fragment.CategoryFinalFragmentArgs
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_search_details.*
import kotlinx.android.synthetic.main.fragment_categoryfinal.*

class SearchDetailsActivity : AppCompatActivity()
{
    lateinit var viewModel : ViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_details)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        searchLoading()
        observeViewModel()
    }

    private fun searchLoading()
    {
        viewModel.getLoading().observe(this, Observer {loading ->
            if(loading)
            {
                searchProgress.visibility = View.VISIBLE
            }
            else
            {
                searchProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getDetailsResult().observe(this,
            Observer { var searchData = it.meals

                txtSearchDetailsName.text = searchData[0].strMeal
                txtSearchDetailsItemName.text = searchData[0].strCategory
                txtSearchDetailsCountry.text = searchData[0].strArea
                Glide.with(this).load(searchData[0].strMealThumb).into(imgSearchDetailsImage)
                txtSearchDetailsInstruction.text = searchData[0].strInstructions

                btnSearchIngredient.setOnClickListener {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Ingredients")
                    val ingredients = arrayOf(searchData[0].strIngredient1,searchData[0].strIngredient2,searchData[0].strIngredient3,searchData[0].strIngredient4,searchData[0].strIngredient5,searchData[0].strIngredient6,searchData[0].strIngredient7,searchData[0].strIngredient8,searchData[0].strIngredient9,searchData[0].strIngredient10,searchData[0].strIngredient11,searchData[0].strIngredient12,searchData[0].strIngredient13,searchData[0].strIngredient14,searchData[0].strIngredient15)
                    //,mealData[0].strIngredient16,mealData[0].strIngredient17,mealData[0].strIngredient18,mealData[0].strIngredient19,mealData[0].strIngredient20
                    builder.setItems(ingredients)  {dialog, which ->}
                    val dialog = builder.create()
                    dialog.show()
                }

                btnSearchRecipe.setOnClickListener{
                    if(searchData[0].strSource.toString().isEmpty())
                    {
                        Toast.makeText(this,"This recipe link is empty.", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse(searchData[0].strSource.toString())))
                    }
                }

                btnSearchYoutube.setOnClickListener{
                    if(searchData[0].strYoutube.isEmpty())
                    {
                        Toast.makeText(this,"This youtube link is empty.", Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(searchData[0].strYoutube)))
                    }
                }
            }
        )
    }

    override fun onResume()
    {
        super.onResume()
        var accept: String = intent.getStringExtra("sent").toString()
        viewModel.loadDetails(accept)
    }
}