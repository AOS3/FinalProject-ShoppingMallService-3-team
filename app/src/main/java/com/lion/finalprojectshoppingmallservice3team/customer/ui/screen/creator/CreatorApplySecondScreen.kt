package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldInputType
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorApplyViewmodel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorApplySecondScreen(creatorApplyViewModel: CreatorApplyViewmodel = hiltViewModel()) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "신청하기",
                        style = TextStyle(fontSize = 24.sp)
                    )
                },
                navigationIcon = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.arrow_back_24px),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        iconButtonOnClick = {
                            creatorApplyViewModel.navigationSecondIconOnClick()
                        },
                        borderNull = true,
                    )
                },
            )
        },
        bottomBar = {
            LikeLionFilledButton(
                text = "다음",
                isEnabled = creatorApplyViewModel.isButtonThirdEnabled.value,
                containerColor = MainColor,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    creatorApplyViewModel.buttonSecondNextOnClick()
                }
            )
        }
    ) { paddingValue ->

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
                .background(MainColor.copy(alpha = 0.1f))
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            /*******************************상단 텍스트*******************************/
            Text(
                text = "MY CREATOR",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MainColor,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "마크샵 크리에이터 신청하기",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "마크샵에서 크리에이터가 되어 팬들과 소통해보세요\n" +
                        "개인 또는 기업 누구나 신청할 수 있으며\n" +
                        "저작권을 보유한 상품과 콘텐츠만 있으면 입정할 수 있습니다",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = "* 필수 입력",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Blue,
                    modifier = Modifier.padding(end = 16.dp, top = 24.dp)
                )
            }


            /*******************************연락처 입력 하는곳*******************************/
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ){
                Column {
                    Text(
                        text = "연락처 정보",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    // 연락처
                    Text(
                        text = "연락처 *",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionOutlinedTextField(
                        textFieldValue = creatorApplyViewModel.creatorPhoneNumber,
                        label = "연락처",
                        onValueChange = {
                            creatorApplyViewModel.updateApplyThirdButtonState()
                        },
                        placeHolder = "하이폰(-) 없이 숫자만 입력해 주세요.",
                        inputType = LikeLionOutlinedTextFieldInputType.NUMBER,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    )
                }
            }


            /*******************************포트폴리오 입력 하는곳*******************************/
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ){
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "포트 폴리오",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 16.dp)
                                .weight(1f)
                        )

                        Text(
                            text = "· 1개 이상 필수 입력",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 16.dp, top = 16.dp)
                        )
                    }
                    // 대표 SNS
                    Text(
                        text = "대표 SNS",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionOutlinedTextField(
                        textFieldValue = creatorApplyViewModel.bestSns,
                        onValueChange = {
                            creatorApplyViewModel.updateApplyThirdButtonState()
                        },
                        label = "대표 SNS",
                        placeHolder = "운영하고 계신 SNS 채널 주소를 입력해 주세요",
                        inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    // 포트폴리오 파일
                    Text(
                        text = "포트폴리오 파일",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )
                    Text(
                        text = "JPG,PNG,PPT,KEYNOTE,PDF 형식의 50MB 이하의 파일만\n" +
                                "업로드 할 수 있습니다 (최대 3개 업로드 가능)",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    LikeLionFilledButton(
                        text = "파일 업로드",
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 16.dp, top = 8.dp),
                        onClick = {/* 포폴 파일 업로드 */ }
                    )

                    // 포트폴리오 사이트
                    Text(
                        text = "포트폴리오 사이트",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionOutlinedTextField(
                        textFieldValue = creatorApplyViewModel.portfolioSite,
                        onValueChange = {
                            creatorApplyViewModel.updateApplyThirdButtonState()
                        },
                        label = "포트폴리오 사이트",
                        placeHolder = "개인 웹사이트,노트폴리오,비헨스 URL 등",
                        inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    )
                }
            }
        }
    }
}