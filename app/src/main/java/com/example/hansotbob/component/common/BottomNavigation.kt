package com.example.hansotbob.component.common

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.FoodBank
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.NoteAdd
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.SetMeal
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme
@Composable
fun HansotThemeNavigationBar(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White, shape = RoundedCornerShape(35.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.navigate("overview") }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(0xFFF3D398), CircleShape)
                            .padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(30.dp),
                            tint = Color(0xFFFFA500)
                        )
                    }
                }
            }
            IconButton(onClick = { navController.navigate("food_share") }) {
                Icon(
                    imageVector = Icons.Outlined.Restaurant,
                    contentDescription = "Sharing Food",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFFFA500)
                )
            }
            IconButton(onClick = { navController.navigate("mealkit_share") }) {
                Icon(
                    imageVector = Icons.Outlined.FoodBank,
                    contentDescription = "Sharing Mealkits",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFFFFA500)
                )
            }
            IconButton(onClick = { /*TODO: Settings navigation action*/ }) {
                Icon(
                    imageVector = Icons.Outlined.MoreHoriz,
                    contentDescription = "Settings",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFFFA500)
                )
            }
            Box(modifier = Modifier.offset(y = (-35).dp, x = (-16).dp)) {
                FloatingActionButton(
                    onClick = { expanded = !expanded },
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.EditNote,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    DropdownMenuItem(
                        text = { Text("집밥 나눔글 쓰기") },
                        onClick = {
                            expanded = false
                            navController.navigate("foodshare_form")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("밀키트 나눔글 쓰기") },
                        onClick = {
                            expanded = false
                            navController.navigate("mealkit_form")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("재료 나눔글 쓰기") },
                        onClick = { expanded = false /* TODO: Action C */ }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewBottomNavigationBar(){
    HansotbobTheme {
        val navController = rememberNavController()
        HansotThemeNavigationBar(navController = navController)
    }
}
