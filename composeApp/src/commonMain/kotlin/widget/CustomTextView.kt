package widget

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun CustomTextView(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray),
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Visible,
    maxLines:Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines
    )

}