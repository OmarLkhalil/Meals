package com.example.restaurantapp.ui.main.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.MealsItem
import com.example.restaurantapp.databinding.FavoriteItemBinding
import com.example.restaurantapp.ui.main.home.HomeFragmentDirections
import com.restaurantapp.domain.entity.CategoriesItem


class FavoriteAdapter(private val context: Context) : ListAdapter<MealsItem, FavoriteAdapter.ViewHolder>(
    MealDiffCallback()
) {

    private lateinit var category: CategoriesItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = getItem(position)
        holder.itemBinding.favName.text = meal.strMeal
        Glide.with(context).load(meal.strMealThumb).into(holder.itemBinding.favImv)

    }

    class ViewHolder(val itemBinding: FavoriteItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    fun navigate(category: CategoriesItem){
        this.category =category
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