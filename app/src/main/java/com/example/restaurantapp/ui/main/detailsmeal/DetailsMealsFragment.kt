package com.example.restaurantapp.ui.main.detailsmeal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.entity.FavoriteMeal
import com.example.domain.entity.MealsItem
import com.example.domain.util.Status
import com.example.domain.util.hideKeyboard
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentMealsDetailsBinding
import com.example.restaurantapp.ui.main.favorite.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsMealsFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentMealsDetailsBinding
    private val args: DetailsMealsFragmentArgs by navArgs()
    private val mealsViewModel by activityViewModels<DetailsMealViewModel>()
    private val favoriteMeals by activityViewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealsDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideKeyboard()
        navController = Navigation.findNavController(view)
        lifecycleScope.launch {
            loadMealsDetails(args.mealId)
            getFavorites()
            setupFavoriteButton()

        }
    }

    private suspend fun loadMealsDetails(mealId: String) = withContext(Dispatchers.IO) {
        mealsViewModel.getMealsDetails(mealId)
            .onEach { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        val meal = result.data?.get(0)
                        if (meal != null) {
                            initDetails(meal)
                            openLink(meal)
                            openYoutube(meal)
                        }
                    }
                    else -> {
                    }
                }
            }.catch { exception ->
                Log.e("DetailsMealsFragment", "Error loading meal details", exception)
            }
            .launchIn(lifecycleScope)
    }

    private fun initDetails(meal: MealsItem) {

        if (meal.strMealThumb != null) {
            Glide.with(this).load(meal.strMealThumb).into(binding.imvHeader)
        }
        binding.mealName.text = meal.strMeal
        binding.instructions.text = meal.strInstructions
        binding.mealCate.text = meal.strCategory
        binding.ingredient.text = getIngredients(meal)
    }

    private fun getIngredients(meal: MealsItem): String {
        val ingredients = listOf(
            meal.strIngredient1,
            meal.strIngredient2,
            meal.strIngredient3,
            meal.strIngredient4,
            meal.strIngredient5,
            meal.strIngredient6,
            meal.strIngredient7,
            meal.strIngredient8,
            meal.strIngredient9,
            meal.strIngredient10,
            meal.strIngredient11,
            meal.strIngredient12,
            meal.strIngredient13,
            meal.strIngredient14,
            meal.strIngredient15,
            meal.strIngredient16,
            meal.strIngredient17,
            meal.strIngredient18,
            meal.strIngredient19,
            meal.strIngredient20
        ).filter { it.toString().isNotBlank() }
        return ingredients.joinToString(separator = "\n")
    }

    private fun openLink(meal: MealsItem) {
        binding.source.setOnClickListener {
            meal.strSource?.let { it1 -> implicitIntent(it1) }
        }
    }

    private fun openYoutube(meal: MealsItem) {
        binding.youtube.setOnClickListener {
            meal.strYoutube?.let { link ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
            }
        }
    }

    private fun implicitIntent(uri: String) {
        val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        activity?.startActivity(implicitIntent)
    }

    private val favoriteChecked   = R.drawable.favorite
    private val favoriteUnChecked = R.drawable.favorite_unchecked
    private var isFavorite: Boolean = false


    private fun setupFavoriteButton() {
        binding.favorite.setOnClickListener {
            lifecycleScope.launch {
                isFavorite = if (!isFavorite) {
                    binding.favorite.setBackgroundResource(favoriteChecked)
                    args.meal?.isFavored = true
                    args.meal?.let { meal ->
                        favoriteMeals.addMealToFavorite(meal).collect {
                            when (it.status) {
                                Status.LOADING -> {}
                                Status.SUCCESS -> {
                                    Log.d("FAVORITE", "Added to Favorites")
                                    Toast.makeText(context, "Added to Favorites", Toast.LENGTH_LONG).show()
                                }
                                Status.FAIL -> {
                                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                    true
                } else {
                    binding.favorite.setBackgroundResource(favoriteUnChecked)
                    args.meal?.idMeal?.let { id ->
                        favoriteMeals.removeMealFromFavorite(id)
                    }
                    false
                } } } }
    private lateinit var favoriteList: MutableList<String>
    private lateinit var favorites: List<FavoriteMeal>

    private suspend fun getFavorites() {
        favoriteList = mutableListOf()
        favorites = listOf()
        favoriteMeals.getAllFavorites().collect {
            when (it.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    favorites = it.data!!
                    for (meal: FavoriteMeal in favorites) {
                        meal.idMeal?.let { id ->
                            favoriteList.add(id)
                            Log.d("Favorite", "Add meal result: ${it.status}")
                        }
                    }
                    isFavorite = if (favoriteList.contains(args.meal?.idMeal)) {
                        binding.favorite.setBackgroundResource(favoriteChecked)
                        true
                    } else {
                        binding.favorite.setBackgroundResource(favoriteUnChecked)
                        false
                    }
                }
                Status.FAIL -> {}
            }
        }
    }
}