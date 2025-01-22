package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionMyCheerItem
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.MyCheerViewModel

@Composable
fun MyCheerScreen(myCheerViewModel: MyCheerViewModel = hiltViewModel()) {
    // 더미 데이터: SnapshotStateList로 관리
    val dummyCheerItems: SnapshotStateList<Map<String, *>> = remember {
        mutableStateListOf(
            mapOf(
                "creatorName" to "크리에이터 1",
                "email" to "a****@gmail.com",
                "date" to "2025.01.09",
                "content" to "크리에이터에게 하고싶은 말 아무거나 마구 적어",
                "attachment" to R.drawable.kakao_login_logo,
                "likes" to 10
            ),
            mapOf(
                "creatorName" to "크리에이터 2",
                "email" to "b****@gmail.com",
                "date" to "2025.01.08",
                "content" to "이 제품은 정말 좋습니다.",
                "attachment" to null,
                "likes" to 5
            ),
            mapOf(
                "creatorName" to "크리에이터 2",
                "email" to "c****@gmail.com",
                "date" to "2025.01.07",
                "content" to "다음에도 구매할 계획입니다!",
                "attachment" to null,
                "likes" to 8
            )
        )
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp)
        ) {
            var previousCreatorName: String? = null
            // LikeLionList 사용
            LikeLionList(
                dataList = dummyCheerItems,
                rowComposable = { cheer ->
                    // 현재 항목의 크리에이터 이름
                    val currentCreatorName = cheer["creatorName"] as? String

                    // 크리에이터 이름 표시 여부 결정
                    val showCreatorName = currentCreatorName != previousCreatorName
                    previousCreatorName = currentCreatorName // 현재 이름을 이전 이름으로 업데이트
                    // 각 데이터 항목을 LikeLionMyCheerItem으로 렌더링
                    LikeLionMyCheerItem(
                        cheer = cheer,
                        showCreatorName = showCreatorName)
                },
                onRowClick = {
                    // 항목 클릭 이벤트 처리
                    println("항목 클릭됨!")
                }
            )
        }
    }
}