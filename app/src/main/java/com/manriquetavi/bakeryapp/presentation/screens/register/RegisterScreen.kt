package com.manriquetavi.bakeryapp.presentation.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.navigation.Screen
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
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatPassword = remember { mutableStateOf("") }
    val isVisiblePassword = rememberSaveable { mutableStateOf(false) }
    val isVisibleRepeatPassword = rememberSaveable { mutableStateOf(false) }

    TitleText()
    UsernameInputField(
        modifier = Modifier,
        text = username,
        focusManager = focusManager,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
    EmailInputField(
        modifier = Modifier,
        text = email,
        focusManager = focusManager,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
    PhoneInputField(
        modifier = Modifier,
        text = phone,
        focusManager = focusManager,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
    PasswordInputField(
        modifier = Modifier,
        text = password,
        focusManager = focusManager,
        isVisiblePassword = isVisiblePassword,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
    PasswordInputField(
        modifier = Modifier,
        text = repeatPassword,
        focusManager = focusManager,
        isVisiblePassword = isVisibleRepeatPassword,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        )
    )
    ButtonRegister()
    TextButtonLogin(screenNavController)
}

@Composable
fun TextButtonLogin(screenNavController: NavHostController) {
    Row(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = "Haven an account?, "
        )
        Text(
            modifier = Modifier.clickable {
                screenNavController.popBackStack()
                screenNavController.navigate(Screen.Login.route)
                                          },
            text = "Login",
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ButtonRegister() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = {  },
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
