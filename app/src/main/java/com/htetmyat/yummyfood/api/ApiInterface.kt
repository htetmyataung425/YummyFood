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
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface
{
    @GET ("random.php")
    fun getRandom() : Call<Random>

    //Category
    @GET("categories.php")
    fun getCategory() : Call<Category>

    //Category List
    @GET("filter.php")
    fun getCategoryList( @Query("c") c: String ) : Call<CategoryList>

    //FinalDetails
    @GET("lookup.php")
    fun getLookup(@Query("i") i : String ) : Call<FinalDetails>

    //Letter
    @GET("search.php")
    fun getSearch(@Query("f") f : String ) : Call<LetterList>

    //Country
    @GET("list.php")
    fun getCountry(@Query("a") a : String ) : Call<Country>

    //CountryList
    @GET("filter.php")
    fun getCountryList(@Query("a")a: String) : Call<CountryList>

    //Ingredients
    @GET("list.php")
    fun getIngredients(@Query("i")i:String) : Call<Ingredients>

    //IngredientsList
    @GET("filter.php")
    fun getIngredientsList(@Query("i")i:String) : Call<Ingredientslist>

    //Search
    @GET("search.php")
    fun getMainSearch(@Query("s")s:String) : Call<Search>

}