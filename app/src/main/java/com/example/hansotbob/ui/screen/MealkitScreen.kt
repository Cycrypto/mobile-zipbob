package com.example.hansotbob.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hansotbob.R
import com.example.hansotbob.component.common.ItemBar
import com.example.hansotbob.data.ListItem
import com.example.hansotbob.fragment.CategoryFragmentContainer

@Composable
fun MealkitScreen(navController:NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        CategoryFragmentContainer(navController, createDummyData())
    }
}

private fun createDummyData(): List<ListItem.MealkitsContent> {
    return listOf(
        ListItem.MealkitsContent(R.drawable.mealkits_image,"밀키트 팝니다 ","1","시흥시 정왕동","10000"),
        ListItem.MealkitsContent(R.drawable.mealkits_image,"밀키트 팝니다 ","1","시흥시 정왕동","10000"),
        ListItem.MealkitsContent(R.drawable.mealkits_image,"밀키트 팝니다 ","1","시흥시 정왕동","10000"),
        ListItem.MealkitsContent(R.drawable.mealkits_image,"밀키트 팝니다 ","1","시흥시 정왕동","10000"),
        ListItem.MealkitsContent(R.drawable.mealkits_image,"밀키트 팝니다 ","1","시흥시 정왕동","10000"),
        ListItem.MealkitsContent(R.drawable.mealkits_image,"밀키트 팝니다 ","1","시흥시 정왕동","10000")
    )
}