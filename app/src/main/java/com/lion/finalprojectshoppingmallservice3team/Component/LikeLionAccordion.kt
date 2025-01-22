package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LikeLionAccordion(
    title: String,
    content: @Composable () -> Unit,
    scrollState: ScrollState? = null,
    coroutineScope: CoroutineScope? = null,
    initiallyExpanded: Boolean = false
) {
    val expanded = remember { mutableStateOf(initiallyExpanded) }
    var rowOffsetY by remember { mutableStateOf(0) }
    var rowHeight by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .onGloballyPositioned { coordinates ->
                rowOffsetY = coordinates.positionInParent().y.toInt()
                rowHeight = coordinates.size.height
            }
    ) {
        // Header: 제목과 화살표 아이콘
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded.value = !expanded.value
                    if (expanded.value && scrollState != null && coroutineScope != null) {
                        coroutineScope.launch {
                            val scrollToPosition = (rowOffsetY - 1 * rowHeight).coerceAtLeast(0)
                            scrollState.animateScrollTo(scrollToPosition)
                        }
                    }
                }
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            // 화살표 아이콘: 펼쳐짐 상태에 맞게 아이콘 변경
            val icon = if (expanded.value) {
                R.drawable.arrow_drop_up_24px
            } else {
                R.drawable.arrow_drop_down_24px
            }
            Icon(
                painter = painterResource(id = icon),
                contentDescription = if (expanded.value) "Collapse" else "Expand"
            )
        }

        // Content: 펼쳐진 상태일 때만 보여줌
        if (expanded.value) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(bottom = 16.dp)
            ) {
                content()
            }
        }

        LikeLionDivider()
    }
}
