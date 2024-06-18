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
        ListItem.MealkitsContent(R.drawable.mealkits_image, "밀키트 팝니다", "시흥시 정왕동", "10000", "한식", "저녁 식사", "2인분", "2022-07-15", "직거래", "신비합니다", 1),
        ListItem.MealkitsContent(R.drawable.mealkits_image, "밀키트 팝니다", "시흥시 정왕동", "10000", "한식", "저녁 식사", "2인분", "2022-07-14", "직거래", "신비합니다", 1),
        ListItem.MealkitsContent(R.drawable.mealkits_image, "밀키트 팝니다", "시흥시 정왕동", "10000", "한식", "저녁 식사", "2인분", "2022-07-13", "직거래", "신비합니다", 2),
        ListItem.MealkitsContent(R.drawable.mealkits_image, "밀키트 팝니다", "시흥시 정왕동", "10000", "한식", "저녁 식사", "2인분", "2022-07-12", "직거래", "신비합니다", 1),
        ListItem.MealkitsContent(R.drawable.mealkits_image, "밀키트 팝니다", "시흥시 정왕동", "10000", "한식", "저녁 식사", "2인분", "2022-07-11", "직거래", "신비합니다", 0),
        ListItem.MealkitsContent(R.drawable.mealkits_image, "밀키트 팝니다", "시흥시 정왕동", "10000", "한식", "저녁 식사", "2인분", "2022-07-10", "직거래", "신비합니다", 2)
    )
}

