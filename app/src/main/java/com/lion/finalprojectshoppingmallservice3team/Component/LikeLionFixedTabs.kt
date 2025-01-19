package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun LikeLionFixedTabs(
    tabTitleWithCounts: List<Pair<String, Int>>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    divider: @Composable () -> Unit = {},
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = {},
        modifier = modifier.fillMaxWidth(),
        divider = divider
    ) {
        tabTitleWithCounts.forEachIndexed { index, (title, count) ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = "$title $count",
                        color = if (selectedTabIndex == index) MainColor else Color(0xFF49454F),
                    )
                },
            )
        }
    }
}