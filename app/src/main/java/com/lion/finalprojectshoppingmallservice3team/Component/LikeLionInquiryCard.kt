package com.lion.finalprojectshoppingmallservice3team.Component

import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor
import kotlinx.coroutines.tasks.await

@Composable
fun LikeLionInquiryCard(item: Map<String, *>) {
    val isAnswer = (item["isAnswer"] as? Boolean) ?: false
    val backgroundColor = if (isAnswer) SubColor else Color(0xFFE0E0E0) // 답변 여부에 따른 배경색

    val fileName = item["attachment"] as? String

    var imageUrl by remember { mutableStateOf<String?>(null) }

    // 🔥 Firebase Storage에서 다운로드 URL 가져오기
    LaunchedEffect(fileName) {
        if (!fileName.isNullOrEmpty()) {
            val storageRef = FirebaseStorage.getInstance().reference.child("image/$fileName")
            try {
                imageUrl = storageRef.downloadUrl.await().toString() // ✅ URL 비동기 가져오기
            } catch (e: Exception) {
                Log.e("FirebaseStorage", "이미지 로드 실패: ${e.message}")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        // 작성자 및 날짜
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = (item["author"] as? String) ?: "",
                fontSize = 14.sp,
                color = if (isAnswer) Color(0xFF673AB7) else Color.Black // 답변이면 보라색
            )
            Text(
                text = (item["date"] as? String) ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 제목 (문의일 때만 표시)
        if (!isAnswer) {
            Text(
                text = "제목: ${(item["title"] ?: "제목 없음") as String}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        // 내용
        Text(
            text = if (isAnswer) "답변 내용: ${(item["content"] ?: "")}" else "내용: ${(item["content"] ?: "")}",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        // ✅ Glide를 이용한 이미지 표시
        if (!imageUrl.isNullOrEmpty()) {
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                },
                update = { imageView ->
                    Glide.with(imageView.context)
                        .load(imageUrl)
                        .into(imageView)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        } else {
            // 첨부 파일
            Text(
                text = "첨부파일: - ",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}