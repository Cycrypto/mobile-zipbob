package com.example.hansotbob.component.common.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.hansotbob.R
import com.example.hansotbob.dto.AuthorData
import com.example.hansotbob.service.FirebaseService
import com.example.hansotbob.viewmodel.ViewModelFactory
import com.example.hansotbob.viewmodel.screen.detail.PostAuthorDataViewModel



@Composable
fun PostAuthordata(
    authorId: String,
    modifier: Modifier = Modifier,
    authorViewModel: PostAuthorDataViewModel = viewModel(factory = ViewModelFactory(FirebaseService()))
) {
    val authorData by authorViewModel.author.collectAsState()

    LaunchedEffect(authorId) {
        authorViewModel.loadItem(authorId)
    }

    authorData?.let { author ->
        val imagePainter = if (author.profileImageUrl == "__NULL__") {
            painterResource(id = R.drawable.person_icon)
        } else {
            rememberAsyncImagePainter(
                model = author.profileImageUrl,
                error = painterResource(id = R.drawable.person_icon)
            )
        }

        Column {
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 0.dp)
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .semantics(mergeDescendants = true) {}
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = "Author Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(
                        text = author.nickname,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    } ?: run {
        // Handle the case when authorData is null
        Text("Loading...", modifier = modifier)
    }
}
