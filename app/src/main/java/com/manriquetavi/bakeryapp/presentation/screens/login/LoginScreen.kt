package com.manriquetavi.bakeryapp.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manriquetavi.bakeryapp.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.EmailInputField
import com.manriquetavi.bakeryapp.presentation.components.PasswordInputField
import com.manriquetavi.bakeryapp.presentation.components.ProgressBar
import com.manriquetavi.bakeryapp.util.ToastMessage
import com.manriquetavi.bakeryapp.util.Util

@Composable
fun LoginScreen(
    screenNavController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val response = loginViewModel.signInState.value
    Scaffold(
        topBar = {  }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LoginContent(
                screenNavController = screenNavController,
                loginViewModel = loginViewModel
            )
        }
    }

    when (response) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> if(response.data) {
            LaunchedEffect(response.data) {
                screenNavController.popBackStack()
                screenNavController.navigate(Screen.Main.route)
            }
        }
        is Response.Error -> {
            Util.printError(response.message)
            ToastMessage(duration = Toast.LENGTH_SHORT, message = "Email or Password incorrect")
        }
    }
}

@Composable
fun LoginContent(
    screenNavController: NavHostController,
    loginViewModel: LoginViewModel
) {
    val focusManager = LocalFocusManager.current
    val email = loginViewModel.email
    val password = loginViewModel.password
    val isVisiblePassword = rememberSaveable { mutableStateOf(false) }
    
    TitleText()
    EmailInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = email,
        isError = false,
        focusManager = focusManager,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
    PasswordInputField(
        modifier = Modifier.padding(top = 16.dp),
        text = password,
        isError = false,
        focusManager = focusManager,
        isVisiblePassword = isVisiblePassword,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        )
    )
    ButtonLogin(
        email = email.value.trim(),
        password = password.value.trim(),
        loginViewModel = loginViewModel
    )
    ButtonGmail()
    TextButtonRegister(screenNavController = screenNavController)
}

@Composable
fun ButtonLogin(
    email:String,
    password: String,
    loginViewModel: LoginViewModel
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = {
                  loginViewModel.signInWithEmailAndPassword(email, password)
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Login"
        )
    }
}

@Composable
fun ButtonGmail() {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = {  },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_gmail),
                contentDescription = "Icon Gmail"
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = "  Connect to Gmail"
            )
        }
    }
}

@Composable
fun TitleText() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = "Welcome",
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        text = "Sign in to continue",
        style = MaterialTheme.typography.h5,
        color = Color.LightGray
    )
}

@Composable
fun TextButtonRegister(
    screenNavController: NavHostController
) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        Text(
            text = "Haven't an account?, "
        )
        Text(
            modifier = Modifier.clickable {
                screenNavController.navigate(Screen.Register.route)
                                          },
            text = "Register",
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}
