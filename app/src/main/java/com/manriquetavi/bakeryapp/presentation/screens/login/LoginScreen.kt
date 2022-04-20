package com.manriquetavi.bakeryapp.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.manriquetavi.bakeryapp.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.EmailInputField
import com.manriquetavi.bakeryapp.presentation.components.PasswordInputField

@Composable
fun LoginScreen(
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
            LoginContent(screenNavController = screenNavController)
        }
    }
}

@Composable
fun LoginContent(screenNavController: NavHostController) {
    val focusManager = LocalFocusManager.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isVisiblePassword = rememberSaveable { mutableStateOf(false) }
    
    TitleText()
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
    PasswordInputField(
        modifier = Modifier,
        text = password,
        focusManager = focusManager,
        isVisiblePassword = isVisiblePassword,
        keyboardActions =  KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        )
    )
    ButtonLogin()
    ButtonGmail()
    TextButtonRegister(screenNavController = screenNavController)
}

@Composable
fun ButtonLogin() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        onClick = {  },
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
    Row(modifier = Modifier.padding(vertical = 16.dp)) {
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
