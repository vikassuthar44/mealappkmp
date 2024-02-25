package widget

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomAnnotatedStringTextView(
    modifier: Modifier = Modifier,
    title: String,
    highLightText: String,
    highLightText1: String,
    highLightTextColor: Color = MaterialTheme.colorScheme.primary,
    fontSize: TextUnit = 18.sp,
    textColor: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    onClick: () -> Unit = {}
) {

    Text(
        text = buildAnnotatedString {
            append(title)
            withStyle(style = SpanStyle(color = highLightTextColor)) {
                append(highLightText)
            }
            append(" and ")
            withStyle(style = SpanStyle(color = highLightTextColor)) {
                append(highLightText1)
            }
        },
        modifier = modifier.clickable {
            onClick.invoke()
        },
        style = MaterialTheme.typography.labelMedium.copy(fontSize = fontSize),
        textAlign = textAlign,
        color = textColor
    )
}