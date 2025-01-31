package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun LikeLionBottomNavigation(
    navController: NavController,
    items: List<BottomNavigationItemData>,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar (
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(0.dp),

        containerColor = Color.LightGray.copy(alpha = 0.5f),
        contentColor = Color.Transparent,
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            items.forEach { item ->
                // 현재 항목이 선택된 상태인지 확인
                val selected = currentRoute == item.route


                // 드디어 리플 제거
                val noRippleInteractionSource = remember {
                    object : MutableInteractionSource {
                        override val interactions = MutableSharedFlow<Interaction>()
                        override suspend fun emit(interaction: Interaction) {}
                        override fun tryEmit(interaction: Interaction) = true
                    }
                }

                NavigationBarItem(
                    modifier = Modifier
                        .background(Color.Transparent),
                    icon = {
                        Icon(imageVector = item.icon,
                            modifier = Modifier
                                .size(28.dp)
                                .offset(y = (4).dp),
                            tint = if (selected) MainColor else Color.Black,
                            contentDescription = item.label) },
                    label = {
                        Text(text = item.label,
                            modifier = Modifier
                                .padding(0.dp)
                                .offset(y = (-4).dp),
                            fontSize = 10.sp,
                            color = if (selected) MainColor else Color.Black)
                    },
                    selected = selected,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                    ),

                    // 리플제거
                    interactionSource = noRippleInteractionSource,
                    onClick = {
                        // 현재 route가 선택된 route와 다를 경우
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                launchSingleTop = true  // 현재 화면을 재사용하여 새로운 화면을 추가하지 않음
                                restoreState = false  // 이전 상태를 복원하지 않음
                                popUpTo(currentRoute!!) {  // 스택을 초기화하여 해당 화면을 최상위로 만듬
                                    inclusive = true  // 해당 route 포함하여 이전 화면 제거
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}


data class BottomNavigationItemData(
    val icon: ImageVector,
    val label: String,
    val route: String
)
