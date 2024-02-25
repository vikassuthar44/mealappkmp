package screens.signup

sealed interface SignupScreenEvent {

    data object MoveToNextScreen: SignupScreenEvent

    data object MoveToLoginScreen : SignupScreenEvent
}