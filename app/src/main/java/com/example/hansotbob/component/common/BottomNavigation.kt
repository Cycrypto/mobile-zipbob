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
import com.example.hansotbob.R
import com.example.hansotbob.ui.theme.HansotbobTheme

@Composable
fun BasicBottomNavigationBar() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        BottomAppBar(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Home", modifier = Modifier.padding(16.dp))
            Text(text = "Search", modifier = Modifier.padding(16.dp))
            Text(text = "Profile", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun CircularNavigationBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .background(Color(0xFFFFA500), RoundedCornerShape(40.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Favorites",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(40.dp)) // 중앙 아이콘 위치를 위해 공간을 만듦
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-35).dp)
        ) {
            IconButton(
                onClick = { /*TODO: Home navigation action*/ },
                modifier = Modifier
                    .size(70.dp)
                    .background(Color(0xFFFFA500), shape = CircleShape)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Home",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun HansotThemeNavigationBar() {
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
            IconButton(onClick = { /*TODO: Home navigation action*/ }) {
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
            IconButton(onClick = { /*TODO: Calendar navigation action*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Restaurant,
                    contentDescription = "Sharing Food",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFFFFA500)
                )
            }
            IconButton(onClick = { /*TODO: Notifications navigation action*/ }) {
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
                    contentDescription = "Sharing Mealkits",
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
                        onClick = { expanded = false /* TODO: Action A */ }
                    )
                    DropdownMenuItem(
                        text = { Text("밀키트 나눔글 쓰기") },
                        onClick = { expanded = false /* TODO: Action B */ }
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
private fun previewBottomNavigationBar(){
    HansotbobTheme {
        HansotThemeNavigationBar()
    }
}
