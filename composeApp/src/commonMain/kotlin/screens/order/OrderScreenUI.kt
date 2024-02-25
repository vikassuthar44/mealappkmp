package screens.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OderScreenUI(
    onTrackOrder: (Int) -> Unit,
    orderAgain: (Int) -> Unit
) {
    var selectedTabPosition by remember {
        mutableStateOf(OrderTab.ACTIVE)
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "Orders",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W800,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            },
            actions = {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        )
    }) { innerPadding ->
        Column {
            TabView(
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
            ) { orderTab ->
                selectedTabPosition = orderTab
                /*when(orderTab) {
                    OrderTab.ACTIVE -> {
                        ActiveOrderScreenUI()
                    }

                    OrderTab.COMPLETED -> {

                    }
                    OrderTab.CANCELLED -> {

                    }
                }*/
            }
            when (selectedTabPosition) {
                OrderTab.ACTIVE -> {
                    ActiveOrderScreenUI(
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                        orderType = OrderType.ACTIVE,
                        onTrackOrder = { orderId ->
                            onTrackOrder.invoke(orderId)
                        },
                        orderAgain = { orderId ->
                            orderAgain.invoke(orderId)
                        }
                    )
                }

                OrderTab.COMPLETED -> {
                    ActiveOrderScreenUI(
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                        orderType = OrderType.COMPLETE,
                        onTrackOrder = {

                        },
                        orderAgain = {

                        }
                    )
                }

                OrderTab.CANCELLED -> {
                    ActiveOrderScreenUI(
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                        orderType = OrderType.CANCELLED,
                        onTrackOrder = {

                        },
                        orderAgain = {

                        }
                    )
                }
            }
        }
    }
}