package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionAccordion
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.CancelRefundFAQViewModel

@Composable
fun CancelRefundFAQScreen (cancelRefundFAQViewModel: CancelRefundFAQViewModel = hiltViewModel()){
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.White,
                title = "FAQ",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    cancelRefundFAQViewModel.navigationButtonClick()
                },
            )
        },
    ){
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
                .verticalScroll(state = scrollState)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                text = "취소/환불",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            LikeLionAccordion(
                title ="제목1" ,
                content = {
                    Text(
                        text = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용용",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="제목2" ,
                content = {
                    Text(
                        text = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용용",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="제목3" ,
                content = {
                    Text(
                        text = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내" +
                                "내용내용내용내용내용내용내용내용내용내\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용용",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="제목4" ,
                content = {
                    Text(
                        text = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용용",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="제목5" ,
                content = {
                    Text(
                        text = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용내용내용내용" +
                                "내용내용내용내용내용내용내용내용내용용",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
        }
    }

}