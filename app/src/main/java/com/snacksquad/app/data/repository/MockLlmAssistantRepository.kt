package com.snacksquad.app.data.repository

import com.snacksquad.app.domain.models.Product
import com.snacksquad.app.domain.repository.LlmAssistantRepository
import com.snacksquad.app.domain.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first

class MockLlmAssistantRepository(private val productRepository: ProductRepository) : LlmAssistantRepository {
    override fun getSnackRecommendations(query: String): Flow<List<Product>> = flow {
        // Simulate LLM processing time
        delay(1500)
        
        val allProducts = productRepository.getFeaturedProducts().first()
        
        // Very basic mock matching logic for the prototype
        val recommended = if (query.contains("spicy", ignoreCase = true) || query.contains("late", ignoreCase = true)) {
            allProducts.filter { it.tags.contains("Spicy") }
        } else if (query.contains("sweet", ignoreCase = true)) {
            allProducts.filter { it.tags.contains("Sweet") }
        } else {
            allProducts.take(2) // Default recommendations
        }
        
        emit(recommended)
    }
}
