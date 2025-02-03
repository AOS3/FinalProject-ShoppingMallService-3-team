package com.lion.finalprojectshoppingmallservice3team.Component


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel


/*****************************크리에이터 랭킹 화면에서 사용***********************/

@Composable
fun LikeLionCreatorProductList(
    productList: List<ProductModel>,
    onCreatorNameClick: (ProductModel) -> Unit,
    onLikeClick: (ProductModel) -> Unit,
    onItemClick: (ProductModel) -> Unit,
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

