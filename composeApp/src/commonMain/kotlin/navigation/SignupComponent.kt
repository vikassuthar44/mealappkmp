package navigation

import com.arkivanov.decompose.ComponentContext
import screens.signup.SignupScreenEvent

class SignupComponent(
    componentContext: ComponentContext,
    private val onNext : () -> Unit,
    private val onLogin : () -> Unit
): ComponentContext by componentContext {

    fun onEvent(event: SignupScreenEvent) {
        when(event) {
            SignupScreenEvent.MoveToLoginScreen -> {
                onLogin()
            }
            SignupScreenEvent.MoveToNextScreen -> {
                onNext()
            }
        }
    }
}