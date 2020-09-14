package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.category.CategoryX
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter (var categoryList : List<CategoryX> = ArrayList()) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()
{
    private var clickListener : ClickListener? = null
    fun setOnClickListener (clickListener: ClickListener)
    {
        this.clickListener = clickListener
    }

    fun updateCategory(categoryList : List<CategoryX>)
    {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }
    inner class CategoryViewHolder(itemView : View): RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var categoryx: CategoryX

        fun bindCategoryX(categoryx : CategoryX)
        {
            this.categoryx = categoryx
            Glide.with(itemView).load(categoryx.strCategoryThumb).into(itemView.imgcategoryimage)
            itemView.txtcateoryname.text = categoryx.strCategory
        }

        override fun onClick(view: View?) {
            clickListener?.click(categoryx)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int)
    {
        holder.bindCategoryX(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    interface ClickListener
    {
        fun click(categoryX : CategoryX)
    }
}