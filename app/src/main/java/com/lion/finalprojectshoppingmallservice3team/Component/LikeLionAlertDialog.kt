package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun LikeLionAlertDialog(
    // 다이얼로그를 보여주는 상태
    showDialogState : MutableState<Boolean>,
    confirmButtonTitle : String = "확인",
    confirmButtonOnClick : () -> Unit = {
        showDialogState.value = false
    },
    dismissButtonTitle : String? = null,
    dismissButtonOnClick : () -> Unit = {
        showDialogState.value = false
    },
    containerColor : Color = MainColor,
    contentColor : Color = Color.White,
    buttomModifier: Modifier,
    titleAlign : TextAlign? = null,
    textModifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    icon : ImageVector? = null,
    title : String? = null,
    text : String? = null,
) {
    if(showDialogState.value){
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            // 다이얼로그가 닫히는 사건이 발생했을 때
            onDismissRequest = {
                showDialogState.value = false
            },
            // 확인 버튼
            confirmButton = {
                LikeLionFilledButton(
                    modifier = buttomModifier,
                    containerColor = containerColor,
                    contentColor = contentColor,
                    text = confirmButtonTitle,
                    onClick = {
                        confirmButtonOnClick()
                    }
                )
            },
            // 취소 버튼
            dismissButton = if(dismissButtonTitle != null){
                {
                    TextButton(
                        onClick = {
                            dismissButtonOnClick()
                        }
                    ) {
                        Text(text = dismissButtonTitle)
                    }
                }
            } else {
                null
            },
            icon = if(icon != null){
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                }
            } else {
                null
            },
            title = if(title != null){
                {
                    Text(
                        text= title,
                        textAlign = titleAlign,
                        modifier = titleModifier
                    )
                }
            } else {
                null
            },
            text = if(text != null){
                {
                    Text(
                        text = text,
                        textAlign = textAlign,
                        modifier = textModifier
                    )
                }
            } else {
                null
            },

            )
    }
}