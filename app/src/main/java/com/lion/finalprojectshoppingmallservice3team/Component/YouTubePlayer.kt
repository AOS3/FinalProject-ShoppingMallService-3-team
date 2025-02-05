package com.lion.finalprojectshoppingmallservice3team.Component

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubePlayer(
    videoId: String, // 유튜브 영상 ID
    modifier: Modifier = Modifier,
    isPlaying: Boolean // 가시성 상태 전달받음
) {

    var youTubePlayerRef by remember { mutableStateOf<YouTubePlayer?>(null) }

    // videoId가 변경될 때마다 영상을 업데이트하기 위한 LaunchedEffect 추가
    LaunchedEffect(videoId) {
        youTubePlayerRef?.cueVideo(videoId, 0f)
    }

    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                enableAutomaticInitialization = false
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(player: YouTubePlayer) {
                        youTubePlayerRef = player
//                        youTubePlayer.loadVideo(videoId, 0f) // 영상 로드 및 시작 시간 설정 / loadVideo는 자동재생임
                        player.cueVideo(videoId, 0f) // 자동 재생 없이 영상 로드
                        player.setVolume(0) // 기본 음소거 설정
                    }
                })
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
//        update = { view ->
//            // 현재 재생 중인 비디오와 요청된 비디오가 다른 경우 업데이트
//            youTubePlayerRef?.let { player ->
//                if (player.getCurrentVideoId() != videoId) {
//                    player.cueVideo(videoId, 0f)
//                }
//            }
//        }
    )

    // 재생/일시정지 제어
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            youTubePlayerRef?.play() // 화면에 보이면 재생
        } else {
            youTubePlayerRef?.pause() // 화면에서 사라지면 일시정지
        }
    }
}