package widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    labelValue: String = "",
    value: String = "",
    placeHolder: String = "Enter something here...",
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = true,
    hasError: Boolean = false,
    readOnly: Boolean = false,
    maxChar: Int? = null,
    onValueChange: (String) -> Unit,
) {

    var text by remember { mutableStateOf(value) }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        singleLine = singleLine,
        isError = hasError,
        readOnly = readOnly,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black.copy(alpha = 0.9f),
            unfocusedTextColor = Color.Black.copy(alpha = 0.9f)
        ),
        placeholder = {
            Text(text = placeHolder)
        },
        onValueChange = {
            if (maxChar != null) {
                if (it.length <= maxChar) {
                    text = it
                    onValueChange(it)
                }
            } else {
                text = it
                onValueChange(it)
            }
        },
        label = {
            CustomTextView(text = labelValue)
        },
        leadingIcon = {
            leadingIcon()
        },
        trailingIcon = {
            trailingIcon()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CustomPasswordTextField(
    modifier: Modifier = Modifier,
    labelValue: String = "",
    value: String = "",
    placeHolder: String = "Enter something here...",
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = true,
    hasError: Boolean = false,
    readOnly: Boolean = false,
    maxChar: Int? = null,
    onValueChange: (String) -> Unit,
) {

    var text by remember { mutableStateOf(value) }
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        singleLine = singleLine,
        isError = hasError,
        readOnly = readOnly,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black.copy(alpha = 0.9f),
            unfocusedTextColor = Color.Black.copy(alpha = 0.9f)
        ),
        placeholder = {
            Text(text = placeHolder)
        },
        onValueChange = {
            if (maxChar != null) {
                if (it.length <= maxChar) {
                    text = it
                    onValueChange(it)
                }
            } else {
                text = it
                onValueChange(it)
            }
        },
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        label = {
            CustomTextView(text = labelValue)
        },
        leadingIcon = {
            leadingIcon()
            //leadingIcon?.let { Icon(imageVector = it, contentDescription = "leading Icon") }
        },
        trailingIcon = {
            trailingIcon?.let {
                Image(
                    modifier = Modifier.clickable {
                        showPassword.value = !showPassword.value
                    },
                    painter = if (showPassword.value) painterResource("visible.xml") else painterResource(
                        "invisible.xml"
                    ),
                    contentDescription = "trailing Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )

}

@Composable
fun CustomWithoutBorderTextField(
    initialValue: String,
    placeHolder: String = "Enter something here...",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = true,
    hasError: Boolean = false,
    readOnly: Boolean = false,
    maxChar: Int? = null,
    onValueChange: (String) -> Unit,
) {

    var text by remember { mutableStateOf(initialValue) }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        singleLine = singleLine,
        isError = hasError,
        readOnly = readOnly,
        placeholder = {
            Text(text = placeHolder)
        },
        onValueChange = {
            if (maxChar != null) {
                if (it.length <= maxChar) {
                    text = it
                    onValueChange(it)
                }
            } else {
                text = it
                onValueChange(it)
            }
        },
        label = {
            Text("")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )

}
