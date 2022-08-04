package com.manriquetavi.bakeryapp.presentation.screens.register

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.components.*
import com.manriquetavi.bakeryapp.util.ToastMessage
import com.manriquetavi.bakeryapp.util.Util
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun RegisterScreen(
    screenNavController: NavHostController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val response = registerViewModel.signUpState.value

    Scaffold(
        topBar = {  }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            RegisterContent(screenNavController, registerViewModel)
        }
    }

    when (response) {
        is Response.Loading -> ProgressBarCircular()
        is Response.Success -> if(response.data) {
            ToastMessage(duration = Toast.LENGTH_SHORT, message = "Success add new user")
        }
        is Response.Error -> {
            Util.printError(response.message)
            ToastMessage(duration = Toast.LENGTH_SHORT, message = "Error add new user")
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun RegisterContent(
    screenNavController: NavHostController,
    registerViewModel: RegisterViewModel
) {

    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

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
    InputField(
        modifier = Modifier.padding(top = 16.dp),
        text = username,
        placeholder = "Username",
        leadingIconImageVector = Icons.Default.Person,
        leadingIconDescription = "Person Icon",
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        errorMessage = "Please, input a valid username",
        isError = !validateUsername.value,
    )
    InputField(
        modifier = Modifier.padding(top = 16.dp),
        text = email,
        placeholder = "Email",
        leadingIconImageVector = Icons.Default.Email,
        leadingIconDescription = "Email Icon",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        errorMessage = "Please, input a valid email",
        isError = !validateEmail.value,
    )
    InputField(
        modifier = Modifier.padding(top = 16.dp),
        text = phoneNumber,
        placeholder = "Phone number",
        leadingIconImageVector = Icons.Default.PhoneAndroid,
        leadingIconDescription = "Phone Icon",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        errorMessage = "Please, input a valid phone number with 9 digits",
        isError = !validatePhoneNumber.value,
        maxLength = 9
    )
    InputField(
        modifier = Modifier
            .padding(top = 16.dp)
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        text = password,
        placeholder = "Password",
        leadingIconImageVector = Icons.Default.Lock,
        leadingIconDescription = "Password Icon",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        isPasswordField = true,
        isVisiblePassword = isVisiblePassword,
        errorMessage = "Must mix capital and non-capital letters, a number, special character and a minimum length of 8",
        isError = !validatePassword.value,
    )

    InputField(
        modifier = Modifier
            .padding(top = 16.dp)
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        text = repeatPassword,
        placeholder = "Repeat Password",
        leadingIconImageVector = Icons.Default.Lock,
        leadingIconDescription = "Password Icon",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        ),
        isPasswordField = true,
        isVisiblePassword = isVisibleRepeatPassword,
        errorMessage = "Passwords must be equal",
        isError = !validateRepeatPassword.value,
    )
    ButtonRegister(
        username = username,
        email = email,
        phoneNumber = phoneNumber,
        password = password,
        repeatPassword = repeatPassword,
        validateUsername = validateUsername,
        validateEmail = validateEmail,
        validatePhoneNumber = validatePhoneNumber,
        validatePassword = validatePassword,
        validateRepeatPassword = validateRepeatPassword,
        registerViewModel = registerViewModel
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
    username: MutableState<String>,
    email: MutableState<String>,
    phoneNumber: MutableState<String>,
    password: MutableState<String>,
    repeatPassword: MutableState<String>,
    validateUsername: MutableState<Boolean>,
    validateEmail: MutableState<Boolean>,
    validatePhoneNumber: MutableState<Boolean>,
    validatePassword: MutableState<Boolean>,
    validateRepeatPassword: MutableState<Boolean>,
    registerViewModel: RegisterViewModel
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = {
            if (
                validateData(
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
            ) {
                registerViewModel.signUp(username = username.value, email = email.value, password = password.value, phoneNumber = phoneNumber.value)
                username.value = ""
                email.value = ""
                phoneNumber.value = ""
                password.value = ""
                repeatPassword.value = ""
            }
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

@ExperimentalFoundationApi
@Preview(showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController(), registerViewModel = hiltViewModel())
}
