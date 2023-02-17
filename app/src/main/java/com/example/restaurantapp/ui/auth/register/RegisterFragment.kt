package com.example.restaurantapp.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class RegisterFragment : Fragment(){

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding   : FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    val RC_SIGN_IN = 9001

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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken
                if (idToken != null) {
                    viewModel.firebaseAuthWithGoogle(idToken)
                } else {
                    viewModel.messageLiveData.value = "Failed to get ID token from Google sign-in"
                }
            } catch (e: ApiException) {
                viewModel.messageLiveData.value = "Google sign-in failed: ${e.localizedMessage}"
            }
        }
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


    }

    private fun initVariables(){

        val navController : NavController = findNavController()
        viewModel.navController = navController
        binding.vm = viewModel
        viewModel.binding = binding

    }

    private fun setUpGoogleAuth(){

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)


        binding.signWithGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        viewModel.googleSignInClient = googleSignInClient
    }
}

