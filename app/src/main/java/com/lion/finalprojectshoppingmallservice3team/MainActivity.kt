package com.lion.finalprojectshoppingmallservice3team

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lion.finalprojectshoppingmallservice3team.Seller.ui.screen.Item.ItemScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.home.HomeScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.LoginScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.SearchFailScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.SearchScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.SearchSuccessScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorApplyScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorApplySecondScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorApplyThirdScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorRankingScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.InquiryListScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.InquiryReadScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.InquiryWriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.LoginMyPageScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorShopScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteBottomScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteGroupScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteNewGroupScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.LogoutMyPageScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.ModifyUserPwScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.MyPostsScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.MyPurchaseHistoryScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.MyRecentScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.UserJoinInfoScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.UserJoinScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.UserSettingScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.CancelRefundFAQScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.InquiryProductListScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.InquiryProductReadScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.InquiryProductWriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.ProductInfoScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.ShopScreen
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalProjectShoppingMallService3teamTheme {
                ShoppingMain()
            }
        }
    }
}

@Composable
fun ShoppingMain() {
    val navController = rememberNavController()
    // Application 객체에 담는다.
    val shoppingApplication = LocalContext.current.applicationContext as ShoppingApplication
    shoppingApplication.navHostController = navController
    NavHost(
        navController = navController, //ProductMaking
        startDestination = "splash",  //splash에서 잠시 바꿈 테스트 용
        enterTransition = {
            if (targetState.destination.route == "inquiryWrite" || targetState.destination.route == "modifyUserPw") {
                fadeIn(
                    tween(300)
                ) +
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Up,
                            tween(300)
                        )
            } else {
                fadeIn(
                    tween(300)
                ) +
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start,
                            tween(300)
                        )
            }

        },
        popExitTransition = {
            if (initialState.destination.route == "inquiryWrite" || initialState.destination.route == "modifyUserPw") {
                fadeOut(
                    tween(300)
                ) +
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Down,
                            tween(300)
                        )
            } else {
                fadeOut(
                    tween(300)
                ) +
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End,
                            tween(300)
                        )
            }
        },
        exitTransition = {
            fadeOut(
                tween(300)
            )
        },
        popEnterTransition = {
            fadeIn(
                tween(300)
            )
        },
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen() }
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen(navController) }
        composable("searchFail") { SearchFailScreen(navController) }
        composable("searchSuccess") { SearchSuccessScreen(navController) }
        composable("userJoin") { UserJoinScreen() }
        composable("userJoinInfo") { UserJoinInfoScreen() }
        composable("logoutMyPage") { LogoutMyPageScreen() }
        composable("loginMyPage") { LoginMyPageScreen() }
        composable("userSetting") { UserSettingScreen() }
        composable("modifyUserPw") { ModifyUserPwScreen() }
        composable("myPosts") { MyPostsScreen() }
        composable("myRecent") { MyRecentScreen() }
        composable("myPurchaseHistory") { MyPurchaseHistoryScreen() }
        composable("inquiryList") { InquiryListScreen() }
        composable("inquiryRead") { InquiryReadScreen() }
        composable("inquiryWrite") { InquiryWriteScreen() }

        // 크리에이터 신청 화면
        composable("creatorApply") {CreatorApplyScreen()}
        composable("creatorApplySecond") { CreatorApplySecondScreen()}
        composable("creatorApplyThird") { CreatorApplyThirdScreen()}

        // 크리에이터 랭킹 화면
        composable("creatorRanking") { CreatorRankingScreen() }

        // shop 화면
        composable( "shop") { ShopScreen() }
        // 상품상세 화면
        composable("productInfo/{productDocumentId}") {
            val productDocumentId = it.arguments?.getString("productDocumentId")!!
            ProductInfoScreen(productDocumentId = productDocumentId)
        }
        // 상품 문의화면
        composable("inquiryProductWrite",
            enterTransition = {
                // inquiryProductWrite 화면에만 아래에서 위로 슬라이드 인
                fadeIn(tween(300)) +
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Up,
                            tween(300)
                        )
            },
            exitTransition = {
                // inquiryProductWrite 화면이 떠날 때 위에서 아래로 슬라이드 아웃
                fadeOut(tween(300)) +
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Down,
                            tween(300)
                        )
            }) { InquiryProductWriteScreen() }
        // 크리에이터 문의 내역확인 화면
        composable("inquiryProductList") { InquiryProductListScreen() }
        // 문의 내역 상세
        composable("inquiryProductRead") { InquiryProductReadScreen() }
        // 취소/환불 FAQ
        composable("cancelRefundFAQ") { CancelRefundFAQScreen() }
        composable("myFavorite") { MyFavoriteScreen() }
        composable("myFavoriteGroup") { MyFavoriteGroupScreen() }
        composable("MyFavoriteNewGroup") { MyFavoriteNewGroupScreen() }
        composable(
            route = "MyFavoriteBottom",
            enterTransition = {
                fadeIn(
                    tween(300)
                ) +
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Up,
                            tween(300)
                        )
            },
            popExitTransition = {
                fadeOut(
                    tween(300)
                ) +
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Down,
                            tween(300)
                        )
            },
        ) { MyFavoriteBottomScreen() }
        composable("CreatorShop"){ CreatorShopScreen() }


/*********************************************Seller******************************************/

        // Item 마크샵 상품 만들기
        composable("ProductMaking"){ ItemScreen() }

    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    // 스플래시 화면에서 1초 대기 후 로그인 화면으로 이동
    LaunchedEffect(Unit) {
        delay(1000) // 1초 대기
        navController.navigate("logoutMyPage") {
            popUpTo("splash") { inclusive = true } // 스플래시 화면 제거
        }
    }

    // 스플래시 화면 UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.marcshop_logo), // mipmap 폴더에서 로고 사용
            contentDescription = "MarcShop Logo",
            modifier = Modifier.size(200.dp) // 로고 크기 설정
        )
    }
}