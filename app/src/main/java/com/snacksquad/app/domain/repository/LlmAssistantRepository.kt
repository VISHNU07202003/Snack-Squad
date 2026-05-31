package com.snacksquad.app.domain.repository

import com.snacksquad.app.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface LlmAssistantRepository {
    fun getSnackRecommendations(query: String): Flow<List<Product>>
}
