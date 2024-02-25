package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.SplashScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        configuration: Configuration,
        context: ComponentContext
    ): Child {
        return when (configuration) {
            Configuration.SplashScreen -> Child.SplashScreen(
                splashScreenComponent = SplashScreenComponent(
                    componentContext = context,
                    onNavigationToHome = {
                        //navigation.pushNew(Configuration.BottomBar)
                        navigation.pop(
                            onComplete = {
                                navigation.pushNew(Configuration.OnBoardingScreen)
                            })
                    }
                )
            )

            Configuration.HomeScreen -> Child.HomeScreen(
                homeScreenComponent = HomeScreenComponent(componentContext = context)
            )

            Configuration.BottomBar -> Child.BottomBar(
                bottomBarComponent = BottomBarComponent(
                    componentContext = context,
                    onTrackOrder = {
                        navigation.pushNew(Configuration.TrackOrderScreen)
                    }
                )
            )

            Configuration.OnBoardingScreen -> Child.OnBoarding(
                onBoardingScreenComponent = OnBoardingScreenComponent(
                    componentContext = context,
                    onNavigationToHome = {
                        navigation.pop(onComplete = {
                            navigation.pushNew(Configuration.LoginScreen)
                        })
                    }
                )
            )

            Configuration.LoginScreen -> Child.LoginScreen(
                loginComponent = LoginComponent(
                    componentContext = context,
                    onNext = {
                        navigation.pop(onComplete = {
                            navigation.push(Configuration.BottomBar)
                        })
                    },
                    onSignUp = {
                        navigation.pop(onComplete = {
                            navigation.push(Configuration.SignupScreen)
                        })
                    }
                )
            )

            Configuration.SignupScreen -> Child.SignupScreen(
                signUpComponent = SignupComponent(
                    componentContext = context,
                    onNext = {
                        navigation.pop(onComplete = {
                            navigation.push(Configuration.BottomBar)
                        })
                    },
                    onLogin = {
                        navigation.pop(onComplete = {
                            navigation.push(Configuration.LoginScreen)
                        })
                    }
                )
            )

           Configuration.TrackOrderScreen -> Child.TrackOrderScreen(
               trackOrderComponent = TrackOrderComponent(
                   componentContext = context,
                   onBack = {
                       navigation.pop()
                   },
                   newYear = {
                       navigation.pushNew(Configuration.NewYearScreen)
                   }
               )
           )

            Configuration.NewYearScreen -> Child.NewYearScreen(
                newYearComponent = NewYearComponent(
                    componentContext = context
                )
            )
        }
    }

    //screens
    sealed class Child {
        data class SplashScreen(val splashScreenComponent: SplashScreenComponent) : Child()

        data class HomeScreen(val homeScreenComponent: HomeScreenComponent) : Child()

        data class BottomBar(val bottomBarComponent: BottomBarComponent) : Child()

        data class OnBoarding(val onBoardingScreenComponent: OnBoardingScreenComponent) : Child()

        data class LoginScreen(val loginComponent: LoginComponent) : Child()

        data class SignupScreen(val signUpComponent: SignupComponent) : Child()

        data class TrackOrderScreen(val trackOrderComponent: TrackOrderComponent) : Child()

        data class NewYearScreen(val newYearComponent: NewYearComponent) : Child()
    }


    @Serializable
    sealed class Configuration {
        @Serializable
        data object SplashScreen : Configuration()

        @Serializable
        data object HomeScreen : Configuration()

        @Serializable
        data object BottomBar : Configuration()

        @Serializable
        data object OnBoardingScreen : Configuration()

        @Serializable
        data object LoginScreen : Configuration()

        @Serializable
        data object SignupScreen : Configuration()

        @Serializable
        data object TrackOrderScreen: Configuration()

        @Serializable
        data object NewYearScreen : Configuration()
    }
}