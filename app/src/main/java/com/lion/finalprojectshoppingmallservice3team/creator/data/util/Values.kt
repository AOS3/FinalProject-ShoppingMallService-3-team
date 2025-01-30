package com.lion.finalprojectshoppingmallservice3team.creator.data.util

// 사용자 상태
enum class CreatorState(val number:Int, val str:String){
    // 정상
    Creator_STATE_NORMAL(1, "정상"),
    // 탈퇴
    Creator_STATE_SIGNOUT(2, "탈퇴")
}

// 사용자 상태
enum class ShopState(val number:Int, val str:String){
    // 정상
    Shop_STATE_NORMAL(1, "정상"),
    // 탈퇴
    Shop_STATE_SIGNOUT(2, "탈퇴")
}