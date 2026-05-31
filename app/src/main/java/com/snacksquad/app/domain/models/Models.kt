package com.snacksquad.app.domain.models

data class User(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String? = null
)

data class Product(
    val id: String,
    val name: String,
    val brand: String,
    val description: String,
    val price: Double,
    val imageUrls: List<String>,
    val categoryId: String,
    val tags: List<String>,
    val stock: Int,
    val rating: Float,
    val reviewCount: Int,
    val availableSizes: List<String> = listOf("Standard")
)

data class CartItem(
    val productId: String,
    val quantity: Int,
    val selectedSize: String
)

data class Address(
    val id: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
    val label: String // e.g., "Home", "Work"
)

enum class OrderStatus {
    PENDING, PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
}

data class Order(
    val id: String,
    val items: List<CartItem>,
    val total: Double,
    val address: Address,
    val status: OrderStatus,
    val createdAt: Long,
    val estimatedDeliveryTime: Long
)

data class Category(
    val id: String,
    val name: String,
    val emoji: String
)

data class Review(
    val id: String,
    val userId: String,
    val userName: String,
    val rating: Float,
    val comment: String,
    val createdAt: Long
)
