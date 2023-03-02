package com.example.restaurantapp.ui.home.favorite

import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup


import androidx.navigation.NavController
import androidx.navigation.Navigation


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import com.bumptech.glide.Glide

import com.example.domain.entity.FavoriteMeal

import com.example.restaurantapp.databinding.FavoriteItemBinding



class FavoriteMealsAdapter(private val context: Context) : ListAdapter<FavoriteMeal, FavoriteMealsAdapter.ViewHolder>(
    MealDiffCallback()
) {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = getItem(position)
        holder.itemBinding.favName.text = meal.strMeal
        Glide.with(context).load(meal.strMealThumb).into(holder.itemBinding.favImv)

        holder.itemView.setOnClickListener{
            navController = Navigation.findNavController(it)
            val action = meal.idMeal?.let { it1 ->
               FavoriteMealsFragmentDirections.actionFavoriteFragmentToDetailsMealsFragment(null, it1)
            }
            if (action != null) {
                navController.navigate(action)
            }
        }
    }

    class ViewHolder(val itemBinding: FavoriteItemBinding) : RecyclerView.ViewHolder(itemBinding.root)


    // The "CategoryDiffCallback" class extends the
    // "DiffUtil.ItemCallback" class and provides custom implementations
    // of the "areItemsTheSame" and "areContentsTheSame" methods.
    // These methods are used by the "ListAdapter" class to determine whether an item has changed
    // between the old and new data sets, and to update the RecyclerView efficiently.
    class MealDiffCallback : DiffUtil.ItemCallback<FavoriteMeal>() {
        override fun areItemsTheSame(
            oldItem: FavoriteMeal,
            newItem: FavoriteMeal
        ): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(
            oldItem: FavoriteMeal,
            newItem: FavoriteMeal
        ): Boolean {
            return oldItem == newItem
        }
    }
}