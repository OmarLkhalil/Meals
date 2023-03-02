package com.example.restaurantapp.ui.home.favorite

import android.os.Bundle


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels


import com.example.restaurantapp.databinding.FragmentFavoriteBinding


import dagger.hilt.android.AndroidEntryPoint


import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class FavoriteMealsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteMealsViewModel by viewModels()
    private lateinit var adapter: FavoriteMealsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteMealsAdapter(requireContext())

        binding.favoriteRec.adapter = adapter

        runBlocking {
            viewModel.getFavoriteMeals().observe(viewLifecycleOwner) { favoriteMeals ->
                adapter.submitList(favoriteMeals)
            }
        }
    }
}