package com.example.restaurantapp.ui.home

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
import com.example.restaurantapp.databinding.CateItemBinding
import com.example.restaurantapp.databinding.MealItemBinding
import com.restaurantapp.domain.entity.CategoriesItem


class MealAdapter(private val context: Context) : ListAdapter<MealsItem, MealAdapter.ViewHolder>(MealDiffCallback()) {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = getItem(position)
        holder.itemBinding.txvCateMealname.text = meal.strMeal
        holder.itemBinding.categoryDesTv.text   = meal.strInstructions
        Glide.with(context).load(meal.strMealThumb).into(holder.itemBinding.imvCatemeal)

        holder.itemView.setOnClickListener{
            navController = Navigation.findNavController(it)
            val action = meal.idMeal?.let { it1 ->  HomeFragmentDirections.actionMainFragmentToDetailsFragment(mealId = it1) }
            if (action != null) {
                navController.navigate(action)
            }
        }
    }


    class ViewHolder(val itemBinding: MealItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
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