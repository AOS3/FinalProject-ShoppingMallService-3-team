package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldInputType
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.ModifyUserPwViewModel

@Composable
fun ModifyUserPwScreen(modifyUserPwViewModel: ModifyUserPwViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                title = "비밀번호 변경",
                backColor = Color.White,
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(R.drawable.close_24px),
                        padding = 10.dp,
                        iconButtonOnClick = {
                            modifyUserPwViewModel.menuCloseOnClick()
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
                .imePadding()
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // 현재 비밀번호
                LikeLionOutlinedTextField(
                    textFieldValue = modifyUserPwViewModel.textFieldModifyCurrentPwValue,
                    label = "현재 비밀번호*",
                    paddingTop = 20.dp,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    placeHolder = "현재 비밀번호를 입력해 주세요.",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.PASSWORD,
                    inputCondition = "[^a-zA-Z0-9_]",
                    singleLine = true,
                    inputType = LikeLionOutlinedTextFieldInputType.PASSWORD,
                    onValueChange = {
                        modifyUserPwViewModel.updateButtonState()
                    }
                )

                // 새 비밀번호
                LikeLionOutlinedTextField(
                    textFieldValue = modifyUserPwViewModel.textFieldModifyNewPwValue,
                    label = "새 비밀번호",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                    placeHolder = "새 비밀번호를 입력해 주세요.",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.PASSWORD,
                    inputCondition = "[^a-zA-Z0-9_]",
                    singleLine = true,
                    inputType = LikeLionOutlinedTextFieldInputType.PASSWORD,
                    onValueChange = {
                        modifyUserPwViewModel.modifyNewPwConditions()
                        modifyUserPwViewModel.updateButtonState()
                    }
                )

                Row(
                    modifier = Modifier.padding(bottom = 4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        tint = when {
                            modifyUserPwViewModel.textFieldModifyNewPwValue.value.isBlank() -> Color.LightGray
                            modifyUserPwViewModel.isModifyPwLengthValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        contentDescription = "Check",
                        modifier = Modifier.size(20.dp).padding(end = 4.dp) // 아이콘 크기 및 간격 설정
                    )
                    Text(
                        text = "영문 숫자 포함 10자 이상",
                        color = when {
                            modifyUserPwViewModel.textFieldModifyNewPwValue.value.isBlank() -> Color.LightGray
                            modifyUserPwViewModel.isModifyPwLengthValid.value -> Color(0xFF0DB00C)
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
                            modifyUserPwViewModel.textFieldModifyNewPwValue.value.isBlank() -> Color.LightGray
                            modifyUserPwViewModel.isModifyPwContainsIdValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        contentDescription = "Check",
                        modifier = Modifier.size(20.dp).padding(end = 4.dp) // 아이콘 크기 및 간격 설정
                    )
                    Text(
                        text = "아이디 불가",
                        color = when {
                            modifyUserPwViewModel.textFieldModifyNewPwValue.value.isBlank() -> Color.LightGray
                            modifyUserPwViewModel.isModifyPwContainsIdValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        fontSize = 14.sp
                    )
                }

                // 새 비밀번호 확인
                LikeLionOutlinedTextField(
                    textFieldValue = modifyUserPwViewModel.textFieldModifyCheckPwValue,
                    label = "새 비밀번호 확인*",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                    placeHolder = "새 비밀번호를 입력해 주세요.",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.PASSWORD,
                    inputCondition = "[^a-zA-Z0-9_]",
                    singleLine = true,
                    inputType = LikeLionOutlinedTextFieldInputType.PASSWORD,
                    onValueChange = {
                        modifyUserPwViewModel.modifyCheckPwConditions()
                        modifyUserPwViewModel.updateButtonState()
                    }
                )

                Row(
                    modifier = Modifier.padding(bottom = 20.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        tint = when {
                            modifyUserPwViewModel.textFieldModifyCheckPwValue.value.isBlank() -> Color.LightGray
                            modifyUserPwViewModel.isModifyPwContainsNewPwValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        contentDescription = "Check",
                        modifier = Modifier.size(20.dp).padding(end = 4.dp) // 아이콘 크기 및 간격 설정
                    )
                    Text(
                        text = "비밀번호 일치",
                        color = when {
                            modifyUserPwViewModel.textFieldModifyCheckPwValue.value.isBlank() -> Color.LightGray
                            modifyUserPwViewModel.isModifyPwContainsNewPwValid.value -> Color(0xFF0DB00C)
                            else -> Color(0xFFB00E0E)
                        },
                        fontSize = 14.sp
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            ) {
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    LikeLionFilledButton(
                        text = "취소",
                        modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray,
                        border = BorderStroke(1.dp, color = Color.LightGray),
                        onClick = {
                            modifyUserPwViewModel.modifyCancelOnClick()
                        }
                    )
                }

                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    LikeLionFilledButton(
                        text = "등록",
                        modifier = Modifier.fillMaxWidth(),
                        isEnabled = modifyUserPwViewModel.isButtonEnabled.value,
                        onClick = {
                            modifyUserPwViewModel.modifySuccessOnClick()
                        }
                    )
                }
            }
        }
    }
}