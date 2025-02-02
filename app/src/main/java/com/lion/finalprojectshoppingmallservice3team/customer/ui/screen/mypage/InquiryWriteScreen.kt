package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.Tools
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.InquiryWriteViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InquiryWriteScreen(inquiryWriteViewModel: InquiryWriteViewModel = hiltViewModel()) {

    val context = LocalContext.current
    // 촬영된 사진의 uri를 담을 객체
    lateinit var contentUri: Uri

    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden, // 초기 상태는 Hidden
            skipHiddenState = false // Hidden 상태를 허용
        )
    )

    // 사진 촬영용 런처
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            Tools.takePictureData(context, contentUri, inquiryWriteViewModel.imageBitmapInquiryState)
        }
    }

    // 앨범용 런처
    val albumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            Tools.takeAlbumData(context, it, inquiryWriteViewModel.imageBitmapInquiryState)
        }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(WindowInsets.navigationBars.asPaddingValues())
            ) {
                Text(
                    text = "사진 선택",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // 사진 보관함
                Text(
                    text = "사진 보관함",

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch { scaffoldState.bottomSheetState.hide() }
                            albumLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                        .padding(vertical = 8.dp)
                )

                // 사진 찍기
                Text(
                    text = "사진 찍기",

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch { scaffoldState.bottomSheetState.hide() }
                            val uri = Tools.gettingPictureUri(context)
                            contentUri = uri
                            cameraLauncher.launch(uri)
                        }
                        .padding(vertical = 8.dp)
                )

                // 사진 찍기
                Text(
                    text = "사진 삭제",

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch { scaffoldState.bottomSheetState.hide() }
                            inquiryWriteViewModel.deleteInquiryImageOnClick()
                        }
                        .padding(vertical = 8.dp)
                )
            }
        },
        sheetPeekHeight = 56.dp, // 기본 peek 높이 조정
        modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()) // 전체 스캐폴드에도 패딩 추가
    ) {
        Scaffold(
            topBar = {
                LikeLionTopAppBar(
                    title = "문의하기",
                    backColor = Color.White,
                    menuItems = {
                        LikeLionIconButton(
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
                    .background(Color.White)
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
                        onTrailingIconClick = {
                            inquiryWriteViewModel.updateSubmitButtonState()
                        },
                        onValueChange = {
                            inquiryWriteViewModel.updateSubmitButtonState()
                        },
                        placeHolder = "제목을 입력해 주세요.",
                        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    )

                    LikeLionOutlinedTextField(
                        textFieldValue = inquiryWriteViewModel.inquiryContentValue,
                        label = "내용",
                        inputCondition = "[^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]",
                        trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT,
                        onTrailingIconClick = {
                            inquiryWriteViewModel.updateSubmitButtonState()
                        },
                        onValueChange = {
                            inquiryWriteViewModel.updateSubmitButtonState()
                        },
                        placeHolder = "문의 내용을 작성해 주세요.\n",
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
                            textFieldValue = inquiryWriteViewModel.newFileName,
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
                                coroutineScope.launch {
                                    scaffoldState.bottomSheetState.expand() // 바텀시트를 표시
                                }
                            }
                        )
                    }

                    Text(
                        text = "jpg, png 확장자만 첨부 가능하며,\n" +
                                "10MB까지 가능합니다.",
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
                            isEnabled = inquiryWriteViewModel.isButtonSubmitEnabled.value,
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
}