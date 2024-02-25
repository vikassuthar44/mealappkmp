package screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import widget.CustomTextView


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SearchWidget() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = Color.LightGray.copy(alpha = 0.5f))
            .clickable { }
            .padding(vertical = 15.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Search, contentDescription = "search icon", tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(10.dp))
                CustomTextView(
                    text = "search food and kitchen",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.DarkGray
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .height(16.dp), color = Color.LightGray
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(painter = painterResource("filter.xml"), contentDescription = "filter icon", colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary))
            }
        }
    }
}