package navigation

import com.arkivanov.decompose.ComponentContext
import screens.splash.SplashScreenEvent

class SplashScreenComponent(
    componentContext: ComponentContext,
    private val onNavigationToHome: () ->Unit
) : ComponentContext by componentContext {

    fun onEvent(event: SplashScreenEvent) {
        when(event) {
            SplashScreenEvent.MoveToHomeScreen -> {
                onNavigationToHome()
            }
        }
    }
}