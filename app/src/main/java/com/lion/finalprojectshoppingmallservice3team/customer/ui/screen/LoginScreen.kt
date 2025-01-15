package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.a02_boardcloneproject.component.LikeLionImage
import com.lion.a02_boardcloneproject.component.LikeLionOutlinedTextField
import com.lion.a02_boardcloneproject.component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.a02_boardcloneproject.component.LikeLionOutlinedTextFieldInputType
import com.lion.a02_boardcloneproject.component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {

                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            // 상단 텍스트 (MarCShop)
            Text(
                text = "MarCShop",
                color = Color(0xFFA16DEB),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier.padding(bottom = 70.dp) // 텍스트와 텍스트필드 간격 조정
            )

            // 첫 번째 텍스트필드 (아이디)
            LikeLionOutlinedTextField(
                textFieldValue = loginViewModel.textFieldLoginIdValue,
                label = "아이디",
                placeHolder = "아이디를 입력해주세요.",
                inputCondition = "[^a-zA-Z0-9_]",
                trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                singleLine = true,
                onValueChange = {
                    loginViewModel.textFieldLoginIdValue.value = it
                    loginViewModel.updateButtonState()
                }
            )

            // 두 번째 텍스트필드 (비밀번호)
            LikeLionOutlinedTextField(
                textFieldValue = loginViewModel.textFieldLoginPwValue,
                paddingTop = 20.dp,
                label = "비밀번호",
                placeHolder = "비밀번호를 입력해주세요.",
                trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.PASSWORD,
                inputCondition = "[^a-zA-Z0-9_]",
                singleLine = true,
                inputType = LikeLionOutlinedTextFieldInputType.PASSWORD,
                onValueChange = {
                    loginViewModel.textFieldLoginPwValue.value = it
                    loginViewModel.updateButtonState()
                }
            )

            LikeLionFilledButton(
                text = "로그인하기",
                isEnabled = loginViewModel.isButtonEnabled.value,
                paddingTop = 20.dp,
                onClick = {

                }
            )

            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 60.dp)
            ) {
                Text(
                    text = "아이디 찾기",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 10.dp)
                        .clickable {

                    },
                )

                Text(
                    text = "|",
                    fontSize = 14.sp,
                    color = Color.Black,
                )

                Text(
                    text = "비밀번호 찾기",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 10.dp)
                        .clickable {

                    },
                )
            }

            Text(
                text = "SNS 계정으로 로그인하기",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 10.dp),
            )

            Row(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                LikeLionImage(
                    painter = painterResource(id = R.drawable.kakao_login_original),
                    modifier = Modifier.width(80.dp).height(40.dp),
                    cornerRadius = 25.dp,
                    onClick = {

                    }
                )

                LikeLionImage(
                    painter = painterResource(id = R.drawable.naver_logo),
                    modifier = Modifier.width(50.dp).height(40.dp)
                        .padding(start = 10.dp),
                    isCircular = true,
                    onClick = {

                    }
                )

                LikeLionImage(
                    painter = painterResource(id = R.drawable.google_logo),
                    modifier = Modifier.width(50.dp).height(40.dp)
                        .padding(start = 10.dp),
                    contentScale = ContentScale.Inside,
                    borderWidth = 1.dp, // 테두리 두께
                    borderColor = Color.LightGray, // 테두리 색상
                    isCircular = true,
                    onClick = {

                    }
                )
            }

            Row(
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    text = "아직 회원이 아니신가요?",
                    color = Color.Black,
                    fontSize = 14.sp,
                )

                Text(
                    text = "회원가입 하기",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 10.dp)
                        .clickable {

                    },
                )
            }
        }
    }
}