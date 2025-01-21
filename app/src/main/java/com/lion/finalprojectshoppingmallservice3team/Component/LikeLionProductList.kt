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

@Composable
fun LikeLionProductList(
    productList: List<Product>,
    onCreatorNameClick: (Product) -> Unit,
    onLikeClick: (Product) -> Unit,
    onItemClick: (Product) -> Unit,
    columns: Int = 2
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier
            .fillMaxSize()
            .padding(),
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

@Composable
fun LikeLionProductItem(
    product: Product,
    onCreatorNameClick: (Product) -> Unit,
    onLikeClick: (Product) -> Unit,
    onItemClick: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onItemClick(product) },
        colors = CardDefaults.cardColors(Color.White),
        //elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        // 이미지와 상태 레이블을 겹쳐서 배치
        Box(modifier = Modifier
            .fillMaxWidth()
        ) {
            // 이미지
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    LikeLionProductImage(
                        modifier = Modifier
                            .graphicsLayer {
                                if(product.stockQuantity == 0){
                                    alpha = 0.5f
                                }
                            },
                        imgUrl = product.imageUrls.firstOrNull() ?: "",
                        size = 200.dp,
                        fixedImage = R.drawable.marcshop_logo
                    )
                }
            }

            // "Soldout" 또는 "Limited" 텍스트 표시
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
            ) {
                if (product.stockQuantity == 0) {
                    Text(
                        text = "Soldout",
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Gray, RoundedCornerShape(5.dp))
                            .padding(horizontal = 5.dp),
                        fontSize = 12.sp
                    )
                }
                // Spacer를 이용해 두 텍스트 사이에 간격을 추가
                if (product.stockQuantity == 0 && product.isLimited) {
                    Spacer(modifier = Modifier.width(5.dp)) // "Soldout"과 "Limited" 사이에 간격 추가
                }

                if (product.isLimited) {
                    Text(
                        text = "Limited",
                        color = Color.White,
                        modifier = Modifier
                            .background(MainColor, RoundedCornerShape(5.dp))
                            .padding(horizontal = 5.dp),
                        fontSize = 12.sp
                    )
                }
            }
        }

        // 크리에이터 이름과 좋아요 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.creator,
                //style = Typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f)
                    .clickable { onCreatorNameClick(product) }
            )
            IconButton(
                onClick = { onLikeClick(product) },
                modifier = Modifier.padding(0.dp)
            ) { // 클릭 시 상태 변경
                Icon(
                    painter = painterResource(
                        id = if (product.isFavorite)
                            R.drawable.favorite_fill_24px
                        else
                            R.drawable.favorite_24px
                    ),
                    contentDescription = "Like Button",
                    tint = if (product.isFavorite) MainColor else Color.LightGray,
                )
            }
        }

        // 상품 이름
        Text(
            text = product.name,
            //style = Typography.bodyMedium,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        val formattedPrice = String.format("%,d", product.price)
        // 상품 가격
        Text(
            text = "${formattedPrice}원",
            fontWeight = FontWeight.Bold,
            //style = Typography.bodySmall,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 2.dp,
                bottom = if(product.reviewCount > 0) 0.dp else 10.dp)
        )

        // 리뷰 정보
        if (product.reviewCount > 0) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, top = 2.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 별 아이콘
                Icon(
                    painter = painterResource(R.drawable.star_24px),
                    contentDescription = "Star Icon",
                    modifier = Modifier.size(12.dp),
                    tint = MainColor
                )

                // 평점과 리뷰 수
                Text(
                    text = "${product.rating}(${product.reviewCount})",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp),
                    color = Color.Gray
                )
            }
        }
    }
}

data class Product(
    val productDocumentId: String,
    val name: String,     // 상품 이름
    val price: Long,    // 상품 가격
    val imageUrl: String,// 상품 이미지 URL (일단 기본값)
    val imageUrls: List<String> = emptyList(),
    val description: String, // 상품 설명
    val creator: String,  // 크리에이터 이름
    val category: String, // 대카테고리 (예: 의류, 굿즈 등)
    val subCategory: String, // 소카테고리 (예: 티셔츠, 키링 등)
    var isFavorite: Boolean, // 좋아요
    var isLimited: Boolean, // 한정판 여부
    val stockQuantity: Int, // 재고 수
    val rating: Float, // 상품 평점 (예: 4.5)
    val reviewCount: Int // 리뷰 수
)
