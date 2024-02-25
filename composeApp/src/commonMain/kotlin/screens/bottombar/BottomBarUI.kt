package screens.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import navigation.BottomBarComponent
import screens.home.HomeScreenUI
import screens.message.MessageScreenUI
import screens.order.OderScreenUI
import screens.order.OrderScreenEvent
import screens.profile.ProfileScreenUI

@Composable
fun BottomBarUI(bottomBarComponent: BottomBarComponent) {
    val bottomList = listOf(
        BottomNavItem.Home,
        BottomNavItem.Order,
        BottomNavItem.Message,
        BottomNavItem.Profile,
    )
    var selectedTab by remember {
        mutableStateOf(BottomNavItem.Home.screen_route)
    }
    Scaffold(
        bottomBar = {
            BottomNavigation(bottomList) { routeName ->
                selectedTab = routeName
            }
        }
    ) {
        when (selectedTab) {
            BottomBarRouteName.HOME -> {
                HomeScreenUI()
            }

            BottomBarRouteName.ORDER -> {
                OderScreenUI(
                    onTrackOrder = {
                        bottomBarComponent.onEventOrder(OrderScreenEvent.TrackOrder)
                    },
                    orderAgain = {

                    }
                )
            }

            BottomBarRouteName.MESSAGE -> {
                MessageScreenUI()
            }

            BottomBarRouteName.PROFILE -> {
                ProfileScreenUI()
            }
        }
    }
}


@Composable
fun BottomNavigation(
    bottomList: List<BottomNavItem>,
    onClick: (String) -> Unit
) {
    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = Modifier
    ) {
        bottomList.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                icon = {
                    Image(
                        imageVector = bottomNavigationItem.icon,
                        contentDescription = "Icon",
                        colorFilter = ColorFilter.tint(
                            color = if (selectedItem == index) MaterialTheme.colorScheme.primary else {
                                Color.LightGray
                            }
                        )
                    )
                },
                label = { Text(text = bottomNavigationItem.title) },
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.LightGray,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = Color.LightGray
                ),
                onClick = {
                    selectedItem = index
                    onClick.invoke(bottomNavigationItem.screen_route)
                    /*navController.navigate(
                        bottomNavigationItem.screen_route
                    ) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(
                                screenRoute
                            ) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }*/
                })
        }
    }
}