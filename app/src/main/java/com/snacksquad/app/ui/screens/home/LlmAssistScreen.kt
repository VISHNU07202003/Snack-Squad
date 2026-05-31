package com.snacksquad.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacksquad.app.domain.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LlmAssistScreen(
    onBack: () -> Unit,
    recommendedProducts: List<Product>, // This would normally come from ViewModel
    onProductClick: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    
    // In a real implementation, this would be observed from a ViewModel
    var showRecommendations by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Snack Guide") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("What are you craving?") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { 
                        if (query.isNotEmpty()) {
                            showRecommendations = true 
                        }
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(50))
                ) {
                    Icon(Icons.Filled.Send, contentDescription = "Send", tint = MaterialTheme.colorScheme.onTertiary)
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            item {
                ChatBubble(
                    message = "Hi Leo! What are you craving today? Tell me what you're doing (e.g. 'movie night', 'studying') and I'll find the perfect snack.",
                    isAi = true
                )
            }
            
            if (showRecommendations) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    ChatBubble(
                        message = query,
                        isAi = false
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                item {
                    ChatBubble(
                        message = "Here are some recommendations based on that!",
                        isAi = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(recommendedProducts) { product ->
                            Box(modifier = Modifier.width(200.dp)) {
                                ProductCard(product = product, onClick = { onProductClick(product.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isAi: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isAi) Arrangement.Start else Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isAi) 0.dp else 16.dp,
                        bottomEnd = if (isAi) 16.dp else 0.dp
                    )
                )
                .background(if (isAi) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
        ) {
            Text(
                text = message,
                color = if (isAi) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSecondary,
                fontSize = 16.sp
            )
        }
    }
}
