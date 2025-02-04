package com.lion.finalprojectshoppingmallservice3team.Component

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.creator.data.model.ShopModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.ProductService
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorMainViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun LikeLionBigUserView(
    randomUser: ShopModel,
    modifier: Modifier = Modifier,
    productService: ProductService
){
    val tempProductList = List(4){""}
    var productList by remember { mutableStateOf< List<String>>(listOf())}
    LaunchedEffect(randomUser) {
        var newProductList = productService.selectCreatorProductData(randomUser.shopDocumentId)
        productList = newProductList
    }
    Card(  // Card View
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp), // fillMaxWidth로 가로를 꽉 차게
        colors = CardDefaults.cardColors(
            Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .clickable(
                onClick = {

                }
            )
        ) {
            Box {
                LikeLionProductImage(
                    imgUrl = randomUser.shopBannerImage,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(16.dp),
                    size = 200.dp
                )
            }

            Box {
                Column(modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.height(80.dp)
                    ) {
                        LikeLionProfileImg(
                            imgUrl = randomUser.shopProfileImage,
                            iconTint = Color.White,
                            profileBack = if (randomUser.shopProfileImage == "") MainColor
                            else Color.Transparent
                        )

                        Column(
                            modifier = Modifier.padding(start = 25.dp).fillMaxSize()
                        ) {
                            Text(
                                text = randomUser.shopName,
                                style = typography.bodyMedium
                            )
                            Text(
                                text = randomUser.shopCompanyName,
                                style = typography.bodySmall
                            )
                            LikeLionTextIconButton(
                                icon = ImageVector.vectorResource(id = R.drawable.favorite_24px),
                                text = "999+",
                                containerColor = Color.Transparent.copy(0.0f)
                            )
                        }

                    }

                    if (productList.isNotEmpty())
                        LikeLionProductListView(productList, null,
                            modifier = Modifier.size(70.dp,80.dp).clip(RoundedCornerShape(15.dp)))
                    else
                        LikeLionProductListView(tempProductList,null,
                            modifier = Modifier.size(70.dp,80.dp).clip(RoundedCornerShape(15.dp)))
                    Log.d("st",productList.toString())
                }
            }

        }


    }
}

@Composable
fun LikeLionBigUserListView(
    randomUsers: List<ShopModel>,
    modifier: Modifier = Modifier,
    entryPaddingValues: Dp = 0.dp,
    bottomPaddingValues: Dp = 0.dp,
    cardModifier: Modifier = Modifier,
    productService: ProductService
){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = entryPaddingValues, bottom = bottomPaddingValues)
    ){
        items(randomUsers.size) { position ->
            LikeLionBigUserView(
                randomUsers[position],
                cardModifier,
                productService
            )
        }
    }
}