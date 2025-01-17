package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun LikeLionTriStateCheckBox(
    stateValue:MutableState<ToggleableState> = mutableStateOf(ToggleableState.Off),
    onClick: () -> Unit = {},
    fontSize: TextUnit =  TextUnit.Unspecified,
    modifier: Modifier,
    fontWeight: FontWeight? = null,
    textModifier: Modifier,
    checkedColor: Color = MainColor,
    uncheckedColor: Color = Color.LightGray,
    text: String = "",
    paddingTop:Dp = 0.dp,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(top = paddingTop)
    ) {
        TriStateCheckbox(
            state = stateValue.value,
            modifier = modifier,
            onClick = {
                if(stateValue.value == ToggleableState.On){
                    stateValue.value = ToggleableState.Off
                } else {
                    stateValue.value = ToggleableState.On
                }

                onClick()
            },
            colors = CheckboxDefaults.colors(
                checkedColor = checkedColor,
                checkmarkColor = Color.White,
                uncheckedColor = uncheckedColor
            ),
        )
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = textModifier.padding().clickable {
                if(stateValue.value == ToggleableState.On){
                    stateValue.value = ToggleableState.Off
                } else {
                    stateValue.value = ToggleableState.On
                }

                onClick()
            }
        )
    }
}