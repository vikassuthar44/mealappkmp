package screens.onboarding

sealed interface OnBoardingScreenEvent {
    data object MoveToHomeScreen: OnBoardingScreenEvent
}