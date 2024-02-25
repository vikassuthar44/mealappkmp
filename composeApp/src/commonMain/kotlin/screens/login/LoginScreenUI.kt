package screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import navigation.LoginComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import widget.CustomButton
import widget.CustomPasswordTextField
import widget.CustomTextField
import widget.CustomTextView

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreenUI(loginComponent: LoginComponent) {
    var rememberMeCheck by remember {
        mutableStateOf(true)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 48.dp)
                    .aspectRatio(1f),
                painter = painterResource("login.xml"),
                contentDescription = null
            )
            CustomTextView(
                modifier = Modifier,
                text = "Login",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.W800
                )
            )
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                onValueChange = {

                },
                singleLine = true,
                maxChar = 35,
                imeAction = ImeAction.Next,
                labelValue = "Email Id",
                placeHolder = "Enter Email Id here...",
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null)
                }
            )
            CustomPasswordTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .background(color = Color.White),
                onValueChange = {

                },
                trailingIcon = "password",
                singleLine = true,
                maxChar = 25,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                labelValue = "Password",
                placeHolder = "Enter Password here...",
                leadingIcon = {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMeCheck,
                        onCheckedChange = {
                            rememberMeCheck = !rememberMeCheck
                        }
                    )
                    CustomTextView(
                        text = "Remember Me",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                CustomTextView(
                    modifier = Modifier.clickable {

                    },
                    text = "Forgot Password",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                buttonTitle = "Log in",
                buttonTextColor = Color.White,
                backgroundColor = MaterialTheme.colorScheme.primary,
                modifier1 = Modifier
                    .fillMaxWidth()
            ) {
                loginComponent.onEvent(LoginScreenEvent.MoveToNextScreen)
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextView(text = "Or Sign in with")
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SignInOption(
                    icon = "facebook.xml",
                    color = Color.Blue.copy(alpha = 0.7f)
                ) {

                }
                SignInOption(
                    icon = "google.xml",
                    color = Color.Red.copy(
                        alpha = 0.7f
                    )
                ) {

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                CustomTextView(text = "New to here!")
                CustomTextView(
                    modifier = Modifier.clickable {
                        loginComponent.onEvent(LoginScreenEvent.MoveToSignUpScreen)
                    },
                    text = "Register", style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignInOption(
    icon: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(color = color)
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 10.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}