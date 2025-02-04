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

    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayerRef = youTubePlayer
//                        youTubePlayer.loadVideo(videoId, 0f) // 영상 로드 및 시작 시간 설정 / loadVideo는 자동재생임
                        youTubePlayer.cueVideo(videoId, 0f) // 자동 재생 없이 영상 로드
                        youTubePlayer.setVolume(0) // 기본 음소거 설정
                    }
                })
            }
        },
        modifier = modifier.fillMaxWidth().height(250.dp)
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