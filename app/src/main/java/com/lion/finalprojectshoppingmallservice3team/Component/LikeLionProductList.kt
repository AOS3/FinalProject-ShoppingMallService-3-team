package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.Typography

@Composable
fun LikeLionProductList(
    productList: List<Product>,
    onProductClick: (Product) -> Unit,
    onLikeClick: (Product) -> Unit,
    columns: Int = 2
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(productList) { product ->
            LikeLionProductItem(
                product = product,
                onProductClick = onProductClick,
                onLikeClick = onLikeClick
            )
        }
    }
}

@Composable
fun LikeLionProductItem(
    product: Product,
    onProductClick: (Product) -> Unit,
    onLikeClick: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductClick(product) }  // 상품 클릭 시 이벤트 처리
    ) {
        // 이미지
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)  // 이미지 높이 설정
        ) {
            // 기본 이미지
            Image(
                painter = painterResource(id = R.drawable.marcshop_logo),  // 기본 이미지
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Fit
            )
        }

        // 크리에이터 이름과 좋아요 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.creator,
                style = Typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = { onLikeClick(product) }) { // 클릭 시 상태 변경
                Icon(
                    painter = painterResource(
                        id = if (product.isFavorite)
                            R.drawable.favorite_fill_24px
                        else
                            R.drawable.favorite_24px
                    ),
                    contentDescription = "Like Button",
                    tint = if (product.isFavorite) MainColor else Color.LightGray
                )
            }
        }

        // 상품 이름
        Text(
            text = product.name,
            style = Typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // 상품 가격
        Text(
            text = "${product.price}원",
            style = Typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp, top = 2.dp)
        )
    }
}

data class Product(
    val id: String,
    val name: String,     // 상품 이름
    val price: Double,    // 상품 가격
    val imageUrl: String, // 상품 이미지 URL (일단 기본값)
    val creator: String,  // 크리에이터 이름
    val category: String, // 대카테고리 (예: 의류, 굿즈 등)
    val subCategory: String, // 소카테고리 (예: 티셔츠, 키링 등)
    var isFavorite: Boolean // 좋아요
)