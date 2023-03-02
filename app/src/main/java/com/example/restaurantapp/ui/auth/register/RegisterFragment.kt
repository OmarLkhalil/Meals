package com.example.restaurantapp.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.AppUser
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class RegisterFragment : Fragment(){

    private lateinit var binding   : FragmentRegisterBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        initVariables()
        setUpListeners()
        return binding.root
    }


    private fun setUpListeners(){

        binding.SignInTxt.setOnClickListener{
            viewModel.navigateToLoginFragment(it)
        }

        binding.SignUpButton.setOnClickListener{
            viewModel.createAccount()
        }

        binding.continueAsGuest.setOnClickListener{
            viewModel.continueAsGuest()
        }

        binding.SigninGoogle.setOnClickListener{
            viewModel.signInWithGoogle()
        }

    }

    private fun initVariables(){

        val navController : NavController = findNavController()
        viewModel.navController = navController
        binding.vm = viewModel
        viewModel.binding = binding
        viewModel.context = requireContext()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        viewModel.googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RegisterViewModel.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                // Signed in successfully, show authenticated UI.
                // You can use the account object to get the user's email and name
                // Signed in successfully, show authenticated UI.
                val user = AppUser(
                    id = null,
                    name = account?.displayName,
                    email = account?.email,
                    favoriteMealId = null
                )
                viewModel.signUpUseCase.execute(user,
                    onSuccess = {
                        viewModel.navController.navigate(R.id.action_registerFragment_to_mainFragment)
                    },
                    onFailure = { error ->
                        viewModel.showError(error.localizedMessage ?: "Unknown error")
                    }
                )
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w("RegisterFragment", "signInResult:failed code=" + e.statusCode)
                viewModel.showError("Google sign in failed")
            }
        }
    }
}