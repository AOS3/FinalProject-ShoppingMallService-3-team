package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor


/*****************************크리에이터 랭킹 화면에서 사용***********************/

@Composable
fun LikeLionCreatorProductList(
    productList: List<Product>,
    onCreatorNameClick: (Product) -> Unit,
    onLikeClick: (Product) -> Unit,
    onItemClick: (Product) -> Unit,
    rows: Int = 1,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(rows),
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        items(productList) { product ->
            LikeLionProductItem(
                product = product,
                onCreatorNameClick = onCreatorNameClick,
                onLikeClick = onLikeClick,
                onItemClick = onItemClick
            )
        }
    }
}

