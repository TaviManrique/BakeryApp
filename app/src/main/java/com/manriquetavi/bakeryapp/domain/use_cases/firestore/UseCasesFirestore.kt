package com.manriquetavi.bakeryapp.domain.use_cases.firestore

import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_categories.GetAllCategories
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_promotions.GetAllPromotions
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_recommendations.GetRecommendations
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_selected_food.GetSelectedFood
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.search_foods.SearchFoods
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.user.GetUserDetails

data class UseCasesFirestore(
    val getUserDetails: GetUserDetails,
    val searchFoods: SearchFoods,
    val getSelectedFood: GetSelectedFood,
    val getAllCategories: GetAllCategories,
    val getAllPromotions: GetAllPromotions,
    val getRecommendations: GetRecommendations
)