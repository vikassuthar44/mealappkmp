package screens.order

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabRow(
    containerColor: Color = Color.White,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    containerShape: Shape = CircleShape,
    indicatorShape: Shape = RoundedCornerShape(size = 5.dp),
    paddingValues: PaddingValues = PaddingValues(4.dp),
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 250, easing = FastOutSlowInEasing),
    fixedSize: Boolean = true,
    selectedTabPosition: Int = 0,
    modifier: Modifier,
    tabItem: @Composable () -> Unit
) {

    Box(
        modifier = modifier.fillMaxWidth().background(color = containerColor)
            .clip(shape = containerShape),
        contentAlignment = Alignment.Center,
    ) {
        SubcomposeLayout(
            Modifier
                .padding(paddingValues)
                .selectableGroup()
        ) { constraints ->
            val tabMeasurable: List<Placeable> =
                subcompose(SubComposeID.PRE_CALCULATE_ITEM, tabItem)
                    .map { it.measure(constraints) }

            val itemsCount = tabMeasurable.size
            val maxItemWidth = tabMeasurable.maxOf { it.width }
            val maxItemHeight = tabMeasurable.maxOf { it.height * 2 }

            val tabPlacables = subcompose(SubComposeID.ITEM, tabItem).map {
                val c = if (fixedSize) constraints.copy(
                    minWidth = maxItemWidth,
                    maxWidth = maxItemWidth,
                    minHeight = maxItemHeight
                ) else constraints
                it.measure(c)
            }

            val tabPositions = tabPlacables.mapIndexed { index, placeable ->
                val itemWidth = if (fixedSize) maxItemWidth else placeable.width
                val x = if (fixedSize) {
                    maxItemWidth * index
                } else {
                    val leftTabWith = tabPlacables.take(index).sumOf { it.width }
                    leftTabWith
                }
                TabPosition(x.toDp(), itemWidth.toDp())
            }

            val tabRowWidth = if (fixedSize) maxItemWidth * itemsCount
            else tabPlacables.sumOf { it.width }

            layout(tabRowWidth, maxItemHeight) {
                subcompose(SubComposeID.INDICATOR) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(
                                top = (maxItemHeight.toDp() * 3 / 4),
                                bottom = maxItemHeight.toDp() / 4 - 5.dp
                            )
                            .background(color = Color.LightGray.copy(alpha = 0.5f), indicatorShape)
                    )
                    Box(
                        Modifier
                            .tabIndicator(tabPositions[selectedTabPosition], animationSpec)
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(
                                top = (maxItemHeight.toDp() * 3 / 4),
                                bottom = maxItemHeight.toDp() / 4 - 5.dp
                            )
                            .background(color = indicatorColor, indicatorShape)
                    )

                }.forEach {
                    it.measure(Constraints.fixed(tabRowWidth, maxItemHeight)).placeRelative(0, 0)
                }

                tabPlacables.forEachIndexed { index, placeable ->
                    val x = if (fixedSize) {
                        maxItemWidth * index
                    } else {
                        val leftTabWith = tabPlacables.take(index).sumOf { it.width }
                        leftTabWith
                    }
                    placeable.placeRelative(x, 0)
                }
            }
        }
    }
}

@Composable
fun TabView(
    modifier: Modifier,
    onClick: (OrderTab) -> Unit
) {

    var selectedTabPosition by remember { mutableStateOf(0) }

    val items = listOf(
        OrderTab.ACTIVE, OrderTab.COMPLETED, OrderTab.CANCELLED
    )

    TabRow(
        modifier = modifier,
        selectedTabPosition = selectedTabPosition
    ) {
        items.forEachIndexed { index, s ->
            TabTitle(
                s.value,
                position = index,
                selectedPosition = selectedTabPosition
            ) {
                selectedTabPosition = index
                when(index) {
                    0 -> {
                     onClick.invoke(OrderTab.ACTIVE)
                    }
                    1 -> {
                        onClick.invoke(OrderTab.COMPLETED)
                    }
                    2 -> {
                        onClick.invoke(OrderTab.CANCELLED)
                    }
                }
            }
        }
    }
}

enum class SubComposeID {
    PRE_CALCULATE_ITEM,
    ITEM,
    INDICATOR
}

enum class OrderTab(val value: String) {
    ACTIVE(value = "Active"),
    COMPLETED(value = "Completed"),
    CANCELLED(value = "Cancelled")
}

data class TabPosition(
    val left: Dp,
    val width: Dp
)

@Composable
fun TabTitle(
    title: String,
    position: Int,
    selectedPosition: Int,
    onClick: (Int) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.W800, fontSize = 20.sp
        ),
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            ) {
                onClick(position)
            },
        color = if (selectedPosition == position) MaterialTheme.colorScheme.primary else Color.Gray
    )
}

fun Modifier.tabIndicator(
    tabPosition: TabPosition,
    animation: AnimationSpec<Dp>
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = tabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabPosition.width,
        animationSpec = animation
    )
    val indicatorOffset by animateDpAsState(
        targetValue = tabPosition.left,
        animationSpec = animation
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
        .fillMaxHeight()
}