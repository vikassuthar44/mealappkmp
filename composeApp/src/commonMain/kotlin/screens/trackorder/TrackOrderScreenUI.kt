package screens.trackorder

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import navigation.TrackOrderComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import widget.CustomTextView
import widget.ProfilePictureUI
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackOrderScreenUI(trackOrderComponent: TrackOrderComponent) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomTextView(
                        text = "Order Status",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.W800,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            trackOrderComponent.onEvent(TrackOrderEvent.MoveToBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
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
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(all = 16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(size = 10.dp))
                        .background(
                            color = Color.LightGray.copy(
                                alpha = 0.3f
                            )
                        )
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                trackOrderComponent.onEvent(TrackOrderEvent.NewYear)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            CustomTextView(
                                text = "Order Id : ",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.Black,
                                    fontWeight = FontWeight.W700
                                )
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            CustomTextView(
                                text = "1234567",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.W700
                                )
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(1.dp)
                                    .background(color = Color.Gray)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(onClick = {

                            }) {
                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                    )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .verticalScroll(state = rememberScrollState()),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomTextView(
                        text = "Order Timeline",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.W700
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OrderReceived()
                    VerticalDotted(
                        modifier = Modifier.padding(start = 10.dp),
                        heightDp = 30
                    )
                    CookingUI()
                    VerticalDotted(
                        modifier = Modifier.padding(start = 10.dp),
                        heightDp = 30
                    )
                    DeliveryUI()
                    VerticalDotted(
                        modifier = Modifier.padding(start = 10.dp),
                        heightDp = 30
                    )
                    KnockKnockUI()

                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
                        modifier = Modifier
                            .padding(all = 0.dp)
                            .clip(shape = RoundedCornerShape(size = 20.dp))
                            .background(color = Color.White)
                            .padding(all = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.LocationOn, contentDescription = null)
                                Spacer(modifier = Modifier.width(20.dp))
                                Column {
                                    CustomTextView(
                                        text = "254, Suthar ro ka vaas, Vishala",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    CustomTextView(
                                        text = "Delivery Address",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color.Gray
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.LocationOn, contentDescription = null)
                                Spacer(modifier = Modifier.width(20.dp))
                                Column {
                                    CustomTextView(
                                        text = "30-40 Min",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    CustomTextView(
                                        text = "Estimate Delivery Time",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color.Gray
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(color = Color.LightGray.copy(alpha = 0.5f))
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    ProfilePictureUI()
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Column {
                                        CustomTextView(
                                            text = "Vikas suthar",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                color = Color.Black
                                            )
                                        )
                                        CustomTextView(
                                            text = "Food Courier",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = Color.Gray
                                            )
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(size = 10.dp))
                                        .background(color = MaterialTheme.colorScheme.primary)
                                        .clickable {

                                        }
                                        .padding(all = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        Icons.Default.Call,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OrderReceived() {
    SingleTimelineView(
        title = "Order Received",
        subTitle = "9:10 AM, 20 Dec, 2023",
        time = "9:10 AM",
        image = "received.xml"
    )
}

@Composable
fun CookingUI() {
    SingleTimelineView(
        title = "Cooking",
        subTitle = "Your order is getting ready",
        time = "9:20 AM",
        image = "cooking.xml"
    )
}

@Composable
fun DeliveryUI() {
    SingleTimelineView(
        title = "Delivery",
        subTitle = "Your order is already on it's way",
        time = "9:30 AM",
        image = "delivery.xml"
    )
}

@Composable
fun KnockKnockUI() {
    SingleTimelineView(
        title = "Knock Knock",
        subTitle = "Your order is at your door step",
        time = "9:40 AM",
        image = "door.xml"
    )
}

@Composable
fun VerticalDotted(
    modifier: Modifier,
    heightDp: Int,
    stepCount: Int = 4
) {
    //LazyColumn {
    //item(stepCount) {
    val heightShape = heightDp / stepCount
    val countValue = remember {
        mutableStateOf(0)
    }
    val coroutine = rememberCoroutineScope()
    val loopValue = remember {
        mutableStateOf(true)
    }
    coroutine.launch {
        while (loopValue.value) {
            delay(200)
            if (countValue.value <= stepCount) {
                countValue.value++
            } else {
                loopValue.value = false
            }
            println("Testing loop value ${loopValue.value}")
        }
    }
    repeat(countValue.value) {
        Column {
            Box(
                modifier = modifier
                    .width(2.dp)
                    .height(heightShape.dp)
                    .clip(
                        shape = RoundedCornerShape(size = 1.dp)
                    )
                    .background(color = MaterialTheme.colorScheme.secondary)
            )
            Spacer(modifier = modifier.height((heightDp / 10).dp))
        }
    }

    /*   }
   }*/
    /*Box(
        modifier = Modifier
            .width(2.dp)
            .height(heightDp)
            .clip(
                shape = DottedShape(
                    step = heightDp,
                )
            ).background(color = MaterialTheme.colorScheme.primary)
    )*/
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SingleTimelineView(
    title: String,
    subTitle: String,
    time: String,
    image: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                CustomTextView(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.W700
                    )
                )
                CustomTextView(
                    text = subTitle,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color.Gray
                    )
                )
            }
        }
        CustomTextView(
            text = time,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.W700
            )
        )
    }
}

private data class DottedShape(
    val step: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height)
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = i * actualStep, y = 0f),
                    size = dotSize
                )
            )
        }
        close()
    })
}