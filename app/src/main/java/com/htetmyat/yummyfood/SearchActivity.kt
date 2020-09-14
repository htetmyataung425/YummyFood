package com.htetmyat.yummyfood

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.htetmyat.yummyfood.adapter.SearchAdapter
import com.htetmyat.yummyfood.api.ApiClient
import com.htetmyat.yummyfood.model.search.Meal
import com.htetmyat.yummyfood.model.search.Search
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.custom_search_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(),SearchAdapter.ClickListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        edtSearch.setOnEditorActionListener(TextView.OnEditorActionListener { bbb, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
            {
                if (edtSearch.text.toString().isNotEmpty())
                {
                    var apiClient = ApiClient()
                    var apiCall = apiClient.getMainSearch(edtSearch.text.toString())
                    apiCall.enqueue(object : Callback<Search>
                    {
                        override fun onFailure(call: Call<Search>, t: Throwable)
                        {
                        }

                        override fun onResponse(call: Call<Search>, response: Response<Search>)
                        {
                            var adapter = SearchAdapter(response.body()?.meals!!)
                            searchRecycler.layoutManager = GridLayoutManager(this@SearchActivity, 2)
                            searchRecycler.adapter = adapter
                            adapter.setOnClickListener(this@SearchActivity)
                        }
                    })
                }
                true
            } else false
        })
    }

    override fun click(meal: Meal)
    {
        var intent = Intent (this,SearchDetailsActivity::class.java)
        intent.putExtra("sent",meal.idMeal)
        startActivity(intent)
    }
}
/*
package com.htetmyat.yummyfood

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.htetmyat.yummyfood.adapter.SearchAdapter
import com.htetmyat.yummyfood.api.ApiClient
import com.htetmyat.yummyfood.model.search.Meal
import com.htetmyat.yummyfood.model.search.Search
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.custom_search_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(),SearchAdapter.ClickListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        edtSearch.setOnEditorActionListener(TextView.OnEditorActionListener { bbb, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
            {
                if (edtSearch.text.toString().isNotEmpty())
                {
                    var apiClient = ApiClient()
                    var apiCall = apiClient.getMainSearch(edtSearch.text.toString())
                    apiCall.enqueue(object : Callback<Search>
                    {
                        override fun onFailure(call: Call<Search>, t: Throwable)
                        {
                        }

                        override fun onResponse(call: Call<Search>, response: Response<Search>)
                        {
                            var adapter = SearchAdapter(response.body()?.meals!!)
                            searchRecycler.layoutManager = GridLayoutManager(this@SearchActivity, 2)
                            searchRecycler.adapter = adapter
                            adapter.setOnClickListener(this@SearchActivity)
                        }
                    })
                }
                true
            } else false
        })
    }

    override fun click(meal: Meal)
    {
        var intent = Intent (this,SearchDetailsActivity::class.java)
        intent.putExtra("sent",meal.idMeal)
        startActivity(intent)
    }
}

*/
