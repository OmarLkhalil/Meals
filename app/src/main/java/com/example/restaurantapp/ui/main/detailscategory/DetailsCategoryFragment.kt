package com.example.restaurantapp.ui.main.detailscategory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.domain.util.Status
import com.example.restaurantapp.databinding.FragmentCategoryDetailsBinding
import com.example.restaurantapp.ui.main.home.meals.MealAdapter
import com.restaurantapp.domain.entity.CategoriesItem
import com.restaurantapp.domain.entity.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsCategoryFragment : Fragment() {

    lateinit var category: CategoriesItem
    private lateinit var navController: NavController
    private lateinit var binding: FragmentCategoryDetailsBinding
    private val args: DetailsCategoryFragmentArgs by navArgs()
    private val mealsViewModel by activityViewModels<DetailsCategoryViewModel>()
    private lateinit var mealsAdapter : MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        category = args.category!!
        mealsAdapter = MealAdapter(requireContext())
        mealsAdapter.navigate(category)
        lifecycleScope.launch {
            category.strCategory?.let { loadMealsDetails(it) }
        }
    }

    private suspend fun loadMealsDetails(cateId: String) = withContext(Dispatchers.IO) {

        mealsViewModel.getCateDetails(cateId)
            .onEach { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        val mealsList=result.data
                        mealsAdapter.submitList(mealsList)
                        }
                    else -> {}
                }

            }

            .catch { exception ->
                Log.e("Details Cate Fragment", "Error loading cate details", exception)
            }
            .launchIn(lifecycleScope)

        binding.mealsContainerId.adapter = mealsAdapter

    }
}