package com.example.restaurantapp.ui.main.home.categories

import android.content.Context


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.navigation.NavController
import androidx.navigation.Navigation


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import com.bumptech.glide.Glide


import com.example.restaurantapp.databinding.CateItemBinding
import com.example.restaurantapp.ui.main.home.HomeFragmentDirections
import com.restaurantapp.domain.entity.CategoriesItem


class CateAdapter(private val context: Context): ListAdapter<CategoriesItem, CateAdapter.ViewHolder>(CategoryDiffCallback()) {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = getItem(position)
        holder.itemBinding.txvCateMealname.text = meal.strCategory
        holder.itemBinding.categoryDesTv.text   = meal.strCategoryDescription
        Glide.with(context).load(meal.strCategoryThumb).into(holder.itemBinding.imvCatemeal)

        holder.itemView.setOnClickListener {
            initCategoryFragment(it, meal)
        }
    }

    private fun initCategoryFragment(view: View, category: CategoriesItem) {
        navController = Navigation.findNavController(view)
        val action = HomeFragmentDirections.actionMainToCateDetails(category,null)
        navController.navigate(action)
    }

    class ViewHolder(val itemBinding: CateItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

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