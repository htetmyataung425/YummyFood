package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.country.Meal
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter (var countryList : List<Meal> = ArrayList()) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>()
{
    private var clickListener: ClickListener?= null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener = clickListener
    }

    fun updateCountry(countryList : List<Meal>)
    {
        this.countryList = countryList
        notifyDataSetChanged()
    }
    inner class CountryViewHolder(itemView : View): RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal: Meal

        fun bindCountry(meal: Meal)
        {
            this.meal = meal
            itemView.txtCountryName.text = meal.strArea
        }

        override fun onClick(view : View?)
        {
           clickListener?.click(meal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }
    override fun getItemCount(): Int
    {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryViewHolder, position: Int)
    {
        holder.bindCountry(countryList[position])
    }

    interface ClickListener
    {
        fun click(meal: Meal)
    }
}