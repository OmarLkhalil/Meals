package com.example.domain.usecase

import com.example.domain.repo.CateRepo

class CateUseCase(private val cateRep: CateRepo) {
   suspend operator fun  invoke() = cateRep.getCategoriesFromApi()
}