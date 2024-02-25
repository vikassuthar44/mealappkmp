package screens.profile

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import widget.CustomTextView

data class UserDetails(
    val name: String,
    val email: String,
    val phoneNumber: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenUI() {
    val userDetails = UserDetails(
        name = "Vikas Suthar",
        email = "vikas@gmail.com",
        phoneNumber = "+91-9876543210"
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomTextView(
                        text = "Profile",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.W800,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding(), bottom = 60.dp)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .clip(shape = RoundedCornerShape(size = 20.dp))
                        .clickable { }
                        .background(color = MaterialTheme.colorScheme.tertiary)
                        .padding(all = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier.size(80.dp).clip(shape = CircleShape)
                                .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CustomTextView(
                                text = userDetails.name.take(1),
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.W800,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Column(verticalArrangement = Arrangement.SpaceEvenly) {
                            CustomTextView(
                                text = userDetails.name,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.W700,
                                    color = Color.Gray
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CustomTextView(
                                text = userDetails.phoneNumber,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.W500,
                                    color = Color.Gray
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
                    Box(
                        modifier = Modifier.fillMaxWidth().clip(
                            shape = RoundedCornerShape(size = 20.dp)
                        ).background(color = MaterialTheme.colorScheme.tertiary)
                            .padding(top = 10.dp)
                    ) {
                        Column(modifier = Modifier) {
                            SingleView(
                                icon = Icons.Default.Favorite,
                                title = "Your Orders",
                                isLast = false,
                                image = "order_list.xml"
                            )
                            SingleView(
                                icon = Icons.Default.Favorite,
                                title = "Favourite Orders",
                                isLast = false
                            )
                            SingleView(
                                icon = Icons.Default.LocationOn,
                                title = "Address Book",
                                isLast = true,
                                image = "address.xml"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().clip(
                            shape = RoundedCornerShape(size = 20.dp)
                        ).background(color = MaterialTheme.colorScheme.tertiary)
                            .padding(top = 10.dp)
                    ) {
                        Column(modifier = Modifier) {
                            SingleView(
                                icon = Icons.Default.Favorite,
                                title = "About Us",
                                isLast = false,
                                image = "info.xml"
                            )
                            SingleView(
                                icon = Icons.Default.Settings,
                                title = "Settings",
                                isLast = false,
                                image = null
                            )
                            SingleView(
                                icon = Icons.Default.Favorite,
                                title = "Support and Help",
                                isLast = false,
                                image = "help.xml"
                            )
                            SingleView(
                                icon = Icons.Default.Settings,
                                title = "Send Feedback",
                                isLast = false,
                                image = "feedback.xml"
                            )
                            SingleView(
                                icon = Icons.Default.ExitToApp,
                                title = "Account Log Out",
                                isLast = true,
                                image = "logout.xml"
                            )
                        }
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SingleView(
    icon: ImageVector,
    title: String,
    isLast: Boolean,
    image: String? = null
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .clickable { }.padding(
                horizontal = 20.dp, vertical = 10.dp
            )
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.clip(shape = CircleShape)
                            .background(color = Color.LightGray.copy(alpha = 0.3f))
                            .padding(all = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (image == null) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Image(
                                painter = painterResource(image),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    CustomTextView(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W600
                        )
                    )
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (!isLast) {
                Divider(
                    color = DividerDefaults.color.copy(alpha = 0.2f)
                )
            }
        }
    }
}