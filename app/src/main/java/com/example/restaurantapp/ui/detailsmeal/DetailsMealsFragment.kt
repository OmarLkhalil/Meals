package com.example.restaurantapp.ui.detailsmeal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import com.example.domain.entity.MealsItem
import com.example.domain.util.Status
import com.example.domain.util.hideKeyboard
import com.example.restaurantapp.databinding.FragmentMealsDetailsBinding
import kotlinx.coroutines.launch


class DetailsMealsFragment : Fragment() {

    private lateinit var navController: NavController
    private var _binding: FragmentMealsDetailsBinding? = null
    private val binding
        get() = _binding!!
    private val arguments: DetailsMealsFragmentArgs by navArgs()
    private val mealsViewModel by activityViewModels<DetailsMealViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideKeyboard()
        navController = Navigation.findNavController(view)
        val mealId = arguments.mealId
        lifecycleScope.launch{
            loadMealsDetails(mealId)
        }
    }

    private suspend fun loadMealsDetails(mealId: String) {
        mealsViewModel.getMealsDetails(mealId).observe(viewLifecycleOwner) {
            val meals = it.data

            if (it.status == Status.SUCCESS) {
                val meal= meals!![0]
                if (meal != null) {
                    initDetails(meal)
                    openLink(meal)
                    openYoutube(meal)
                }
            }
        }
    }

    private fun initDetails(meal: MealsItem) {
        val imageUrl = meal.strMealThumb
        Glide.with(this).load(imageUrl).into(binding.imvHeader)

        val mealName = meal.strMeal
        binding.mealName.text = mealName

        val instructionText = meal.strInstructions
        binding.instructions.text = instructionText


        val categoryName = meal.strCategory
        binding.mealCate.text = categoryName



        addIngredientToIngredients(meal)
        addMeasures(meal)
    }

    private fun putIngredient(gradients: String) {
        binding.ingredient.text = gradients
    }

    private fun getIngredient(ingredients: MutableList<String?>) {
        val ingredientText = StringBuilder()
        for (ingredient: String? in ingredients) {
            if (ingredient != null) {
                if (ingredient != " " && ingredient.isNotEmpty()) {
                    ingredientText.append("\n \u2022$ingredient")
                }
            }
            putIngredient(ingredientText.toString())
        }
    }

    private fun addIngredientToIngredients(meal: MealsItem) {
        val ingredients = mutableListOf<String?>()
        ingredients.add(meal.strIngredient1)
        ingredients.add(meal.strIngredient2)
        ingredients.add(meal.strIngredient3)
        ingredients.add(meal.strIngredient4)
        ingredients.add(meal.strIngredient5)
        ingredients.add(meal.strIngredient6)
        ingredients.add(meal.strIngredient7)
        ingredients.add(meal.strIngredient8)
        ingredients.add(meal.strIngredient9)
        ingredients.add(meal.strIngredient10)
        ingredients.add(meal.strIngredient11)
        ingredients.add(meal.strIngredient12)
        ingredients.add(meal.strIngredient13)
        ingredients.add(meal.strIngredient14)
        ingredients.add(meal.strIngredient15)

        getIngredient(ingredients)
    }

    private fun addMeasures(meal: MealsItem) {
        val measures = mutableListOf<String?>()
        measures.add(meal.strMeasure1)
        measures.add(meal.strMeasure2)
        measures.add(meal.strMeasure3)
        measures.add(meal.strMeasure4)
        measures.add(meal.strMeasure5)
        measures.add(meal.strMeasure6)
        measures.add(meal.strMeasure7)
        measures.add(meal.strMeasure8)
        measures.add(meal.strMeasure9)
        measures.add(meal.strMeasure10)
        measures.add(meal.strMeasure11)
        measures.add(meal.strMeasure12)
        measures.add(meal.strMeasure13)
        measures.add(meal.strMeasure14)
        measures.add(meal.strMeasure15)
        getMeasures(measures)
    }

    private fun getMeasures(measuresList: MutableList<String?>) {
        val measureText = StringBuilder()
        for (measure: String? in measuresList) {
            if (measure != null) {
                if (measure != " " && measure.isNotEmpty()) {
                    measureText.append("\n \u2022$measure")
                }
            }
            putMeasuresText(measureText.toString())
        }
    }

    private fun openLink(meal: MealsItem) {
        binding.source.setOnClickListener {
            implicitIntent(meal.strSource as String)
        }
    }

    private fun openYoutube(meal: MealsItem) {
        binding.youtube.setOnClickListener {
            meal.strYoutube?.let { it1 -> implicitIntent(it1) }
        }
    }

    private fun implicitIntent(uri: String) {
        val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        activity?.startActivity(implicitIntent)
    }
    private fun putMeasuresText(measureText: String) {
        binding.measure.text = measureText
    }

}
