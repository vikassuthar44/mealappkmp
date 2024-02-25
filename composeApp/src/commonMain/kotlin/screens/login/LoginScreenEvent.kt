package screens.login


sealed interface LoginScreenEvent {
    data object MoveToNextScreen: LoginScreenEvent

    data object MoveToSignUpScreen: LoginScreenEvent
}