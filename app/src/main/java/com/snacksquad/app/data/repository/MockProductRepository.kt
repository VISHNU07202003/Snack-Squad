package com.snacksquad.app.data.repository

import com.snacksquad.app.domain.models.Category
import com.snacksquad.app.domain.models.Product
import com.snacksquad.app.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockProductRepository : ProductRepository {

    private val categories = listOf(
        Category("1", "Popcorn", "🍿"),
        Category("2", "Chocolate", "🍫"),
        Category("3", "Nuts", "🥜"),
        Category("4", "Drinks", "🧃"),
        Category("5", "Cookies", "🍪"),
        Category("6", "Spicy", "🌶️"),
        Category("7", "Healthy", "🌿")
    )

    private val products = listOf(
        Product(
            id = "p1",
            name = "Jalapeno Cheddar Popcorn",
            brand = "Kernel King",
            description = "Spicy, cheesy, and perfectly crunchy. The ultimate late-night study fuel.",
            price = 4.99,
            imageUrls = listOf("https://images.unsplash.com/photo-1585653040243-7f2a1b926084?auto=format&fit=crop&q=80&w=400"),
            categoryId = "1",
            tags = listOf("Spicy", "Gluten-Free"),
            stock = 50,
            rating = 4.8f,
            reviewCount = 142
        ),
        Product(
            id = "p2",
            name = "Dark Chocolate Sea Salt Almonds",
            brand = "Nutty Delights",
            description = "Premium roasted almonds covered in 70% dark chocolate with a hint of sea salt.",
            price = 6.49,
            imageUrls = listOf("https://images.unsplash.com/photo-1604242692760-2f7b0c26856d?auto=format&fit=crop&q=80&w=400"),
            categoryId = "2",
            tags = listOf("Vegan", "Sweet"),
            stock = 120,
            rating = 4.9f,
            reviewCount = 310
        ),
        Product(
            id = "p3",
            name = "Sparkling Mango Energy Water",
            brand = "FizzWave",
            description = "Zero sugar, natural caffeine from green tea, and refreshing mango flavor.",
            price = 2.99,
            imageUrls = listOf("https://images.unsplash.com/photo-1556881286-fc6915169721?auto=format&fit=crop&q=80&w=400"),
            categoryId = "4",
            tags = listOf("Zero Sugar", "Energy"),
            stock = 200,
            rating = 4.5f,
            reviewCount = 89
        ),
        Product(
            id = "p4",
            name = "Spicy Lime Tortilla Chips",
            brand = "Crunch Co.",
            description = "Intense lime and chili flavor on thick-cut corn tortilla chips.",
            price = 3.49,
            imageUrls = listOf("https://images.unsplash.com/photo-1584285407026-cda1b9cc01a3?auto=format&fit=crop&q=80&w=400"),
            categoryId = "6",
            tags = listOf("Spicy", "Crunchy"),
            stock = 15,
            rating = 4.6f,
            reviewCount = 205
        )
    )

    override fun getCategories(): Flow<List<Category>> = flowOf(categories)

    override fun getFeaturedProducts(): Flow<List<Product>> = flowOf(products)

    override fun getProductsByCategory(categoryId: String): Flow<List<Product>> = 
        flowOf(products.filter { it.categoryId == categoryId })

    override fun getProductById(productId: String): Flow<Product?> = 
        flowOf(products.find { it.id == productId })

    override fun searchProducts(query: String): Flow<List<Product>> = 
        flowOf(products.filter { it.name.contains(query, ignoreCase = true) || it.tags.any { tag -> tag.contains(query, ignoreCase = true) } })
}
