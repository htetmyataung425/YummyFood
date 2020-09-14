package com.htetmyat.yummyfood.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.htetmyat.yummyfood.api.ApiClient
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
import retrofit2.Callback
import retrofit2.Response
import kotlin.jvm.internal.Ref

class ViewModel : ViewModel()
{
    //Random
    private var randomLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getRandomLoading() : LiveData<Boolean> = randomLoading

    private var randomResult : MutableLiveData<Random> = MutableLiveData()
    fun getRandomResult() : LiveData<Random> = randomResult
    fun loadRandom()
    {
        var apiClient = ApiClient()
        var callRandom = apiClient.getRandom()
        callRandom.enqueue(object : Callback<Random>
        {
            override fun onFailure(call: Call<Random>, t: Throwable)
            {
                randomLoading.value = true
            }
            override fun onResponse(call: Call<Random>, response: Response<Random>)
             {
                randomResult.value = response.body()!!
                 randomLoading.value = false
            }
        })
    }
    //Category
    private var categoryResult : MutableLiveData<Category> = MutableLiveData()
    fun getCategoryResult() : LiveData<Category> = categoryResult
    fun loadCategory()
    {
        var apiClient = ApiClient()
        var callCategory = apiClient.getCategory()
        callCategory.enqueue(object: Callback<Category>{
            override fun onFailure(call: Call<Category>, t: Throwable)
            {
            }
            override fun onResponse(call: Call<Category>, response: Response<Category>)
            {
                categoryResult.value = response.body()!!
            }
        })
    }
    //CategoryList
    private var categoryListLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getCategoryListLoading() : LiveData<Boolean> = categoryListLoading

