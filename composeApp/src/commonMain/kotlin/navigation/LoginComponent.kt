package navigation

import com.arkivanov.decompose.ComponentContext
import screens.login.LoginScreenEvent

class LoginComponent(
    componentContext: ComponentContext,
    private val onNext: () -> Unit,
    private val onSignUp : () -> Unit
) : ComponentContext by componentContext {

    fun onEvent(event: LoginScreenEvent) {
        when(event) {
            LoginScreenEvent.MoveToNextScreen -> {
                onNext()
            }

            LoginScreenEvent.MoveToSignUpScreen -> {
                onSignUp()
            }
        }
    }
}