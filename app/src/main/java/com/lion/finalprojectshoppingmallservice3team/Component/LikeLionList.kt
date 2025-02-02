package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier


@Composable
fun LikeLionList(
    dataList: SnapshotStateList<Map<String, *>>,
    rowComposable: @Composable (Map<String, *>) -> Unit,
    onRowClick: (Map<String, *>) -> Unit = {}, // ✅ 클릭한 item을 받도록 변경
) {
    LazyColumn {
        items(dataList){ dataMap ->
            Column(
                modifier = Modifier.fillMaxWidth().clickable {
                    onRowClick(dataMap)
                }
            ) {
                rowComposable(dataMap)
            }
        }
    }
}

