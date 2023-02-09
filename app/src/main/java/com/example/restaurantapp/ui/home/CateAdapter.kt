package com.example.restaurantapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantapp.databinding.CateItemBinding
import com.restaurantapp.domain.entity.CategoriesItem


class CateAdapter : ListAdapter<CategoriesItem, CateAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClickListener(position, item)
        }
    }


    var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener{
        fun onItemClickListener(position: Int, item: CategoriesItem)
    }

    class ViewHolder(private val itemBinding: CateItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: CategoriesItem) {
            itemBinding.txvCateMealname.text = category.strCategory
            itemBinding.categoryDesTv.text = category.strCategoryDescription
            Glide.with(itemBinding.root.context).load(category.strCategoryThumb).into(itemBinding.imvCatemeal)
        }
    }

    // The "CategoryDiffCallback" class extends the
    // "DiffUtil.ItemCallback" class and provides custom implementations
    // of the "areItemsTheSame" and "areContentsTheSame" methods.
    // These methods are used by the "ListAdapter" class to determine whether an item has changed
    // between the old and new data sets, and to update the RecyclerView efficiently.
    class CategoryDiffCallback : DiffUtil.ItemCallback<CategoriesItem>() {
        override fun areItemsTheSame(
            oldItem: CategoriesItem,
            newItem: CategoriesItem
        ): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(
            oldItem: CategoriesItem,
            newItem: CategoriesItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}