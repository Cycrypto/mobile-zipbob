package com.example.hansotbob.ui.screen.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.ui.theme.HansotbobTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharingFoodFormScreen(navController: NavController) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)) {

        item {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.primary),
                title = { Text("등록 화면") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle title input change */ },
                label = { Text("제목 입력") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Text("등록 폼")
                Spacer(modifier = Modifier.height(8.dp))
                Text("음식 종류")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle input change */ },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("카테고리")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle input change */ },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("양")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle input change */ },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("제조 일자")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle input change */ },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("거래 장소")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle input change */ },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("거래 방법")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle input change */ },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle description input change */ },
                label = { Text("한줄 설명") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Button(
                onClick = { /* Handle image upload */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text("사진 업로드")
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Button(
                onClick = { /* Handle registration */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("등록")
            }
        }
    }
}


@Preview
@Composable
private fun PreviewSharingFoodFormScreen(){
    HansotbobTheme {
        val navController = rememberNavController()
        SharingFoodFormScreen(navController)
    }
}