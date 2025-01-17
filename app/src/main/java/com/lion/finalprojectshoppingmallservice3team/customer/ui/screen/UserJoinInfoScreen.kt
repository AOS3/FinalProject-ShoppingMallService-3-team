package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCheckBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldInputType
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTriStateCheckBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.UserJoinInfoViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun UserJoinInfoScreen(userJoinInfoViewModel: UserJoinInfoViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.Transparent,
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    userJoinInfoViewModel.navigationIconOnClick()
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center,

        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 70.dp),
                horizontalArrangement = Arrangement.Center,
            ) { // 상단 텍스트 (MarCShop)
                Text(
                    text = "MarCShop",
                    color = MainColor,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth() // Row가 가로 전체를 채우도록 설정
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 첫 번째 텍스트필드 (아이디)
                LikeLionOutlinedTextField(
                    textFieldValue = userJoinInfoViewModel.textFieldUserJoinIdValue,
                    label = "아이디",
                    placeHolder = "아이디를 입력해 주세요.",
                    modifier = Modifier.weight(1f),
                    inputCondition = "[^a-zA-Z0-9_]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    onTrailingIconClick = {
                        userJoinInfoViewModel.updateCheckIdButtonState()
                    },
                    singleLine = true,
                    onValueChange = {
                        userJoinInfoViewModel.updateCheckIdButtonState()
                    }
                )

                // 중복확인 버튼
                LikeLionFilledButton(
                    text = "중복확인",
                    isEnabled = userJoinInfoViewModel.isButtonIdEnabled.value,
                    modifier = Modifier.weight(0.4f).padding(top = 6.dp, start = 5.dp).height(56.dp),
                    onClick = {
                        // 중복확인 로직
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth() // Row가 가로 전체를 채우도록 설정
                    .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 두 번째 텍스트필드 (닉네임)
                LikeLionOutlinedTextField(
                    textFieldValue = userJoinInfoViewModel.textFieldUserJoinNicknameValue,
                    label = "닉네임",
                    placeHolder = "닉네임을 입력해 주세요.",
                    modifier = Modifier.weight(1f),
                    inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    onTrailingIconClick = {
                        userJoinInfoViewModel.joinNicknameConditions()
                    },
                    singleLine = true,
                    onValueChange = {
                        userJoinInfoViewModel.joinNicknameConditions()
                    }
                )

                // 중복확인 버튼
                LikeLionFilledButton(
                    text = "중복확인",
                    isEnabled = userJoinInfoViewModel.isButtonNicknameEnabled.value,
                    modifier = Modifier.weight(0.4f).padding(top = 6.dp, start = 5.dp).height(56.dp),
                    onClick = {
                        // 중복확인 로직
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        tint = when {
                            userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                            userJoinInfoViewModel.isJoinLengthValid.value -> Color(0xFF0DB00C) // 조건 충족
                            else -> Color(0xFFB00E0E)
                        },
                        contentDescription = "Check",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "2~10자",
                        color = when {
                            userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                            userJoinInfoViewModel.isJoinLengthValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        fontSize = 14.sp
                    )
                }

                Row(
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        tint = when {
                            userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                            userJoinInfoViewModel.isJoinSpecialCharInvalid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        contentDescription = "Check",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "특수문자 불가",
                        color = when {
                            userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                            userJoinInfoViewModel.isJoinSpecialCharInvalid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        fontSize = 14.sp
                    )
                }

                Row(
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        tint = when {
                            userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                            userJoinInfoViewModel.isJoinConsonantVowelValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        contentDescription = "Check",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "자음 모음 단독 사용 불가",
                        color = when {
                            userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                            userJoinInfoViewModel.isJoinConsonantVowelValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        fontSize = 14.sp
                    )
                }
            }

            // 세 번째 텍스트필드 (비밀번호)
            LikeLionOutlinedTextField(
                textFieldValue = userJoinInfoViewModel.textFieldUserJoinPwValue,
                label = "비밀번호",
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                placeHolder = "비밀번호를 입력해 주세요.",
                trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.PASSWORD,
                inputCondition = "[^a-zA-Z0-9_]",
                singleLine = true,
                inputType = LikeLionOutlinedTextFieldInputType.PASSWORD,
                onValueChange = {
                    userJoinInfoViewModel.joinPwConditions()
                }
            )

            Row(
                modifier = Modifier.padding(bottom = 4.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = when {
                        userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                        userJoinInfoViewModel.isJoinPwLengthValid.value -> Color(0xFF0DB00C)
                        else -> Color(0xFFB00E0E)
                    },
                    contentDescription = "Check",
                    modifier = Modifier.size(20.dp).padding(end = 4.dp) // 아이콘 크기 및 간격 설정
                )
                Text(
                    text = "영문 숫자 포함 10자 이상",
                    color = when {
                        userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                        userJoinInfoViewModel.isJoinPwLengthValid.value -> Color(0xFF0DB00C)
                        else -> Color(0xFFB00E0E)
                    },
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.padding(bottom = 20.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = when {
                        userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                        userJoinInfoViewModel.isJoinContainsIdValid.value -> Color(0xFF0DB00C)
                        else -> Color(0xFFB00E0E)
                    },
                    contentDescription = "Check",
                    modifier = Modifier.size(20.dp).padding(end = 4.dp) // 아이콘 크기 및 간격 설정
                )
                Text(
                    text = "아이디 불가",
                    color = when {
                        userJoinInfoViewModel.textFieldUserJoinNicknameValue.value.isBlank() -> Color.LightGray
                        userJoinInfoViewModel.isJoinContainsIdValid.value -> Color(0xFF0DB00C)
                        else -> Color(0xFFB00E0E)
                    },
                    fontSize = 14.sp
                )
            }

            // 전체 동의
            LikeLionTriStateCheckBox(
                stateValue = userJoinInfoViewModel.triStateCheckBoxUserJoinInfoAllValue,
                text = "전체 동의",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp).size(14.dp),
                textModifier = Modifier.padding(start = 15.dp),
                onClick = {
                    userJoinInfoViewModel.triStateCheckBoxUserJoinInfoAllOnClick()
                }
            )

            LikeLionDivider(
                paddingTop = 10.dp,
                color = Color.LightGray
            )

            Row(
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LikeLionCheckBox(
                    text = "만 14세 이상입니다.",
                    modifier = Modifier.padding(start = 5.dp).size(14.dp),
                    fontSize = 14.sp,
                    checkedValue = userJoinInfoViewModel.checkBoxUserJoinInfo1Value,
                    textModifier = Modifier.padding(start = 15.dp),
                    onCheckedChange = {
                        userJoinInfoViewModel.checkBoxUserJoinInfoOnChanged()
                    },
                )

                Row(
                    modifier = Modifier.fillMaxWidth(), // 가로 전체 크기 설정
                    horizontalArrangement = Arrangement.End // 오른쪽 정렬
                ) {
                    Text(
                        text = "보기",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline, // 밑줄 추가
                    )
                }
            }

            Row(
                modifier = Modifier.padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LikeLionCheckBox(
                    text = "이용약관 동의",
                    modifier = Modifier.padding(start = 5.dp).size(14.dp),
                    fontSize = 14.sp,
                    checkedValue = userJoinInfoViewModel.checkBoxUserJoinInfo2Value,
                    textModifier = Modifier.padding(start = 15.dp),
                    onCheckedChange = {
                        userJoinInfoViewModel.checkBoxUserJoinInfoOnChanged()
                    },
                )

                Row(
                    modifier = Modifier.fillMaxWidth(), // 가로 전체 크기 설정
                    horizontalArrangement = Arrangement.End // 오른쪽 정렬
                ) {
                    Text(
                        text = "보기",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline, // 밑줄 추가
                    )
                }
            }

            Row(
                modifier = Modifier.padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LikeLionCheckBox(
                    text = "개인정보 수집 ＊ 이용 동의",
                    modifier = Modifier.padding(start = 5.dp).size(14.dp),
                    fontSize = 14.sp,
                    checkedValue = userJoinInfoViewModel.checkBoxUserJoinInfo3Value,
                    textModifier = Modifier.padding(start = 15.dp),
                    onCheckedChange = {
                        userJoinInfoViewModel.checkBoxUserJoinInfoOnChanged()
                    },
                )

                Row(
                    modifier = Modifier.fillMaxWidth(), // 가로 전체 크기 설정
                    horizontalArrangement = Arrangement.End // 오른쪽 정렬
                ) {
                    Text(
                        text = "보기",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline, // 밑줄 추가
                    )
                }
            }

            Row(
                modifier = Modifier.padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LikeLionCheckBox(
                    text = "광고성 정보 수신동의 (선택)",
                    modifier = Modifier.padding(start = 5.dp).size(14.dp),
                    fontSize = 14.sp,
                    checkedValue = userJoinInfoViewModel.checkBoxUserJoinInfo4Value,
                    textModifier = Modifier.padding(start = 15.dp),
                    onCheckedChange = {
                        userJoinInfoViewModel.checkBoxUserJoinInfoOnChanged()
                    },
                )

                Row(
                    modifier = Modifier.fillMaxWidth(), // 가로 전체 크기 설정
                    horizontalArrangement = Arrangement.End // 오른쪽 정렬
                ) {
                    Text(
                        text = "보기",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline, // 밑줄 추가
                    )
                }
            }

            LikeLionFilledButton(
                text = "가입 완료하기",
                isEnabled = userJoinInfoViewModel.isButtonJoinEnabled.value,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                onClick = {

                }
            )
        }
    }
}