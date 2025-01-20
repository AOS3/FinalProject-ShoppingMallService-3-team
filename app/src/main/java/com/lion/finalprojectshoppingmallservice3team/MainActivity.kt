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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.InquiryListScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.InquiryReadScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.InquiryWriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.LoginMyPageScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.LoginScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.LogoutMyPageScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.ModifyUserPwScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.MyCheerScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.MyPostsScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.MyPurchaseHistoryScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.MyRecentScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.MyReviewScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.UserJoinInfoScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.UserJoinScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.UserSettingScreen
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
            if (targetState.destination.route == "inquiryWrite") {
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
            if (initialState.destination.route == "inquiryWrite") {
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
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    // 스플래시 화면에서 2초 대기 후 로그인 화면으로 이동
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