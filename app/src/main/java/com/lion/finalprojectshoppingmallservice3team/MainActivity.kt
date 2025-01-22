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
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.CancelRefundFAQScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.InquiryProductListScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.InquiryProductReadScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.InquiryProductWriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.InquiryReadScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.LoginScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.ProductInfoScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.ShopScreen
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
        navController = navController,
        startDestination = "splash",
        enterTransition = {
            fadeIn(
                tween(300)
            ) +
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(300)
                    )
        },
        popExitTransition = {
            fadeOut(
                tween(300)
            ) +
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(300)
                    )
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
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    // 스플래시 화면에서 1초 대기 후 로그인 화면으로 이동
    LaunchedEffect(Unit) {
        delay(1000) // 1초 대기
        navController.navigate("login") {
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