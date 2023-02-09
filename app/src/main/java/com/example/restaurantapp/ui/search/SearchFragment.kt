package com.example.restaurantapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.util.*
import com.example.restaurantapp.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch

class SearchFragment: Fragment(){

    private lateinit var searchedMealsAdapter: ResultsAdapter
    private val searchVM by activityViewModels<SearchViewModel>()


    private var _binding: FragmentSearchBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        searchAction()
        initRecyclerView()
    }

    private fun init() {
        showKeyboard(requireContext(), binding.searchBoxId)
        searchedMealsAdapter = ResultsAdapter(requireContext())
    }

    private fun searchAction() {
        binding.searchBoxId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                lifecycleScope.launch { searchVM.getSearchedMeals(p0.toString()).observe(viewLifecycleOwner) {
                    when (it.status) {
                        Status.LOADING -> {
                            binding.searchProgressBar.show()
                        }
                        Status.SUCCESS -> {
                            val mealsList = it.data
                            binding.noMealsText.showIf {
                                mealsList == null
                            }
                            searchedMealsAdapter.submitList(mealsList)
                            binding.searchProgressBar.hide()
                        }
                        Status.FAIL -> {/*do nothing*/
                        }
                    }
                }
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun initRecyclerView() {
        binding.resultsContent.apply {
            setHasFixedSize(true)
            adapter = searchedMealsAdapter
        }
    }

}