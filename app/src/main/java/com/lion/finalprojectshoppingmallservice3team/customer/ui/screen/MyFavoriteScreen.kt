package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import android.R.attr.text
import android.annotation.SuppressLint
import android.util.Log.i
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionBigUserListView
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionChipGroup
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionSmallUserListView
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.customer.ui.data.model.Customer
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.MyFavoriteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyFavoriteScreen(myFavoriteViewModel: MyFavoriteViewModel = hiltViewModel()){
    var selected by remember{
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {

                },
                backColor = Color.White,
            )
        },
        modifier = Modifier.background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it).background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LikeLionChipGroup(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                elements = myFavoriteViewModel.chipElements,
                chipStyle =  myFavoriteViewModel.chipState,
                onChipClicked = {content,isSelected,idx ->
                    myFavoriteViewModel.setEvent(idx)
                }
            )

            var list: MutableList<Customer> = MutableList(10, { Customer("사용자", " ") })

            LikeLionSmallUserListView(list)
            LikeLionBigUserListView(list)
        }

    }
}