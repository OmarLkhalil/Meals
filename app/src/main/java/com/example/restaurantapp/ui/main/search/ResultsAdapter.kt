package com.example.restaurantapp.ui.main.search

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
import com.example.restaurantapp.databinding.SearchItemBinding


class ResultsAdapter(val context: Context) : ListAdapter<MealsItem, ResultsAdapter.ViewHolder>(
    MealDiffCallback()
) {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = getItem(position)
        val searchedMealImageUrl = meal.strMealThumb
        val searchedMealName = meal.strMeal

        holder.itemBinding.resultsName.text = searchedMealName
        Glide.with(context).load(searchedMealImageUrl).into(holder.itemBinding.resultsImv)

        holder.itemView.setOnClickListener{
            navController = Navigation.findNavController(it)
            val action = meal.idMeal?.let { it1 ->
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                    mealId = it1
                )
            }
            if (action != null) {
                navController.navigate(action)
            }
        }
    }

    inner class ViewHolder(val itemBinding: SearchItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

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