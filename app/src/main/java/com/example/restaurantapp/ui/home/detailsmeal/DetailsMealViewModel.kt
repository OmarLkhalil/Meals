package com.example.restaurantapp.ui.home.detailsmeal

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel


import com.example.domain.entity.FavoriteMeal
import com.example.domain.repo.AuthenticationRepo
import com.example.domain.repo.FavoriteMealRepository
import com.example.domain.repo.MealDetailsRepo


import com.example.restaurantapp.R


import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener


import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsMealViewModel @Inject constructor(
     private val mealDetailsRepo: MealDetailsRepo,
     private val favoriteMealsRepo: FavoriteMealRepository,
     authenticationRepo: AuthenticationRepo,
) : ViewModel() {

     lateinit var context: Context


     private val userId: String = authenticationRepo.getUser()?.id ?: ""

     fun getMealsDetails(mealId: String) = mealDetailsRepo.getDetailsResult(mealId)

     suspend fun toggleFavoriteMeal(favoriteMeal: FavoriteMeal, onSuccessListener: OnSuccessListener<Void>, onFailureListener: OnFailureListener) {
          if (userId.isEmpty()) {
               // If the user is not logged in, show a dialog asking them to log in
               AlertDialog.Builder(context)
                    .setTitle(R.string.login_dialog_title)
                    .setMessage(R.string.login_dialog_message)
                    .setPositiveButton(R.string.login_dialog_login) { dialog, _ ->
                         dialog.dismiss()
                         // Navigate to the login screen
                    }
                    .setNegativeButton(R.string.login_dialog_cancel) { dialog, _ ->
                         dialog.dismiss()
                    }
                    .show()
          } else {
               if (favoriteMeal.isFavored) {
                    favoriteMealsRepo.deleteFavoriteMeal(favoriteMeal, userId, onSuccessListener, onFailureListener)
               } else {
                    favoriteMealsRepo.addFavoriteMeal(favoriteMeal, userId, onSuccessListener, onFailureListener)
               }
          }
     }
}
