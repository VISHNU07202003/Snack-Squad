package com.snacksquad.app.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacksquad.app.domain.models.Product
import com.snacksquad.app.ui.screens.home.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    initialQuery: String = "",
    searchResults: List<Product>,
    onBackClick: () -> Unit,
    onProductClick: (String) -> Unit,
    onSearchQueryChange: (String) -> Unit
) {
    var query by remember { mutableStateOf(initialQuery) }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                    SearchBar(
                        query = query,
                        onQueryChange = { 
                            query = it
                            onSearchQueryChange(it) 
                        },
                        onSearch = { onSearchQueryChange(it) },
                        active = false,
                        onActiveChange = { },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Search snacks...") },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                        trailingIcon = {
                            Row {
                                IconButton(onClick = { /* Open Voice Search */ }) {
                                    Icon(Icons.Filled.Mic, contentDescription = "Voice Search")
                                }
                                IconButton(onClick = { /* Open Filter Sheet */ }) {
                                    Icon(Icons.Filled.FilterList, contentDescription = "Filter")
                                }
                            }
                        }
                    ) {}
                }
            }
        }
    ) { paddingValues ->
        if (searchResults.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🔍", style = MaterialTheme.typography.displayLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("No snacks found", style = MaterialTheme.typography.headlineSmall)
                    Text("Try searching for something else", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                }
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 16.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(searchResults) { product ->
                    ProductCard(product = product, onClick = { onProductClick(product.id) })
                }
            }
        }
    }
}
