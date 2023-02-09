package com.example.restaurantapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.domain.entity.MealsItem
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentMainBinding
import com.example.restaurantapp.ui.detailscategory.DetailsCategoryActivity
import com.example.restaurantapp.ui.detailsmeal.DetailsMealsFragment
import com.restaurantapp.domain.entity.CategoriesItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//The class is annotated with "@AndroidEntryPoint",
// which is a part of the Android KTX library and simplifies the process of creating and accessing Android ViewModels.
@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var navController: NavController

    private val cateViewModel by activityViewModels<CateViewModel>()
    private val mealViewModel by activityViewModels<MealsViewModel>()


    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!


    //The MainActivity class uses the "viewModels()" delegate from the Android KTX library
    // to obtain an instance of the "CateViewModel" class,
    // which is responsible for managing the data of the categories.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        moveToSearchScreen()
    }


    private fun init() {

        val categoryRecView = requireView().findViewById<RecyclerView>(R.id.cate_rec)
        val mealRecView = requireView().findViewById<RecyclerView>(R.id.meal_rec)

        //The "viewModel.getCategories()"
        // method call fetches the categories from the API and stores them in the "HomeViewModel".
        cateViewModel.getCategories()
        val categoriesAdapter = CateAdapter()


        //The "viewModel.getMeals()"
        // method call fetches the meals from the API and stores them in the "HomeViewModel".
        mealViewModel.getMeals()
        val mealsAdapter = MealAdapter()


        //The lifecycleScope.launch block is a coroutine that runs in the lifecycle scope of the MainActivity.
        // It collects the categories from the "CateViewModel"
        // (_categories) and updates the RecyclerView by calling the "submitList" method of the "CateAdapter" class.
        lifecycleScope.launch{
            cateViewModel._categories.collect{

                // The "submitList" method is a convenient method
                // provided by the "ListAdapter" class from the Android architecture components library.
                // It updates the list of items in the adapter and calculates the difference between the old and new lists,
                // which is used to update the UI efficiently.
                // The "it?.categories" expression is checking if "it" is not null and accessing the "categories" property of the "it" object,
                // which is likely a data class that contains the list of categories.
                categoriesAdapter.submitList(it?.categories)
                categoryRecView.adapter = categoriesAdapter
            }
        }


        lifecycleScope.launch {
            mealViewModel._meals.collect{

                mealsAdapter.submitList(it?.meals)
                mealRecView.adapter = mealsAdapter
            }
        }

        categoriesAdapter.onItemClickListener = object: CateAdapter.OnItemClickListener{
            override fun onItemClickListener(position: Int, item: CategoriesItem) {
                val intent = Intent(requireContext(), DetailsCategoryActivity::class.java)
                intent.putExtra("image", item.strCategoryThumb)
                intent.putExtra("details", item.strCategoryDescription)
                startActivity(intent)
            }
        }

        mealsAdapter.onItemClickListener = object: MealAdapter.OnItemClickListener{
            override fun onItemClickListener(position: Int, item: MealsItem) {
                val intent = Intent(requireContext(), DetailsMealsFragment::class.java)
                intent.putExtra("image", item.strMealThumb)
                intent.putExtra("details", item.strInstructions)
                startActivity(intent)
            }
        }
    }

    private fun moveToSearchScreen() {
        binding.searchBoxMainId.setOnClickListener {
            navController= Navigation.findNavController(it)
            navController.navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }
}