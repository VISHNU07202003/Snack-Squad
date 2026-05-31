package com.snacksquad.app.domain.repository

import com.snacksquad.app.domain.models.Category
import com.snacksquad.app.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getCategories(): Flow<List<Category>>
    fun getFeaturedProducts(): Flow<List<Product>>
    fun getProductsByCategory(categoryId: String): Flow<List<Product>>
    fun getProductById(productId: String): Flow<Product?>
    fun searchProducts(query: String): Flow<List<Product>>
}
