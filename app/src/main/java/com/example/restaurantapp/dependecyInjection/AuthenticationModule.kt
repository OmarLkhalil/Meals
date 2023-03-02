package com.example.restaurantapp.dependecyInjection

import com.example.data.repo.AuthenticationRepoImpl
import com.example.domain.repo.AuthenticationRepo
import com.example.domain.usecase.SignInUseCase
import com.example.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule{

    @Provides
    fun provideAuth(): AuthenticationRepo {
        return AuthenticationRepoImpl()
    }

    @Provides
    fun getSignInUseCase(authenticationRepo: AuthenticationRepo): SignInUseCase{
        return SignInUseCase(authenticationRepo)
    }

    @Provides
    fun getSignUpUseCase(authenticationRepo: AuthenticationRepo): SignUpUseCase{
        return SignUpUseCase(authenticationRepo)
    }

}