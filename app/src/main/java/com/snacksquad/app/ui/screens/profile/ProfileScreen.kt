package com.snacksquad.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacksquad.app.domain.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    user: User,
    onNavigateToOrderHistory: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(user.name.first().toString(), fontSize = 48.sp, color = MaterialTheme.colorScheme.onPrimary)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(user.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(user.email, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column {
                        ProfileMenuItem(icon = Icons.Filled.History, title = "Order History", onClick = onNavigateToOrderHistory)
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        ProfileMenuItem(icon = Icons.Filled.LocationOn, title = "Saved Addresses", onClick = { })
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        ProfileMenuItem(icon = Icons.Filled.Payment, title = "Payment Methods", onClick = { })
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        ProfileMenuItem(icon = Icons.Filled.Favorite, title = "Wishlist", onClick = { })
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        ProfileMenuItem(icon = Icons.Filled.Notifications, title = "Notifications", onClick = { })
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                TextButton(
                    onClick = onLogoutClick,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Text("Log Out", color = MaterialTheme.colorScheme.error, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, fontSize = 16.sp, modifier = Modifier.weight(1f))
        Icon(Icons.Filled.ChevronRight, contentDescription = "Go", tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f))
    }
}
