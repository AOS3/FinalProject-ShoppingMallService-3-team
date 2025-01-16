package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun LikeLionBottomNavigation(
    navController: NavController,
    items: List<BottomNavigationItemData>,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route


    NavigationBar (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}


data class BottomNavigationItemData(
    val icon: ImageVector,
    val label: String,
    val route: String
)