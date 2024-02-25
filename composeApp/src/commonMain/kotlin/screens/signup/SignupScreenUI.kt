package screens.signup

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.SignupComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import screens.login.SignInOption
import widget.CustomAnnotatedStringTextView
import widget.CustomButton
import widget.CustomPasswordTextField
import widget.CustomTextField
import widget.CustomTextView

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignUPScreenUI(signupComponent: SignupComponent) {
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
                    .padding(horizontal = 64.dp)
                    .aspectRatio(1f),
                painter = painterResource("signup.xml"),
                contentDescription = null
            )
            CustomTextView(
                modifier = Modifier,
                text = "Sign Up",
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
            CustomTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .background(color = Color.White),
                onValueChange = {

                },
                singleLine = true,
                maxChar = 35,
                imeAction = ImeAction.Next,
                labelValue = "Full name",
                placeHolder = "Enter full name here...",
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                }
            )
            CustomTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .background(color = Color.White),
                onValueChange = {

                },
                singleLine = true,
                maxChar = 35,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                labelValue = "Mobile Number",
                placeHolder = "Enter mobile number here...",
                leadingIcon = {
                    Icon(Icons.Default.Phone, contentDescription = null)
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
                    Icon(Icons.Default.Email, contentDescription = null)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomAnnotatedStringTextView(
                title = "By creating an account, you are agreeing to our",
                highLightText = " Privacy Policy ",
                highLightText1 = " Terms and Conditions.",
                highLightTextColor = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                buttonTitle = "Sing Up",
                buttonTextColor = Color.White,
                backgroundColor = MaterialTheme.colorScheme.primary,
                modifier1 = Modifier
                    .fillMaxWidth()
            ) {
                signupComponent.onEvent(SignupScreenEvent.MoveToNextScreen)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextView(text = "Or Sign Up with")
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
                CustomTextView(text = "Already an Account?")
                CustomTextView(
                    modifier = Modifier.clickable {
                        signupComponent.onEvent(SignupScreenEvent.MoveToLoginScreen)
                    },
                    text = "Log in", style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}