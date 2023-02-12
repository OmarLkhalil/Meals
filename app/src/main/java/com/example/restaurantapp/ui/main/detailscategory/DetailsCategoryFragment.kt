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
import com.bumptech.glide.Glide
import com.example.domain.util.Status
import com.example.restaurantapp.databinding.FragmentCategoryDetailsBinding
import com.restaurantapp.domain.entity.CategoriesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsCategoryFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentCategoryDetailsBinding
    private val args: DetailsCategoryFragmentArgs by navArgs()
    private val mealsViewModel by activityViewModels<DetailsCategoryViewModel>()

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
        lifecycleScope.launch {
            loadMealsDetails(args.mealId)
        }
    }

    private suspend fun loadMealsDetails(cateId: String) = withContext(Dispatchers.IO) {
        mealsViewModel.getCateDetails(cateId)
            .onEach { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        val cate = result.data?.get(0)
                        if (cate != null) {
                            initDetails(cate)
                        }
                    }
                    else -> {
                    }
                }
            }.catch { exception ->
                Log.e("Details Cate Fragment", "Error loading cate details", exception)
            }
            .launchIn(lifecycleScope)
    }

    private fun initDetails(cate: CategoriesItem) {

        if (cate.strCategory != null) {
            Glide.with(this).load(cate.strCategoryThumb).into(binding.imvHeader)
        }
        binding.mealName.text = cate.strCategory
        binding.instructions.text = cate.strCategoryDescription
    }
}