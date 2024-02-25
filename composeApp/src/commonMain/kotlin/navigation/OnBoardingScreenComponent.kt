package navigation

import com.arkivanov.decompose.ComponentContext
import screens.onboarding.OnBoardingScreenEvent

class OnBoardingScreenComponent(
    componentContext: ComponentContext,
    private val onNavigationToHome: () ->Unit
) : ComponentContext by componentContext {

    fun onEvent(onBoardingScreenEvent: OnBoardingScreenEvent) {
        when (onBoardingScreenEvent) {
            OnBoardingScreenEvent.MoveToHomeScreen -> {
                onNavigationToHome()
            }
        }
    }
}