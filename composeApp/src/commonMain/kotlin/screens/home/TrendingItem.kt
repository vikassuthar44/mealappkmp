package screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
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

data class TrendingItem(
    val title: String,
    val tag: List<String>,
    val isFreeDelivery: Boolean,
    val deliveryTime: String,
    val rating: String,
    val image: String,
    var isAdded: Boolean
)

data object TrendingItemList {
    fun getItemList(): List<TrendingItem> = arrayListOf<TrendingItem>().apply {
        /*repeat(
            10
        ) {*/
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        add(
            TrendingItem(
                title = "Warren Foods",
                tag = listOf("Veg", "Meals", "Burgers"),
                isFreeDelivery = true,
                deliveryTime = "10-15 min",
                rating = "4.2",
                image = "pizza_trans.png",
                isAdded = false
            )
        )
        //}

    }
}

fun <T> LazyListScope.gridItems12(
    data: List<T>,
    columnCount: Int,
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    items(rows, key = { it.hashCode() }) { rowIndex ->
        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = modifier
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(1F, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent(data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1F, fill = true))
                }
            }
        }
    }
}

fun <TrendingItem> LazyListScope.gridItems(
    data: List<TrendingItem>,
    columnCount: Int,
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(TrendingItem) -> Unit
) {
    val size = data.size
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    println("size $size and row: $rows")
    items(rows, key = { it.hashCode() }) { rowIndex ->
        println("row index $rowIndex")
        Row(
            modifier = modifier.padding(vertical = 8.dp),
            horizontalArrangement = horizontalArrangement,
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                println("item index $itemIndex")
                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(1F, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent(data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1F, fill = true))
                }
            }
        }
    }
}

@Composable
fun TrendingItemHeader() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextView(
                text = "Trending Meals",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            )
            CustomTextView(
                modifier = Modifier.clickable { },
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SingleTrendingView(
    item: TrendingItem,
    onAddToCart : () -> Unit
) {
    val count by remember {
        mutableStateOf(1)
    }
    println("SingleTrendingView")
    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(500)
        visible = true
    }
    Box(
        modifier = Modifier.clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = MaterialTheme.colorScheme.tertiary)
            .clickable {

            }.padding(bottom = 20.dp),

        ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically { fullHeight -> fullHeight }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(item.image),
                    contentDescription = null
                )
                CustomTextView(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W800, color = Color.Black, fontSize = 20.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource("delivery.xml"),
                        contentDescription = null
                    )
                    CustomTextView(
                        text = "Free Delivery",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 15.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Icon(
                        imageVector = Icons.Default.Close, contentDescription = null
                    )
                    CustomTextView(
                        text = item.deliveryTime,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp,
                            color = Color.Gray
                        )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(item.tag) { tag ->
                        Box(
                            modifier = Modifier.clip(shape = RoundedCornerShape(size = 5.dp))
                                .background(color = Color.LightGray)
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CustomTextView(
                                text = tag,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 15.sp,
                                    color = Color.DarkGray
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (item.isAdded) {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(size = 5.dp))
                            .clickable {
                                item.isAdded = true
                            }
                            .background(color = MaterialTheme.colorScheme.primary)
                            .padding(vertical = 5.dp, horizontal = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null, tint = Color.White)
                            CustomTextView(
                                text = count.toString(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.White
                                )
                            )
                            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(size = 5.dp))
                            .clickable {
                                onAddToCart.invoke()
                            }
                            .background(color = MaterialTheme.colorScheme.primary)
                            .padding(vertical = 5.dp, horizontal = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomTextView(
                            text = "Add to Cart",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}