package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionBigUserListView
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.MyRecentCreatorViewModel

@Composable
fun MyRecentCreatorScreen(myRecentCreatorViewModel: MyRecentCreatorViewModel = hiltViewModel()) {

    // ViewModel에서 사용자 데이터를 가져온다.
    val recentCreators by myRecentCreatorViewModel.recentCreators.collectAsState(initial = emptyList())

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
        ) { // 화면에 데이터를 표시
            LikeLionBigUserListView(randomUsers = recentCreators) }
    }

}