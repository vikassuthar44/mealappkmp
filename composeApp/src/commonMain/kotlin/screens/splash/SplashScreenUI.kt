package screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import navigation.SplashScreenComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreenUI(splashScreenComponent: SplashScreenComponent) {
    val coroutineScope = rememberCoroutineScope()
    var showImage by remember { mutableStateOf(false) }
    coroutineScope.launch {
        delay(200)
        showImage = true
    }

    coroutineScope.launch {
        delay(5000)
        splashScreenComponent.onEvent(SplashScreenEvent.MoveToHomeScreen)
    }
    Box(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            showImage,
            enter = expandIn()
        ) {
            Image(
                painterResource("compose-multiplatform.xml"),
                null
            )
        }
    }
}