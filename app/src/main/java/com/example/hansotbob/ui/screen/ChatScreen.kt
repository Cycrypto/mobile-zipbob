package com.example.hansotbob.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.hansotbob.R
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.ui.theme.PrimaryColor
import com.example.hansotbob.ui.theme.ReviewInputColor
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.screen.detail.PostAuthorDataViewModel

data class Message(val text: String, val isUser: Boolean)

val messages = listOf(
    Message("시간 맞춰오시는거죠?", isUser = false),
    Message("네! 지금 거의 도착했어요!", isUser = true),
    Message("네네 저도 근처입니다", isUser = false),
    Message("네네!", isUser = true),
    Message("GS 앞으로 가겠습니다", isUser = false),
    Message("좋은 하루 보내세요~", isUser = true),
    Message("네 감사합니다~", isUser = false)
)

@Composable
fun ChatScreen(
    navController: NavHostController,
    authorId: String,
    itemId: String,
    viewModel: PostAuthorDataViewModel = viewModel(
        factory = ViewModelFactory(FirebaseService())
    )
) {

    val authorData by viewModel.author.collectAsState()

    LaunchedEffect(authorId) {
        viewModel.loadItem(authorId)
    }

    var message by remember { mutableStateOf(TextFieldValue("")) }
    val profileImageUrl = authorData?.profileImageUrl ?: "__NULL__"

    val imagePainter = if (profileImageUrl == "__NULL__") {
        painterResource(id = R.drawable.person_icon)
    } else {
        rememberAsyncImagePainter(
            model = profileImageUrl,
            error = painterResource(id = R.drawable.person_icon)
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(authorData?.nickname ?: "Loading...", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {/* onMenuClick */ }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    items(messages) { message ->
                        if (message.isUser) {
                            UserMessageBubble(message.text)
                        } else {
                            OpponentMessageBubble(message.text, imagePainter)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ReviewInputColor)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("메세지 보내기", color = ReviewInputColor) },
                        shape = RoundedCornerShape(40.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { /* Send message action */ },
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Gray, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowUpward,
                            contentDescription = "Send",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun UserMessageBubble(message: String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .background(PrimaryColor, shape = RoundedCornerShape(20.dp))
                .padding(11.dp)
        ) {
            Text(message, fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun OpponentMessageBubble(message: String, imagePainter: Painter) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top

    ) {
        Image(
            painter = imagePainter,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Transparent),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .background(ReviewInputColor, shape = RoundedCornerShape(20.dp))
                .padding(11.dp)
        ) {
            Text(message, fontSize = 16.sp)
        }
    }
}



