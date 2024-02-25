package screens.message

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import widget.CustomTextView
import widget.ProfilePictureUI

data class MessageList(
    val name: String,
    val lastMessage: String,
    val time: String,
    val seen: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreenUI() {
    val messageList = arrayListOf<MessageList>().apply {
        repeat(20) {
            add(
                MessageList(
                    name = "Bhavesh Kumar",
                    lastMessage = "Great I will arrive soon.",
                    time = "08:35AM",
                    seen = false
                )
            )
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomTextView(
                        text = "Message",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.W800,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                items(messageList) { message ->
                    SingleMessageView(message = message)
                }
            }
        }
    }
}

@Composable
fun SingleMessageView(message: MessageList) {
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .clickable { }
            .background(color = Color.White)
            .padding(vertical = 10.dp, horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfilePictureUI(size = 40.dp)
                Spacer(modifier = Modifier.width(15.dp))
                Column(verticalArrangement = Arrangement.Center) {
                    CustomTextView(
                        text = message.name,
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.W800
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextView(
                        text = message.lastMessage,
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = if (message.seen) Color.Gray else Color.Black
                        )
                    )
                }
            }
            CustomTextView(
                text = message.time,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.W800
                )
            )
        }
    }
}