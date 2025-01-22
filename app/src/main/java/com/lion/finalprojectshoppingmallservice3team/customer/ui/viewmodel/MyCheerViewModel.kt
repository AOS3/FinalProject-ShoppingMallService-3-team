package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MyCheerViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){
}