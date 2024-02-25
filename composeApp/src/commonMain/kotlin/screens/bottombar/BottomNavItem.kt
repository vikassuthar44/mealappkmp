package screens.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

object BottomBarRouteName {
    const val HOME = "home"
    const val ORDER = "order"
    const val MESSAGE = "message"
    const val PROFILE = "profile"
}

sealed class BottomNavItem(var title:String, var icon: ImageVector, var screen_route:String){

    data object Home : BottomNavItem("Home", Icons.Default.Home,BottomBarRouteName.HOME)
    data object Order: BottomNavItem("Order",Icons.Default.Favorite,BottomBarRouteName.ORDER)
    data object Message: BottomNavItem("Message",Icons.Default.Email,BottomBarRouteName.MESSAGE)
    data object Profile: BottomNavItem("Profile",Icons.Default.Person,BottomBarRouteName.PROFILE)
}