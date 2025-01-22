package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionAlertDialog
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionImage
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldInputType
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.LoginViewModel


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.Transparent,
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    loginViewModel.navigationIconOnClick()
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                modifier = Modifier.fillMaxWidth(),
                inputCondition = "[^a-zA-Z0-9_]",
                trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                onTrailingIconClick = {
                    loginViewModel.updateButtonState()
                },
                singleLine = true,
                onValueChange = {
                    loginViewModel.updateButtonState()
                }
            )

            // 두 번째 텍스트필드 (비밀번호)
            LikeLionOutlinedTextField(
                textFieldValue = loginViewModel.textFieldLoginPwValue,
                paddingTop = 20.dp,
                label = "비밀번호",
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "비밀번호를 입력해주세요.",
                trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.PASSWORD,
                inputCondition = "[^a-zA-Z0-9_]",
                singleLine = true,
                inputType = LikeLionOutlinedTextFieldInputType.PASSWORD,
                onValueChange = {
                    loginViewModel.updateButtonState()
                }
            )


            LikeLionFilledButton(
                text = "로그인하기",
                isEnabled = loginViewModel.isButtonEnabled.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                paddingTop = 20.dp,
                onClick = {
                    loginViewModel.buttonLoginClick()
                }
            )

            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 60.dp)
            ) {
                Text(
                    text = "아이디 찾기",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(end = 10.dp)
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
                    modifier = Modifier
                        .padding(start = 10.dp)
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

                // 카카오 아이콘으로 바꾸기
                LikeLionImage(
                    painter = painterResource(id = R.drawable.kakao_login_logo),
                    modifier = Modifier
                        .size(50.dp),
                    isCircular = true,
                    onClick = {

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

                        }
                    )
                }
            }

            Row(
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    text = "아직 회원이 아니신가요?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                )

                Text(
                    text = "회원가입 하기",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            loginViewModel.buttonUserJoinClick()
                    },
                )
            }

            // 존재하지 않는 아이디
            LikeLionAlertDialog(
                showDialogState = loginViewModel.alertDialogLoginFail1State,
                confirmButtonTitle = "확인",
                confirmButtonOnClick = {
                    loginViewModel.textFieldLoginIdValue.value = ""
                    loginViewModel.textFieldLoginPwValue.value = ""
                    loginViewModel.alertDialogLoginFail1State.value = false
                    loginViewModel.updateButtonState()
                },
                title = "로그인 오류",
                text = "존재하지 않는 아이디 입니다"
            )

            // 잘못된 비밀번호
            LikeLionAlertDialog(
                showDialogState = loginViewModel.alertDialogLoginFail2State,
                confirmButtonTitle = "확인",
                confirmButtonOnClick = {
                    loginViewModel.textFieldLoginPwValue.value = ""
                    loginViewModel.alertDialogLoginFail2State.value = false
                    loginViewModel.updateButtonState()
                },
                title = "로그인 오류",
                text = "잘못된 비밀번호 입니다"
            )

            // 잘못된 비밀번호
            LikeLionAlertDialog(
                showDialogState = loginViewModel.alertDialogLoginFail3State,
                confirmButtonTitle = "확인",
                confirmButtonOnClick = {
                    loginViewModel.textFieldLoginIdValue.value = ""
                    loginViewModel.textFieldLoginPwValue.value = ""
                    loginViewModel.alertDialogLoginFail3State.value = false
                    loginViewModel.updateButtonState()
                },
                title = "로그인 오류",
                text = "탈퇴한 회원입니다"
            )
        }
    }
}