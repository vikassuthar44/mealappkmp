package screens.onboarding


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navigation.OnBoardingScreenComponent

@Composable
fun OnBoardingUI(onBoardingScreenComponent: OnBoardingScreenComponent) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        OnBoardingScreenUI(
            onSkip = {
                onBoardingScreenComponent.onEvent(OnBoardingScreenEvent.MoveToHomeScreen)
            },
            onCompleted = {
                onBoardingScreenComponent.onEvent(OnBoardingScreenEvent.MoveToHomeScreen)
            }
        )
    }
}