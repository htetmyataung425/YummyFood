package com.htetmyat.yummyfood.api

import com.htetmyat.yummyfood.model.Ingredients.Ingredients
import com.htetmyat.yummyfood.model.categorylist.CategoryList
import com.htetmyat.yummyfood.model.category.Category
import com.htetmyat.yummyfood.model.countrylist.CountryList
import com.htetmyat.yummyfood.model.country.Country
import com.htetmyat.yummyfood.model.finaldetails.FinalDetails
import com.htetmyat.yummyfood.model.ingredientslist.Ingredientslist
import com.htetmyat.yummyfood.model.letterList.LetterList
import com.htetmyat.yummyfood.model.random.Random
import com.htetmyat.yummyfood.model.search.Search
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient
{
    var retrofit: Retrofit
    var apiInterface: ApiInterface
    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    init
    {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        apiInterface = retrofit.create(ApiInterface :: class.java)
    }
    fun getRandom() : Call<Random> {  return apiInterface.getRandom() }
    //Category
    fun getCategory() : Call<Category> { return apiInterface.getCategory()  }
    //CategoryList
    fun getCategoryList(c : String) : Call<CategoryList> { return apiInterface.getCategoryList(c) }
    //FinalDetails
    fun getLookup(i : String) : Call<FinalDetails> {  return apiInterface.getLookup(i)  }
    //letter
     fun getSearch(f : String) : Call<LetterList> {   return apiInterface.getSearch(f)  }
    //CountryList
    fun getCountry(a : String) : Call<Country> { return apiInterface.getCountry(a) }
    //CountryDetails
    fun getCountryList(a: String) : Call<CountryList> {return apiInterface.getCountryList(a)}
    //Ingredients
    fun getIngredients(i:String) : Call<Ingredients> {  return apiInterface.getIngredients(i) }
    //Ingredients list
    fun getIngredientsList(i:String) : Call<Ingredientslist> { return apiInterface.getIngredientsList(i)}
    //MainSearch
    fun getMainSearch(s:String) : Call<Search> { return  apiInterface.getMainSearch(s)}
}