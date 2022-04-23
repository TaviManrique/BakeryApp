package com.manriquetavi.bakeryapp.presentation.screens.register

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.presentation.components.EmailInputField
import com.manriquetavi.bakeryapp.presentation.components.PasswordInputField
import com.manriquetavi.bakeryapp.presentation.components.PhoneInputField
import com.manriquetavi.bakeryapp.presentation.components.UsernameInputField

@Composable
fun RegisterScreen(
    screenNavController: NavHostController
) {
    Scaffold(
        topBar = {  }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            RegisterContent(screenNavController)
        }
    }
}

@Composable
fun RegisterContent(screenNavController: NavHostController) {

    val focusManager = LocalFocusManager.current

    //User New Account
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }
    val isVisiblePassword = rememberSaveable { mutableStateOf(false) }
    val isVisibleRepeatPassword = rememberSaveable { mutableStateOf(false) }

    //Validate states
    val validateUsername = rememberSaveable { mutableStateOf(true) }
    val validateEmail = rememberSaveable { mutableStateOf(true) }
    val validatePhoneNumber = rememberSaveable { mutableStateOf(true) }
    val validatePassword = rememberSaveable { mutableStateOf(true) }
    val validateRepeatPassword = rememberSaveable { mutableStateOf(true) }

    TitleText()
    UsernameInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = username,
        isError = !validateUsername.value,
        focusManager = focusManager,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        errorMessage = "Please, input a valid username"
    )
    EmailInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = email,
        isError = !validateEmail.value,
        focusManager = focusManager,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        errorMessage = "Please, input a valid email"
    )
    PhoneInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = phoneNumber,
        isError = !validatePhoneNumber.value,
        focusManager = focusManager,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        errorMessage = "Please, input a valid phone number with 9 digits"
    )
    PasswordInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = password,
        isError = !validatePassword.value,
        focusManager = focusManager,
        isVisiblePassword = isVisiblePassword,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        errorMessage = "Must mix capital and non-capital letters, a number, special character and a minimum length of 8"
    )
    PasswordInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = repeatPassword,
        isError = !validateRepeatPassword.value,
        focusManager = focusManager,
        placeholder = "Repeat Password",
        isVisiblePassword = isVisibleRepeatPassword,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        ),
        errorMessage = "Passwords must be equal"
    )
    ButtonRegister(
        username = username.value,
        email = email.value,
        phoneNumber = phoneNumber.value,
        password = password.value,
        repeatPassword = repeatPassword.value,
        validateUsername = validateUsername,
        validateEmail = validateEmail,
        validatePhoneNumber = validatePhoneNumber,
        validatePassword = validatePassword,
        validateRepeatPassword = validateRepeatPassword
    )
    TextButtonLogin(screenNavController)
}

@Composable
fun TextButtonLogin(screenNavController: NavHostController) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        Text(
            text = "Haven an account?, "
        )
        Text(
            modifier = Modifier.clickable {
                screenNavController.popBackStack()
                                          },
            text = "Login",
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ButtonRegister(
    username: String,
    email: String,
    phoneNumber: String,
    password: String,
    repeatPassword: String,
    validateUsername: MutableState<Boolean>,
    validateEmail: MutableState<Boolean>,
    validatePhoneNumber: MutableState<Boolean>,
    validatePassword: MutableState<Boolean>,
    validateRepeatPassword: MutableState<Boolean>
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = {
                  validateData(
                      username = username,
                      email = email,
                      phoneNumber = phoneNumber,
                      password = password,
                      repeatPassword = repeatPassword,
                      validateUsername = validateUsername,
                      validateEmail = validateEmail,
                      validatePhoneNumber = validatePhoneNumber,
                      validatePassword = validatePassword,
                      validateRepeatPassword = validateRepeatPassword
                  )
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Register"
        )
    }
}

@Composable
fun TitleText() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = "Create Account",
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        text = "Sign up to get started",
        style = MaterialTheme.typography.h5,
        color = Color.LightGray
    )
}

fun validateData(
    username: String,
    email: String,
    phoneNumber: String,
    password: String,
    repeatPassword: String,
    validateUsername: MutableState<Boolean>,
    validateEmail: MutableState<Boolean>,
    validatePhoneNumber: MutableState<Boolean>,
    validatePassword: MutableState<Boolean>,
    validateRepeatPassword: MutableState<Boolean>
): Boolean {
    val passwordRegex = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!\-_?&])(?=\S+$).{8,}""".toRegex()
    validateUsername.value = username.trim().isNotBlank()
    validateEmail.value = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    validatePhoneNumber.value = Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.trim().length == 9 && phoneNumber.trim().first() == '9'
    validatePassword.value = passwordRegex.matches(password)
    validateRepeatPassword.value = password == repeatPassword
    return validateUsername.value && validateEmail.value && validatePhoneNumber.value && validatePassword.value && validateRepeatPassword.value
}

@Preview(showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}
