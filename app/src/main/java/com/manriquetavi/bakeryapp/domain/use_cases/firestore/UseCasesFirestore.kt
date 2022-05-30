package com.manriquetavi.bakeryapp.domain.use_cases.firestore

import com.manriquetavi.bakeryapp.domain.use_cases.firestore.search_foods.SearchFoods
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.user.GetUserDetails

data class UseCasesFirestore(
    val getUserDetails: GetUserDetails,
    val searchFoods: SearchFoods
)