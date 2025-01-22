package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCheckBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldInputType
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.InquiryProductWriteViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor

@Composable
fun InquiryProductWriteScreen(
    inquiryProductWriteViewModel: InquiryProductWriteViewModel = hiltViewModel(),
) {


//    var product by remember { mutableStateOf<Product?>(null) }
//
//    LaunchedEffect(productDocumentId) {
//        inquiryProductWriteViewModel.gettingProductData(productDocumentId) {
//            product = it
//        }
//    }

    val focusManager = LocalFocusManager.current
    Scaffold (
        modifier = Modifier.clickable(
            // 리플 효과 제거
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            focusManager.clearFocus()
        },
        topBar = {
            LikeLionTopAppBar(
                title = inquiryProductWriteViewModel.topAppBarTitle.value,
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(R.drawable.close_24px),
                        iconButtonOnClick = {
                            inquiryProductWriteViewModel.closeButtonClick()
                        },
                        padding = 10.dp,
                        borderNull = true
                    )
                },
                backColor = Color.White,
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                LikeLionFilledButton(
                    onClick = { inquiryProductWriteViewModel.cancelButtonClick()},
                    modifier = Modifier.weight(1f),
                    contentColor = MainColor,
                    containerColor = Color.White,
                    border = BorderStroke(1.dp, MainColor),
                    text = "취소"
                )

                Spacer(modifier = Modifier.width(8.dp))

                LikeLionFilledButton(
                    onClick = { inquiryProductWriteViewModel.onSubmitInquiryProduct()},
                    modifier = Modifier.weight(1f),
                    text = "문의 접수",
                    isEnabled = inquiryProductWriteViewModel.isButtonEnabled.value
                )
            }
        }
    ) {
        Column (modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(it)
            .padding(10.dp)
            )
        {
            Column(
                modifier = Modifier.weight(1f)
            ){
                LikeLionOutlinedTextField(
                    textFieldValue = inquiryProductWriteViewModel.textFieldInquiryProductNameValue,
                    label = "이름",
                    inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    placeHolder = "이름을 입력해주세요.",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    onValueChange = {
                        inquiryProductWriteViewModel.textFieldInquiryProductNameValue.value = it
                        inquiryProductWriteViewModel.updateButtonState()
                    },
                )

                LikeLionOutlinedTextField(
                    textFieldValue = inquiryProductWriteViewModel.textFieldInquiryProductPhoneNumberValue,
                    label = "연락처",
                    inputCondition = "[^0-9]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    inputType = LikeLionOutlinedTextFieldInputType.NUMBER,
                    placeHolder = " \'-\' 없이 연락처를 입력해주세요. ",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    onValueChange = {
                        inquiryProductWriteViewModel.textFieldInquiryProductPhoneNumberValue.value = it
                        inquiryProductWriteViewModel.updateButtonState()
                    },
                )

                LikeLionOutlinedTextField(
                    textFieldValue = inquiryProductWriteViewModel.textFieldInquiryProductTitleValue,
                    label = "제목",
                    inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    placeHolder = "제목을 입력해 주세요.",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    onValueChange = {
                        inquiryProductWriteViewModel.textFieldInquiryProductTitleValue.value = it
                        inquiryProductWriteViewModel.updateButtonState()
                    }
                )

                LikeLionOutlinedTextField(
                    textFieldValue = inquiryProductWriteViewModel.textFieldInquiryProductContentValue,
                    label = "문의내용",
                    inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                    placeHolder = "문의 내용을 자세하게 작성해 주세요.\n" +
                            "동일한 내용을 반복적으로 문의하거나\n" +
                            "욕설, 폭언 등의 사용은 지양해 주세요.",
                    modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp),
                    onValueChange = {
                        inquiryProductWriteViewModel.textFieldInquiryProductContentValue.value = it
                        inquiryProductWriteViewModel.updateButtonState()
                    }
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
                        textFieldValue = inquiryProductWriteViewModel.textFieldInquiryFileContentValue,
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
                Spacer(modifier = Modifier.height(10.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    LikeLionCheckBox(
                        text = "개인 정보 수집 이용 동의",
                        checkedValue = inquiryProductWriteViewModel.isCheckBoxChecked,
                        onCheckedChange = {
                            inquiryProductWriteViewModel.updateButtonState()
                        },
                        modifier = Modifier,
                        textModifier = Modifier
                    )
                    Text(
                        modifier = Modifier.clickable {  },
                        text = "보기",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Gray
                    )
                }

            }

        }
    }
}