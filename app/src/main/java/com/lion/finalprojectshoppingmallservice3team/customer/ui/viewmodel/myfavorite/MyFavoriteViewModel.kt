package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.myfavorite

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
class MyFavoriteViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel()  {
    val shoppingApplication = context as ShoppingApplication
    val chipElements: SnapshotStateList<ChipState> = mutableStateListOf(
        ChipState("크리에이터", mutableStateOf(true)),
        ChipState("마이그룹1", mutableStateOf(false)),
        ChipState("마이그룹2", mutableStateOf(false)),
        ChipState("마이그룹3", mutableStateOf(false)),
        ChipState("마이그룹4", mutableStateOf(false)),
        ChipState("상품", mutableStateOf(false))
    )
    val chipState = ChipStyle(
        selectedColor = Color(0xFFA16DEB),
        selectedTextColor = Color.White,
        unselectedColor = Color.White,
        unselectedTextColor = Color.Black,
        chipTextStyle = Typography.bodySmall,
        chipModifier = Modifier.padding(start = 15.dp, top = 8.dp, bottom = 8.dp, end = 15.dp),
    )

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
    }

    fun myGroupScreen(){
        shoppingApplication.navHostController.navigate("MyFavoriteGroup")
    }

    fun creatorShopScreen(){
        shoppingApplication.navHostController.navigate("CreatorShop")
    }

//    fun handleEvents(event: SaveEvent) {
//        when (event) {
//            is SaveEvent.OnChipClicked -> {
//                updateState {
//                    chipElements.mapIndexed { index, chipState ->
//                        chipState.isSelected.value = index == event.chipIndex
//                    }
//                    copy(
//                        chipElements = chipElements
//                    )
//                }
//            }
//        }
//    }
}