package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.InquiryWriteViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor

@Composable
fun InquiryWriteScreen(inquiryWriteViewModel: InquiryWriteViewModel = hiltViewModel()) {

    // 애니메이션 좌우 말고 상하로 변경

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                title = "문의하기",
                backColor = Color.Transparent,
                menuItems = {
                    LikeLionIconButton(
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        icon = ImageVector.vectorResource(R.drawable.close_24px),
                        padding = 10.dp,
                        borderNull = true,
                        iconButtonOnClick = {
                            inquiryWriteViewModel.inquiryWriteCloseOnClick()
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                LikeLionOutlinedTextField(
                    textFieldValue = inquiryWriteViewModel.inquiryTitleValue,
                    label = "제목",
                    inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    placeHolder = "제목을 입력해 주세요.",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
                )

                LikeLionOutlinedTextField(
                    textFieldValue = inquiryWriteViewModel.inquiryContentValue,
                    label = "내용",
                    inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    placeHolder = "문의 내용을 자세하게 작성해 주세요.\n" +
                            "동일한 내용을 반복적으로 문의하거나\n" +
                            "욕설, 폭언 등의 사용은 지양해 주세요.",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "문의 내용은 최대 1000자까지 입력 가능합니다.",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )

                    Text(
                        text = "0 / 1000 글자",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LikeLionOutlinedTextField(
                        textFieldValue = inquiryWriteViewModel.inquiryFileValue,
                        label = "파일",
                        inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                        readOnly = true,
                        trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                        placeHolder = "파일을 첨부해주세요.",
                        modifier = Modifier.fillMaxWidth().weight(1f),
                    )

                    LikeLionFilledButton(
                        text = "파일첨부",
                        containerColor = SubColor,
                        contentColor = MainColor,
                        modifier = Modifier.weight(0.4f).padding(top = 6.dp, start = 10.dp).height(56.dp),
                        onClick = {

                        }
                    )
                }

                Text(
                    text = "jpg, png, zip, pdf 확장자만 첨부 가능하며,\n" +
                            "10MB까지 가능합니다.\n" +
                            "(여러 사진을 첨부해야 한다면 zip파일로 첨부해 주세요.)",
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
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
                            inquiryWriteViewModel.inquiryWriteCancelOnClick()
                        }
                    )
                }

                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    LikeLionFilledButton(
                        text = "문의 접수",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            inquiryWriteViewModel.inquiryWriteSuccessOnClick()
                        }
                    )
                }
            }
        }
    }
}