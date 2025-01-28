package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TopAppBar
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
fun CreatorApplyScreen(creatorApplyViewModel: CreatorApplyViewmodel = hiltViewModel()) {
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
                            creatorApplyViewModel.navigationFirstIconOnClick()
                        },
                        borderNull = true,
                    )
                },
            )
        },
        bottomBar = {
            LikeLionFilledButton(
                text = "다음",
                isEnabled = creatorApplyViewModel.isButtonSecondEnabled.value,
                containerColor = MainColor,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    creatorApplyViewModel.buttonFirstNextOnClick()
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

            /*******************************입력 하는곳*******************************/
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ){
                Column {
                    Text(
                        text = "기본 정보",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    // 샵 이름
                    Text(
                        text = "샵 이름 *",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionOutlinedTextField(
                        label = "샵 이름",
                        onValueChange = {
                            creatorApplyViewModel.updateApplySecondButtonState()
                        },
                        textFieldValue = creatorApplyViewModel.shopName,
                        placeHolder = "샵 이름을 입력주세요",
                        inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    // 도메인명
                    Text(
                        text = "도메인명 *",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionOutlinedTextField(
                        label = "도메인명",
                        onValueChange = {
                            creatorApplyViewModel.updateApplySecondButtonState()
                        },
                        textFieldValue = creatorApplyViewModel.domainName,
                        placeHolder = "your_domain",
                        inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    // 본인 또는 브랜드 소개
                    Text(
                        text = "본인 또는 브랜드 소개 *",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionOutlinedTextField(
                        textFieldValue = creatorApplyViewModel.brandDescription,
                        label = "본인 또는 브랜드 소개",
                        onValueChange = {
                            creatorApplyViewModel.updateApplySecondButtonState()
                        },
                        placeHolder = "본인이나 브랜드에 대한 소개를 남겨주세요.",
                        inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    // 회사명
                    Text(
                        text = "회사명 *",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionOutlinedTextField(
                        label = "회사명",
                        onValueChange = {
                            creatorApplyViewModel.updateApplySecondButtonState()
                        },
                        textFieldValue = creatorApplyViewModel.companyName,
                        placeHolder = "회사 이름을 입력해주세요.",
                        inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    // 회사 서류 제출
                    Text(
                        text = "회사 서류 제출",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )
                    Text(
                        text = "회사 서류를 제출해주세요. 제출받은 서류는 담당팀에서 심사\n" +
                                "를 거쳐 결과를 전달해주겠습니다. 심사 기간은 최대 5일이\n" +
                                "소비될 수 있습니다.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    LikeLionFilledButton(
                        text = "파일 업로드",
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 16.dp, top = 8.dp, bottom = 16.dp),
                        onClick = {/* 파일 업로드 */ }
                    )

                }
            }
        }
    }
}