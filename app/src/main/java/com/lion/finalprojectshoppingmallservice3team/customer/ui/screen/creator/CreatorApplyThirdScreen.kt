package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCheckBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldInputType
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTriStateCheckBox
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorApplyViewmodel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorApplyThirdScreen(creatorApplyViewModel: CreatorApplyViewmodel = hiltViewModel()) {

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
                            creatorApplyViewModel.navigationThirdIconOnClick()
                        },
                        borderNull = true,
                    )
                },
            )
        },
        bottomBar = {
            LikeLionFilledButton(
                text = "신청하기",
                isEnabled = creatorApplyViewModel.isButtonSubmitEnabled.value,
                containerColor = MainColor,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    creatorApplyViewModel.buttonSubmitOnClick()
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
            ) {
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
            ) {
                Column {
                    Text(
                        text = "개인정보 수집*이용 동의",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )

                    LikeLionTriStateCheckBox(
                        stateValue = creatorApplyViewModel.triCheckboxAllValue,
                        onClick = {
                            creatorApplyViewModel.triCheckboxAllValueOnClick()
                        },
                        text = "전체 항목에 동의 합니다.",
                        fontSize = 14.sp,
                        textModifier = Modifier
                            .scale(1f),
                        modifier = Modifier.padding(start = 0.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LikeLionCheckBox(
                            checkedValue = creatorApplyViewModel.checkboxPersonalInfoAgree,
                            onCheckedChange = {
                                creatorApplyViewModel.checkBoxOnChanged()
                            },
                            text = "*개인 정보 수집 및 이용에 관한\n" +
                                    "내용을 모두 확인하였으며 동의합니다",
                            fontSize = 14.sp,
                            textModifier = Modifier.scale(1f),
                            modifier = Modifier
                                .padding(start = 0.dp)
                        )

                        Text(
                            text = "보기",
                            textDecoration = TextDecoration.Underline,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }

                    /*******************************꼭 읽어주세요! 화면*******************************/

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.2f)),
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.check_24px),
                                contentDescription = null,
                                tint = Color.DarkGray
                            )

                            Text(
                                text = "꼭 읽어주세요!",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                        Text(
                            text = "· 판매하는 콘텐츠의 저작권은 판매 주체인 크리에이터\n" +
                                    " 에게 있으며 사전 접수 신청시 저작권 소유자 확인을 \n" +
                                    " 인증한 것으로 간주합니다.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                        )

                        Text(
                            text = "· 저작권 등 타인의 권리를 침해 하거나 명예를 훼손하는\n" +
                                    " 이미지, 디자인등의 게시에 대한 책임은 크리에이터에\n" +
                                    " 게 있습니다.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                        )

                        Text(
                            text = "· 제공된 개인정보는 개인정보 제공자가 동의한 내용외\n" +
                                    " 에 다른 목적으로 활용하지 않으며, 보유 기간 내에 \n" +
                                    " 제공된 개인정보의 이용을 거부하고자 할 때에 자유롭\n" +
                                    " 게 개인정보 관리 책임자(abcdefg@gmail.com),\n" +
                                    " 고객센터(1234-5678)을 통해 열람,정정,삭제를 요구\n" +
                                    " 할 수 있습니다.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 16.dp,
                                top = 8.dp,
                                end = 16.dp
                            ),
                        )
                    }
                }
            }
        }
    }
}
