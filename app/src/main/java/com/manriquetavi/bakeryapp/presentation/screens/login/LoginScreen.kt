package com.manriquetavi.bakeryapp.presentation.screens.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manriquetavi.bakeryapp.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.navigation.Screen
import com.manriquetavi.bakeryapp.presentation.components.InputField
import com.manriquetavi.bakeryapp.presentation.components.ProgressBar
import com.manriquetavi.bakeryapp.util.ToastMessage
import com.manriquetavi.bakeryapp.util.Util

@Composable
fun LoginScreen(
    screenNavController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {


    val response = loginViewModel.signInState.value

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val authCredential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            loginViewModel.signInWithCredential(authCredential)
        } catch (e: Exception) {
            Log.e("TAG", "Google sign in failed", e)
        }
    }

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
                loginViewModel = loginViewModel,
                launcher = launcher
            )
        }
    }
    when (response) {
        is Response.Loading -> ProgressBar()
        is Response.Success ->
            if(response.data) {
                LaunchedEffect(response.data) {
                    screenNavController.popBackStack()
                    screenNavController.navigate(Screen.Main.route)
                }
            } else {
                ToastMessage(duration = Toast.LENGTH_SHORT, message = "Sign In Failed")
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
    loginViewModel: LoginViewModel,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    val focusManager = LocalFocusManager.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isVisiblePassword = rememberSaveable { mutableStateOf(false) }
    
    TitleText()
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
        )
    )
    InputField(
        modifier = Modifier.padding(top = 16.dp),
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
                focusManager.clearFocus()
            }
        ),
        isPasswordField = true,
        isVisiblePassword = isVisiblePassword,
    )
    ButtonLogin(
        email = email.value.trim(),
        password = password.value.trim(),
        loginViewModel = loginViewModel
    )
    ButtonGmail(
        launcher = launcher
    )
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
fun ButtonGmail(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = {

            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(token)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(context,gso)
            launcher.launch(googleSignInClient.signInIntent)

        },
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
