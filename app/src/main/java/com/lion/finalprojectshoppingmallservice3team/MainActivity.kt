package com.lion.finalprojectshoppingmallservice3team

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lion.finalprojectshoppingmallservice3team.Seller.ui.screen.Item.ItemScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.home.HomeScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.LoginScreen
import androidx.navigation.navArgument
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionBottomNavItems
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionBottomNavigation
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.ProductRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.ProductService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.ProductCategory
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.ProductSubCategory
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO
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
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.UserSettingScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorApplyScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorApplySecondScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorApplyThirdScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorListScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorMainScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorNoticeScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator.CreatorShopScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.home.HomeScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteBottomScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteGroupScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteNewGroupScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite.MyFavoriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.CancelRefundFAQScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.InquiryListScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.InquiryReadScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.InquiryWriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.LoginMyPageScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.LoginScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.LogoutMyPageScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.ModifyUserPwScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.MyPostsScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.MyPurchaseHistoryScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.MyRecentScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.UserJoinInfoScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage.UserJoinScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.InquiryProductListScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.InquiryProductReadScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.InquiryProductWriteScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.ProductInfoScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop.ShopScreen
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorApplyViewmodel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var productService: ProductService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        setContent {
            FinalProjectShoppingMallService3teamTheme {
                ShoppingMain(windowInsetsController)
            }
        }
        val productRepository = ProductRepository()
        productService = ProductService(productRepository)



    }
}

@Composable
fun ShoppingMain(windowInsetsController: WindowInsetsControllerCompat) {
    val navController = rememberNavController()
    // Application 객체에 담는다.
    val shoppingApplication = LocalContext.current.applicationContext as ShoppingApplication
    shoppingApplication.navHostController = navController
    val creatorApplyViewModel: CreatorApplyViewmodel = hiltViewModel()

    //***************** 바텀네비게이션 관련 코드 ****************************//
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isLoggedIn by shoppingApplication.isLoggedIn.collectAsState()


    // 스플래시 상태 관리
    var isSplashCompleted by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (isSplashCompleted && currentRoute in listOf("home", "creator", "myFavorite", "shop", "logoutMyPage", "loginMyPage"))
            LikeLionBottomNavigation(
                navController = navController,
                items = LikeLionBottomNavItems(isLoggedIn),
            )
        },
    ) {
        //***************** 바텀네비게이션 관련 코드 ****************************//
        Log.d("TAG", "ShoppingMain: ${it}")
        NavHost(
            navController = navController,
            startDestination = "splash",
            enterTransition = {
                if (targetState.destination.route == "inquiryWrite" || targetState.destination.route == "modifyUserPw") {
                    fadeIn(
                        tween(300)
                    ) +
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                tween(300)
                            )
                }
                else {
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
                if (initialState.destination.route == "inquiryWrite" || initialState.destination.route == "logoutMyPage") {
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
            composable("splash") {
                SplashScreen(navController = navController)
            }

            //***************** 바텀네비게이션 관련 코드 ****************************//
            composable("home",
                enterTransition = {EnterTransition.None},
                exitTransition = { ExitTransition.None },
            ){

                isSplashCompleted = true
                HomeScreen(
                    windowInsetsController
                )
            }
            composable("creator",
                enterTransition = {EnterTransition.None},
                exitTransition = { ExitTransition.None },
            ){
                CreatorMainScreen()
            }
            composable("myFavorite",
                enterTransition = {EnterTransition.None},
                exitTransition = { ExitTransition.None },

            ) {
                MyFavoriteScreen()
            }
            composable("shop",
                enterTransition = {EnterTransition.None},
                exitTransition = { ExitTransition.None },
            ) {
                ShopScreen()
            }
            composable("logoutMyPage",
                enterTransition = {EnterTransition.None},
                exitTransition = { ExitTransition.None },
            ) {
                LogoutMyPageScreen()
            }
            composable("loginMyPage",
                enterTransition = {EnterTransition.None},
                exitTransition = { ExitTransition.None },
            ) {
                LoginMyPageScreen()
            }
            //***************** 바텀네비게이션 관련 코드 ****************************//

            composable("login") { LoginScreen() }
            composable("search") { SearchScreen(navController) }
            composable("searchFail") { SearchFailScreen(navController) }
            composable("searchSuccess") { SearchSuccessScreen(navController) }
            composable("userJoin") { UserJoinScreen() }
            composable("userJoinInfo") { UserJoinInfoScreen() }
            composable("userSetting") { UserSettingScreen() }
            composable("modifyUserPw") { ModifyUserPwScreen() }
            composable("myPosts") { MyPostsScreen() }
            composable("myRecent") { MyRecentScreen() }
            composable("myPurchaseHistory") { MyPurchaseHistoryScreen() }
            composable("inquiryList") { InquiryListScreen() }
            composable(
                route = "inquiryRead/{documentId}",
                arguments = listOf(navArgument("documentId") { type = NavType.StringType })
            ) { backStackEntry ->
                val inquiryDocumentId = backStackEntry.arguments?.getString("documentId") ?: ""
                InquiryReadScreen(inquiryDocumentId = inquiryDocumentId)
            }
            composable("inquiryWrite") { InquiryWriteScreen() }
            composable("creatorApply") { CreatorApplyScreen(creatorApplyViewModel = creatorApplyViewModel) }
            composable("creatorApplySecond") { CreatorApplySecondScreen(creatorApplyViewModel = creatorApplyViewModel) }
            composable("creatorApplyThird") { CreatorApplyThirdScreen(creatorApplyViewModel = creatorApplyViewModel) }
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
            // 크리에이터 신청 화면
            composable("creatorApply") {CreatorApplyScreen()}
            composable("creatorApplySecond") { CreatorApplySecondScreen()}
            composable("creatorApplyThird") { CreatorApplyThirdScreen()}

            // 크리에이터 랭킹 화면
            composable("creatorRanking") { CreatorRankingScreen() }

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

            composable("creatorList"){ CreatorListScreen() }
            composable("creatorNotice"){ CreatorNoticeScreen() }
        }
    }
}

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    // `navigationTarget` 상태 관찰
    val navigationTarget by splashViewModel.navigationTarget.collectAsState()

    // 스플래시 화면이 처음 표시될 때 자동 로그인 로직 실행
    LaunchedEffect(Unit) {
        splashViewModel.checkAutoLogin(context) // 자동 로그인 로직 실행
    }

    // `navigationTarget` 상태 변화에 따라 네비게이션 처리
    LaunchedEffect(navigationTarget) {
        if (navigationTarget.isNotEmpty() && navigationTarget != "splash") {
            navController.navigate(navigationTarget) {
                popUpTo("splash") { inclusive = true }
            }
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
            painter = painterResource(id = R.drawable.marcshop_logo),
            contentDescription = "MarcShop Logo",
            modifier = Modifier.size(200.dp)
        )
    }
}
