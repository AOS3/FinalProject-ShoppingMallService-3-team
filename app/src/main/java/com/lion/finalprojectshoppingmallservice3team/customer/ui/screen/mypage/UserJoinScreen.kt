package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionImage
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.UserJoinViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun UserJoinScreen(userJoinViewModel: UserJoinViewModel = hiltViewModel()) {
    val context = LocalContext.current

    // ActivityResultLauncher 설정
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        userJoinViewModel.handleGoogleSignUpResult(context, task) // 결과 처리
    }

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.White,
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    userJoinViewModel.navigationIconOnClick()
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 상단 텍스트 (MarCShop)
            Text(
                text = "MarCShop",
                color = MainColor,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier.padding(bottom = 70.dp) // 텍스트와 텍스트필드 간격 조정
            )

            Text(
                text = "SNS 계정으로 회원가입",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 20.dp),
            )

            Row(
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                // 카카오 아이콘으로 바꾸기
                LikeLionImage(
                    painter = painterResource(id = R.drawable.kakao_login_logo),
                    modifier = Modifier
                        .size(50.dp),
                    isCircular = true,
                    onClick = {
                        userJoinViewModel.kakaoSignUp(context)
                    }
                )

                Row(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    LikeLionImage(
                        painter = painterResource(id = R.drawable.naver_logo),
                        modifier = Modifier
                            .size(50.dp),
                        isCircular = true,
                        onClick = {
                            userJoinViewModel.naverSignUp(context)
                        }
                    )
                }

                Row(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    LikeLionImage(
                        painter = painterResource(id = R.drawable.google_logo),
                        modifier = Modifier
                            .size(50.dp),
                        contentScale = ContentScale.Inside,
                        borderWidth = 1.dp, // 테두리 두께
                        borderColor = Color.LightGray, // 테두리 색상
                        isCircular = true,
                        onClick = {
                            userJoinViewModel.googleSignUp(context, launcher)
                        }
                    )
                }
            }

            Text(
                text = "또는",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 20.dp),

            )

            LikeLionFilledButton(
                text = "회원가입 하기",
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                onClick = {
                    userJoinViewModel.buttonNextOnClick()
                }
            )

            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "이미 계정이 있으신가요?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                )

                Text(
                    text = "로그인하기",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                        userJoinViewModel.buttonLoginClick()
                    }
                )
            }
        }
    }
}