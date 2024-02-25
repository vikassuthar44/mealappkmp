package screens.order

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun CompletedOrderScreen(modifier: Modifier) {
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
                SingleCompleteOrder(activeOrder = activeOrder)
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SingleCompleteOrder(activeOrder: ActiveOrder) {
    Box(
        modifier = Modifier
            .padding(all = 16.dp).clip(shape = RoundedCornerShape(size = 20.dp))
            .background(color = MaterialTheme.colorScheme.tertiary)
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier.width(100.dp).clip(shape = RoundedCornerShape(size = 20.dp))
                        .aspectRatio(1f)
                        .background(color = Color.White),
                    painter = painterResource(activeOrder.image), contentDescription = null
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = activeOrder.title, style = TextStyle(
                            fontWeight = FontWeight.W800, color = Color.Black, fontSize = 22.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${activeOrder.itemCount} items", style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 15.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier.height(15.dp).width(1.dp)
                                .background(color = Color.LightGray)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Distance ${activeOrder.distance}", style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 15.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$${activeOrder.price}", style = TextStyle(
                                fontWeight = FontWeight.W800,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 20.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier.clip(
                                shape = RoundedCornerShape(
                                    size = 10.dp
                                )
                            )
                                .background(color = MaterialTheme.colorScheme.primary)
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp),
                                text = "Completed",
                                style = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(color = Color.LightGray))
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
                            color = MaterialTheme.colorScheme.primary,
                            width = 1.dp
                        ).clickable { }
                        .padding(horizontal = 30.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Leave a Review",
                        style = TextStyle(color = MaterialTheme.colorScheme.primary)
                    )
                }
                Box(
                    modifier = Modifier.clip(
                        shape = RoundedCornerShape(size = 10.dp),
                    ).background(color = MaterialTheme.colorScheme.primary)
                        .clickable { }
                        .padding(horizontal = 30.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Order Again",
                        style = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                    )
                }
            }
        }
    }
}