package com.example.restaurantapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.MealsItem
import com.example.restaurantapp.databinding.CateItemBinding
import com.example.restaurantapp.databinding.MealItemBinding
import com.restaurantapp.domain.entity.CategoriesItem


class MealAdapter : ListAdapter<MealsItem, MealAdapter.ViewHolder>(MealDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun onItemClickListener(position: Int, item: MealsItem)
    }

    class ViewHolder(private val itemBinding: MealItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(meal: MealsItem) {
            itemBinding.txvCateMealname.text = meal.strMeal
            itemBinding.categoryDesTv.text = meal.strInstructions
            Glide.with(itemBinding.root.context).load(meal.strMealThumb).into(itemBinding.imvCatemeal)
        }
    }

    // The "CategoryDiffCallback" class extends the
    // "DiffUtil.ItemCallback" class and provides custom implementations
    // of the "areItemsTheSame" and "areContentsTheSame" methods.
    // These methods are used by the "ListAdapter" class to determine whether an item has changed
    // between the old and new data sets, and to update the RecyclerView efficiently.
    class MealDiffCallback : DiffUtil.ItemCallback<MealsItem>() {
        override fun areItemsTheSame(
            oldItem: MealsItem,
            newItem: MealsItem
        ): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(
            oldItem: MealsItem,
            newItem: MealsItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}