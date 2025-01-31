package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator

import android.R.attr.enabled
import android.content.Context
import androidx.annotation.HalfFloat
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.ui.theme.Typography
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CreatorMainViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication
    val chipElements: SnapshotStateList<ChipState> = mutableStateListOf(
        ChipState("전체", mutableStateOf(true)),
        ChipState("개인", mutableStateOf(false)),
        ChipState("기업", mutableStateOf(false)),
    )
    val companyEnabled = mutableStateOf(false)
    val companyCurrent = mutableStateOf("")
    val chipState = ChipStyle(
        selectedColor = Color(0xFFA16DEB),
        selectedTextColor = Color.White,
        unselectedColor = Color.White,
        unselectedTextColor = Color.Black,
        chipTextStyle = Typography.bodySmall,
        chipModifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 10.dp)
    )

    val searchText = mutableStateOf("")

    var companyList = mutableStateListOf<String>()

    fun setEvent(idx:Int){
        chipElements.forEach {
            if (it == chipElements[idx])
                it.apply {
                    isSelected.value = true
                }
            else
                it.apply {
                    isSelected.value = false
                }

        }
        if (chipElements[2].isSelected.value){
            companyEnabled.value = true
        }else{
            companyEnabled.value = false
        }
    }
}