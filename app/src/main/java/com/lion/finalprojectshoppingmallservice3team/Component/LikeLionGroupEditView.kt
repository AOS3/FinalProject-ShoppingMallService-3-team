package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.padding
import android.R.attr.text
import android.util.Log.e
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R
import java.nio.file.Files.size

@Composable
fun LikeLionGroupEditView(myGroup:String,idx:Int) {
    Card {
        Row(
            modifier = Modifier.background(Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (idx != 0)
                Icon(
                    painter = painterResource(id = R.drawable.bar_24px),
                    contentDescription = null,
                    modifier = Modifier.padding(7.dp).size(40.dp,35.dp),
                    tint = Color(0xFF9F9F9F),
                )
            else Icon(
                painter = painterResource(id = R.drawable.keep_24px),
                contentDescription = null,
                modifier = Modifier.padding(15.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .background(Color.White),
                text = myGroup,
            )
            if (idx != 0 || myGroup == "상품") {
                LikeLionIconButton(
                    icon = ImageVector.vectorResource(id = R.drawable.edit_24px),
                    padding = 10.dp,
                    borderNull = true
                )
                LikeLionIconButton(
                    icon = ImageVector.vectorResource(id = R.drawable.close_24px),
                    padding = 10.dp,
                    borderNull = true
                )
            }
        }
    }
}

@Composable
fun LikeLionGroupEditListView(myGroups:List<String>) {
    LazyColumn {
        items(myGroups.size) { idx ->
            LikeLionGroupEditView(myGroups[idx],idx)
        }
    }
}