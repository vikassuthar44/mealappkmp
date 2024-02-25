package widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier1: Modifier = Modifier,
    buttonTitle: String = "Submit",
    buttonTextColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(size = 10.dp),
    isEnable: State<Boolean> = mutableStateOf(true),
    isShouldLoading: MutableState<Boolean>? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    onClick: () -> Unit,
) {

    val isLoading = remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = isShouldLoading?.value) {
        isLoading.value = isShouldLoading?.value ?: false
    }


    val selected = remember {
        mutableStateOf(false)
    }
    val scale = animateFloatAsState(targetValue = if (selected.value) 0.9f else 1f, label = "")
    Box(
        modifier = modifier1
            .clip(shape = shape)
            .scale(scale.value)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
        ) {
        val modifier = if (isEnable.value) Modifier
            .clickable {
                onClick.invoke()
                focusManager.clearFocus()
            } else Modifier
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            if (isLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(all = 5.dp),
                    color = Color.White
                )
            } else {
                CustomTextView(
                    text = buttonTitle,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = buttonTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
            }
        }

    }
}