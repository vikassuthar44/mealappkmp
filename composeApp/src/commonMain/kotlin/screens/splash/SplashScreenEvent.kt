package screens.splash

sealed interface SplashScreenEvent {
    data object MoveToHomeScreen: SplashScreenEvent
}