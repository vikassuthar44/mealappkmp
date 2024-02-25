package screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import widget.CustomTextView
import widget.ProfilePictureUI

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun HomeScreenUI() {
    val coroutineScope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(false) }
    var dataList by remember {
        mutableStateOf(TrendingItemList.getItemList())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "location icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Column(verticalArrangement = Arrangement.Center) {
                            CustomTextView(
                                text = "Delivery to:",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color.DarkGray
                                )
                            )
                            Row {
                                CustomTextView(
                                    text = "254, Sutharo ka vas Vishala",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.W500,
                                        color = Color.DarkGray
                                    )
                                )
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "arrow drop down",
                                )
                            }
                        }
                    }
                },
                navigationIcon = {
                    ProfilePictureUI(
                        size = 40.dp
                    )
                    /*Box(modifier = Modifier.clip(shape = CircleShape), contentAlignment = Alignment.Center) {
                        Image(painter = painterResource(id = R.drawable.human), contentDescription = "profile")
                    }*/
                    /*IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                        *//*Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource("notification.xml"),
                            contentDescription = "profile"
                        )*//*
                    }*/
                },
                actions = {
                    Row {
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource("notification.xml"),
                                contentDescription = "notification icon",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)//MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = "shopping icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        }

    ) { innerPadding ->
        coroutineScope.launch(Dispatchers.Default) {
            delay(200)
            isVisible = true
        }
        AnimatedVisibility(
            isVisible,
            enter = fadeIn()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(horizontal = 0.dp)
                    .padding(bottom = 70.dp)
            ) {
                LazyColumn {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        SearchWidget()
                        Spacer(modifier = Modifier.height(16.dp))
                        CategoryView()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        TrendingItemHeader()
                    }
                    gridItems(
                        data = dataList,
                        columnCount = 2,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) { itemData ->
                        SingleTrendingView(itemData) {
                            dataList[0].isAdded = true
                        }
                    }
                    /*item {

                    }*/
                }
                /*Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(state = rememberScrollState())
                ) {
                    //Spacer(modifier = Modifier.height(20.dp))
                    SearchWidget()
                    Spacer(modifier = Modifier.height(16.dp))
                    CategoryView()
                    Spacer(modifier = Modifier.height(16.dp))
                    TrendingItemUI()
                }*/
            }
        }
    }
}