import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.RootComponent
import screens.bottombar.BottomBarUI
import screens.home.HomeScreenUI
import screens.login.LoginScreenUI
import screens.newyear.NewYearScreenUI
import screens.onboarding.OnBoardingUI
import screens.signup.SignUPScreenUI
import screens.splash.SplashScreenUI
import screens.trackorder.TrackOrderScreenUI
import theme.MealAppTheme

@Composable
fun App(
    rootComponent: RootComponent,
    settingsRepository: SettingsRepository? = null
) {
    settingsRepository?.clear()
    MealAppTheme {
        val childStack by rootComponent.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.HomeScreen -> {
                    HomeScreenUI()
                }

                is RootComponent.Child.SplashScreen -> {
                    SplashScreenUI(instance.splashScreenComponent)
                }

                is RootComponent.Child.BottomBar -> {
                    BottomBarUI(
                        bottomBarComponent = instance.bottomBarComponent
                    )
                }

                is RootComponent.Child.OnBoarding -> {
                    OnBoardingUI(instance.onBoardingScreenComponent)
                }

                is RootComponent.Child.LoginScreen -> {
                    LoginScreenUI(instance.loginComponent)
                }

                is RootComponent.Child.SignupScreen -> {
                    SignUPScreenUI(instance.signUpComponent)
                }

                is RootComponent.Child.TrackOrderScreen -> {
                    TrackOrderScreenUI(
                        trackOrderComponent = instance.trackOrderComponent
                    )
                }

                is RootComponent.Child.NewYearScreen -> {
                    NewYearScreenUI()
                }
            }
        }
    }
}