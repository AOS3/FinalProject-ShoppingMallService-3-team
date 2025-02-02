package com.lion.finalprojectshoppingmallservice3team

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Star
import com.lion.finalprojectshoppingmallservice3team.Component.BottomNavigationItemData

fun bottomNavItems(isLoggedIn: Boolean): List<BottomNavigationItemData> {
    return listOf(
        BottomNavigationItemData(
            icon = Icons.Default.Home,
            label = "Home",
            route = "home"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Mood,
            label = "Creator",
            route = "creatorMain"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Star,
            label = "My",
            route = "myFavorite"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Shop,
            label = "Shop",
            route = "shop"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Person,
            label = "My Page",
            route = if (isLoggedIn) "loginMyPage" else "logoutMyPage"
        )
    )
}