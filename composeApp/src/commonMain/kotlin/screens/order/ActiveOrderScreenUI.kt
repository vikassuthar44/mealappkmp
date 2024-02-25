package screens.order

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import widget.CustomTextView

data class ActiveOrder(
    val title: String,
    val itemCount: Int,
    val price: Float,
    val isPaid: Boolean,
    val distance: String,
    val image: String
)

enum class OrderType {
    ACTIVE, COMPLETE, CANCELLED
}

object ActiveOrderList {
    fun getActiveOrder(): List<ActiveOrder> = ArrayList<ActiveOrder>().apply {
        add(
            ActiveOrder(
                title = "Big Gardan Salad",
                itemCount = 3,
                price = 199.30f,
                isPaid = true,
                distance = "2.4 km",
                image = "pizza_trans.png"
            )
        )
        add(
            ActiveOrder(
                title = "Big Gardan Salad",
                itemCount = 3,
                price = 199.30f,
                isPaid = false,
                distance = "2.4 km",
                image = "pizza_trans.png"
            )
        )
        add(
            ActiveOrder(
                title = "Big Gardan Salad",
                itemCount = 3,
                price = 199.30f,
                isPaid = true,
                distance = "2.4 km",
                image = "pizza_trans.png"
            )
        )
        add(
            ActiveOrder(
                title = "Big Gardan Salad",
                itemCount = 3,
                price = 199.30f,
                isPaid = false,
                distance = "2.4 km",
                image = "pizza_trans.png"
            )
        )
    }
}

@Composable
fun ActiveOrderScreenUI(
    modifier: Modifier,
    orderType: OrderType,
    onTrackOrder: (Int) -> Unit,
    orderAgain: (Int) -> Unit
) {
    val activeOrderList = ActiveOrderList.getActiveOrder()
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(
                activeOrderList
            ) { activeOrder ->
                SingleActiveOrder(
                    activeOrder = activeOrder,
                    orderType = orderType,
                    onTrackOrder = { orderId ->
                        onTrackOrder.invoke(orderId)
                    },
                    orderAgain = { orderId ->
                        orderAgain.invoke(orderId)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SingleActiveOrder(
    activeOrder: ActiveOrder,
    orderType: OrderType,
    onTrackOrder: (Int) -> Unit,
    orderAgain: (Int) -> Unit
) {
    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp).clip(shape = RoundedCornerShape(size = 20.dp))
            .background(color = Color.White)
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically { fullHeight ->  fullHeight/2}
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        modifier = Modifier.width(100.dp)
                            .clip(shape = RoundedCornerShape(size = 20.dp))
                            .aspectRatio(1f)
                            .background(color = Color.White),
                        painter = painterResource(activeOrder.image), contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        CustomTextView(
                            text = activeOrder.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.W800, color = Color.Black
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomTextView(
                                text = "${activeOrder.itemCount} items",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 15.sp,
                                    color = Color.Gray
                                )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(
                                modifier = Modifier.height(15.dp).width(1.dp)
                                    .background(color = Color.LightGray)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            CustomTextView(
                                text = "Distance ${activeOrder.distance}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 15.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomTextView(
                                text = "$${activeOrder.price}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.W800,
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Box(
                                modifier = if (activeOrder.isPaid) Modifier.clip(
                                    shape = RoundedCornerShape(
                                        size = 10.dp
                                    )
                                )
                                    .background(color = MaterialTheme.colorScheme.primary)
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 5.dp
                                    ) else Modifier.clip(
                                    shape = RoundedCornerShape(size = 10.dp)
                                )
                                    .background(color = MaterialTheme.colorScheme.primary)
                                    .clickable { }
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                CustomTextView(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    text =
                                    when (orderType) {
                                        OrderType.ACTIVE -> if (activeOrder.isPaid) "Paid" else "Pay"
                                        OrderType.COMPLETE -> "Completed"
                                        OrderType.CANCELLED -> "Cancelled"
                                    },
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = Color.White
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().height(1.dp)
                        .background(color = Color.LightGray)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(size = 10.dp))
                            .border(
                                shape = RoundedCornerShape(size = 10.dp),
                                color = if (orderType == OrderType.ACTIVE || orderType == OrderType.CANCELLED) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                                width = 1.dp
                            ).clickable { }
                            .padding(horizontal = 30.dp, vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomTextView(
                            text =
                            when (orderType) {
                                OrderType.ACTIVE -> "Cancel Order"
                                OrderType.COMPLETE -> "Leave a Review"
                                OrderType.CANCELLED -> "Cancelled"
                            },
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (orderType == OrderType.ACTIVE || orderType == OrderType.CANCELLED) MaterialTheme.colorScheme.error else
                                    MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                    Box(
                        modifier = Modifier.clip(
                            shape = RoundedCornerShape(size = 10.dp),
                        ).background(color = MaterialTheme.colorScheme.primary)
                            .clickable {
                                when (orderType) {
                                    OrderType.ACTIVE -> {
                                        onTrackOrder.invoke(123456)
                                    }

                                    else -> {
                                        orderAgain.invoke(123456)
                                    }
                                }
                            }
                            .padding(horizontal = 30.dp, vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomTextView(
                            text = when (orderType) {
                                OrderType.ACTIVE -> "Track Order"
                                OrderType.COMPLETE -> "Order Again"
                                OrderType.CANCELLED -> "Order again"
                            },
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}