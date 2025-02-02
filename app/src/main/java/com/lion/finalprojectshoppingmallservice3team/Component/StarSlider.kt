package com.lion.finalprojectshoppingmallservice3team.Component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarSlider(
    value: Float = 0.5F,
    starSize:Int = 22,
    starColor:Color = MainColor,
    enabled: Boolean = true
) {
    var half by remember { mutableIntStateOf(0) }
    var fill by remember { mutableIntStateOf(0) }
    var empty by remember { mutableIntStateOf(0) }
    val offsetWidth = starSize/2 + 8
    val width = 5*offsetWidth + (offsetWidth/8)
    Log.d("st","width $width $offsetWidth")

    Slider(
        modifier = Modifier.size(width.dp,starSize.dp),
        state = SliderState(
            value = value,
            steps = 9,
            valueRange = 0.1f..1f
        ),
        enabled = enabled,
        thumb = {

        },
        track = { sliderState ->

            val value = (sliderState.value*10).toInt()
            val endValue = (sliderState.valueRange.endInclusive*10).toInt()
            val count = value/(endValue/(sliderState.steps))
            val dCount = endValue - count
            half = if (count%2!=0 || dCount%2!=0)
                1
            else 0

            fill = count/2
            empty = dCount/2
            // 경고 (console 과부화)
            //Log.d("st","$fill $half $empty")
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                        .height(starSize.dp)
                ) {
                    for (i in 0..4){
                            Icon(
                                imageVector =
                                    ImageVector.vectorResource(
                                        if (fill != 0) {
                                            fill--
                                            R.drawable.star_fill_24px
                                        }else if (half != 0){
                                            half--
                                            R.drawable.star_half_24px
                                        } else {
                                            empty--
                                            R.drawable.star_24px
                                        }
                                    ),
                                contentDescription = "",
                                tint = starColor,
                                modifier = Modifier.offset((i*offsetWidth).dp,0.dp)
                            )
                    }
                }


            }
        }


    )
}