    private var categoryListResult : MutableLiveData<CategoryList> = MutableLiveData()
    fun getCategoryListResult() : LiveData<CategoryList> = categoryListResult
    fun loadCategoryList(c : String)
    {
        var apiClient = ApiClient()
        var callItem = apiClient.getCategoryList(c)
        callItem.enqueue(object : Callback<CategoryList>
        {
            override fun onFailure(call: Call<CategoryList>, t: Throwable)
            {
                categoryListLoading.value = true
            }
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>)
            {
                categoryListResult.value = response.body()
                categoryListLoading.value = false
            }
        })
    }
    //FinalDetails
    private var Loading : MutableLiveData<Boolean> = MutableLiveData()
    fun getLoading() : LiveData<Boolean> = Loading

    private var detailslResult : MutableLiveData<FinalDetails> = MutableLiveData()
    fun getDetailsResult() : LiveData<FinalDetails> = detailslResult
    fun loadDetails(i : String)
    {
        var apiClient = ApiClient()
        var callDetails  = apiClient.getLookup(i)
        callDetails.enqueue(object : Callback<FinalDetails>
        {
            override fun onFailure(call: Call<FinalDetails>, t: Throwable)
            {
                Loading.value = true
            }
            override fun onResponse(call: Call<FinalDetails>, response: Response<FinalDetails>)
            {
                detailslResult.value = response.body()!!
                Loading.value = false
            }
        })
    }
    //LetterList
    private var letterListLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getLetterListLoading() : LiveData<Boolean> = letterListLoading

    private var letterListResult : MutableLiveData<LetterList> = MutableLiveData()
    fun getLetterListResult() : LiveData<LetterList> = letterListResult
    fun loadLetterList(f : String)
    {
        var apiClient = ApiClient()
        var callLetter = apiClient.getSearch(f)
        callLetter.enqueue(object : Callback<LetterList>
        {
            override fun onFailure(call: Call<LetterList>, t: Throwable)
            {
                letterListLoading.value= true
            }
            override fun onResponse(call: Call<LetterList>, response: Response<LetterList>)
            {
                letterListResult.value = response.body()
                letterListLoading.value = false
            }

        })
    }
    //Country
    private var countryLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getCountryLoading() : LiveData<Boolean> = countryLoading

    private var countryResult : MutableLiveData<Country> = MutableLiveData()
    fun getCountryResult() : LiveData<Country> = countryResult
    fun loadCountry(a : String)
    {
        var apiClient = ApiClient()
        var callCountryList = apiClient.getCountry(a)
        callCountryList.enqueue(object : Callback<Country>
        {
            override fun onFailure(call: Call<Country>, t: Throwable)
            {
                countryLoading.value = true
            }
            override fun onResponse(call: Call<Country>, response: Response<Country>)
            {
                countryResult.value = response.body()
                countryLoading.value = false
            }
        })
    }
    //CountryList
    private var countryListLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getCountryListLoading() : LiveData<Boolean> = countryListLoading

    private var countryListResult : MutableLiveData<CountryList> = MutableLiveData()
    fun getCountryListResult() :LiveData<CountryList> = countryListResult
    fun loadCountryList(a: String)
    {
        var apiClient = ApiClient()
        var callCountryDetails= apiClient.getCountryList(a)
        callCountryDetails.enqueue(object :Callback<CountryList>{
            override fun onFailure(call: Call<CountryList>, t: Throwable)
            {
                countryListLoading.value = true
            }
            override fun onResponse(call: Call<CountryList>, response: Response<CountryList>)
            {
                countryListResult.value = response.body()
                countryListLoading.value = false
            }
        })
    }
    //Ingredients
    private var ingredientsLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getIngredientsLoading() : LiveData<Boolean> = ingredientsLoading

    private var ingredientsResult : MutableLiveData<Ingredients> = MutableLiveData()
    fun getIngredientsResult():LiveData<Ingredients> = ingredientsResult
    fun loadIngredients(i:String)
    {
        var apiClient= ApiClient()
        var callIngredients = apiClient.getIngredients(i)
        callIngredients.enqueue(object:Callback<Ingredients>
        {
            override fun onFailure(call: Call<Ingredients>, t: Throwable)
            {
                ingredientsLoading.value = true
            }
            override fun onResponse(call: Call<Ingredients>, response: Response<Ingredients>)
            {
                ingredientsResult.value = response.body()
                ingredientsLoading.value = false
            }
        })
    }
    //Ingredients List
    private var ingredientsListLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getIngredientsListLoading() : LiveData<Boolean> = ingredientsListLoading

    private var ingredientsListResult : MutableLiveData<Ingredientslist> = MutableLiveData()
    fun getIngredientsListResult() : LiveData<Ingredientslist> = ingredientsListResult
    fun loadIngredientslist(i:String)
    {
        var apiClient = ApiClient()
        var callIngredientsList = apiClient.getIngredientsList(i)
        callIngredientsList.enqueue(object :Callback<Ingredientslist>
        {
            override fun onFailure(call: Call<Ingredientslist>, t: Throwable)
            {
                ingredientsListLoading.value = true
            }
            override fun onResponse(call: Call<Ingredientslist>, response: Response<Ingredientslist>)
            {
                ingredientsListResult.value = response.body()
                ingredientsListLoading.value = false
            }
        })
    }
   /* //Search
    private var searchLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getSearchLoading() : LiveData<Boolean> = searchLoading

    private var searchResult : MutableLiveData<Search> = MutableLiveData()
    fun getSearchResult() : LiveData<Search> = searchResult
    fun loadSearch(s:String)
    {
        var apiClient = ApiClient()
        var callMainSearch = apiClient.getMainSearch()
        callMainSearch.enqueue(object : Callback<Search>
        {
            override fun onFailure(call: Call<Search>, t: Throwable)
            {
                searchLoading.value = true
            }
            override fun onResponse(call: Call<Search>, response: Response<Search>)
            {
                searchResult.value = response.body()
                searchLoading.value = false
            }
        })
    }*/
   /* private var searchLoading : MutableLiveData<Boolean> = MutableLiveData()
    fun getSearchLoading() : LiveData<Boolean> = searchLoading

    private var searchResult : MutableLiveData<Search> = MutableLiveData()
    fun getSearchResult() : LiveData<Search> = searchResult
    fun loadSearch(s:String)
    {
        var apiClient = ApiClient()
        var callMainSearch = apiClient.getMainSearch(s)
        callMainSearch.enqueue(object : Callback<Search>
        {
            override fun onFailure(call: Call<Search>, t: Throwable)
            {
                searchLoading.value = true
            }
            override fun onResponse(call: Call<Search>, response: Response<Search>)
            {
                searchResult.value = response.body()
                searchLoading.value = false
            }
        })
    }*/
}