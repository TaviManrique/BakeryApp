package com.manriquetavi.bakeryapp.domain.use_cases.firestore

import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_categories.GetAllCategories
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_foods.GetAllFoods
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_foods_selected_category.GetAllFoodsSelectedCategory
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_all_promotions.GetAllPromotions
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_recommendations.GetRecommendations
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.get_selected_food.GetSelectedFood
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders.AddOrder
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders.GetAllOrderByUser
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.orders.GetSelectedOrder
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.search_foods.SearchFoods
import com.manriquetavi.bakeryapp.domain.use_cases.firestore.user.GetUserDetails

data class UseCasesFirestore(
    val getUserDetails: GetUserDetails,
    val searchFoods: SearchFoods,
    val getSelectedFood: GetSelectedFood,
    val getAllCategories: GetAllCategories,
    val getAllPromotions: GetAllPromotions,
    val getRecommendations: GetRecommendations,
    val getAllFoodsSelectedCategory: GetAllFoodsSelectedCategory,
    val getAllFoods: GetAllFoods,
    val addOrder: AddOrder,
    val getAllOrderByUser: GetAllOrderByUser,
    val getSelectedOrder: GetSelectedOrder
)