package com.snacksquad.app.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class CheckoutStep {
    ADDRESS, PAYMENT, REVIEW
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    onBackClick: () -> Unit,
    onPlaceOrder: () -> Unit
) {
    var currentStep by remember { mutableStateOf(CheckoutStep.ADDRESS) }
    
    // Form States
    var address by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout") },
                navigationIcon = {
                    IconButton(onClick = {
                        when (currentStep) {
                            CheckoutStep.ADDRESS -> onBackClick()
                            CheckoutStep.PAYMENT -> currentStep = CheckoutStep.ADDRESS
                            CheckoutStep.REVIEW -> currentStep = CheckoutStep.PAYMENT
                        }
                    }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 16.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = {
                            when (currentStep) {
                                CheckoutStep.ADDRESS -> currentStep = CheckoutStep.PAYMENT
                                CheckoutStep.PAYMENT -> currentStep = CheckoutStep.REVIEW
                                CheckoutStep.REVIEW -> onPlaceOrder()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                    ) {
                        Text(
                            text = if (currentStep == CheckoutStep.REVIEW) "Place Order" else "Continue",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Progress Indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StepIndicator("Address", currentStep.ordinal >= CheckoutStep.ADDRESS.ordinal)
                Divider(modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                StepIndicator("Payment", currentStep.ordinal >= CheckoutStep.PAYMENT.ordinal)
                Divider(modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                StepIndicator("Review", currentStep.ordinal >= CheckoutStep.REVIEW.ordinal)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Step Content
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                when (currentStep) {
                    CheckoutStep.ADDRESS -> {
                        Text("Delivery Address", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = address,
                            onValueChange = { address = it },
                            label = { Text("Street Address") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        // More fields would go here...
                    }
                    CheckoutStep.PAYMENT -> {
                        Text("Payment Method", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = cardNumber,
                            onValueChange = { cardNumber = it },
                            label = { Text("Card Number") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    CheckoutStep.REVIEW -> {
                        Text("Order Review", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Delivering to", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                Text(if (address.isEmpty()) "123 Main St" else address, fontWeight = FontWeight.Bold)
                                Divider(modifier = Modifier.padding(vertical = 12.dp))
                                Text("Paying with", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                Text("Card ending in 1234", fontWeight = FontWeight.Bold)
                                Divider(modifier = Modifier.padding(vertical = 12.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Total", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    Text("$24.98", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StepIndicator(label: String, isActive: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            Icons.Filled.CheckCircle,
            contentDescription = null,
            tint = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            modifier = Modifier.size(32.dp)
        )
        Text(label, fontSize = 12.sp, fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal)
    }
}
