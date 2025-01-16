package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R

@Composable
fun LikeLionGroupEditView(myGroup:String) {
    Card {
        Row(
            modifier = Modifier.background(Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LikeLionIconButton(
                icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                text = "",
                iconSize = 30.dp,
                size = 35.dp,
                padding = 10.dp,
                borderNull = true,

                )
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .background(Color.White),
                text = myGroup,
            )
            LikeLionIconButton(
                icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                text = "",
                iconSize = 30.dp,
                size = 35.dp,
                padding = 10.dp,
                borderNull = true
            )
            LikeLionIconButton(
                icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                text = "",
                iconSize = 30.dp,
                size = 35.dp,
                padding = 10.dp,
                borderNull = true
            )
        }
    }
}

@Composable
fun LikeLionGroupEditListView(myGroups:List<String>) {
    LazyColumn {
        items(myGroups.size) { idx ->
            LikeLionGroupEditView(myGroups[idx])
        }
    }
}