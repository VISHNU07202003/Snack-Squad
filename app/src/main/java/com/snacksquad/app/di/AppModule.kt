package com.snacksquad.app.di

import com.snacksquad.app.data.repository.MockLlmAssistantRepository
import com.snacksquad.app.data.repository.MockProductRepository
import com.snacksquad.app.domain.repository.LlmAssistantRepository
import com.snacksquad.app.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository {
        return MockProductRepository()
    }

    @Provides
    @Singleton
    fun provideLlmAssistantRepository(
        productRepository: ProductRepository
    ): LlmAssistantRepository {
        return MockLlmAssistantRepository(productRepository)
    }
}
