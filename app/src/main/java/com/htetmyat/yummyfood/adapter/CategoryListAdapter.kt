package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.categorylist.Meal
import kotlinx.android.synthetic.main.item_categorylist.view.*

class CategoryListAdapter (var categoryListList : List<Meal> = ArrayList()) : RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>()
{
    private var clickListener : ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener = clickListener
    }
    fun updateCategoryList(categoryListList : List<Meal>)
    {
        this.categoryListList = categoryListList
        notifyDataSetChanged()
    }

    inner class CategoryListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {  itemView.setOnClickListener(this)  }

        lateinit var meal: Meal

        fun bindCategoryList(meal : Meal)
        {
            this.meal = meal
            Glide.with(itemView).load(meal.strMealThumb).into(itemView.imgCategoryListImage)
            itemView.txtCategoryListName.text = meal.strMeal
        }

        override fun onClick(p0: View?)
        {
            clickListener?.click(meal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_categorylist,parent,false)
        return CategoryListViewHolder(view)
    }
    override fun getItemCount(): Int
    {
        return categoryListList.size
    }
    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int)
    {
        holder.bindCategoryList(categoryListList[position])
    }

    interface ClickListener
    {
        fun click(meal : Meal)
    }
}
