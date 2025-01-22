package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    onHomeClick: () -> Unit = {},
    onCreatorClick: () -> Unit = {},
    onMyClick: () -> Unit = {},
    onShopClick: () -> Unit = {},
    onMyPageClick: () -> Unit = {}
) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { onHomeClick() },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onCreatorClick() },
            icon = { Icon(Icons.Default.Mood, contentDescription = "Creator") },
            label = { Text("Creator") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onMyClick() },
            icon = { Icon(Icons.Default.Star, contentDescription = "My") },
            label = { Text("My") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onShopClick() },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop") },
            label = { Text("Shop") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { onMyPageClick() },
            icon = { Icon(Icons.Default.Person, contentDescription = "My Page") },
            label = { Text("My Page") }
        )
    }
}