package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manriquetavi.bakeryapp.ui.theme.Purple500
import com.manriquetavi.bakeryapp.ui.theme.descriptionColor

@Composable
fun SearchCakeInputField(
    text: MutableState<String>,
    //onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
    focusManager: FocusManager?,
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        value = text.value,
        onValueChange = {
            /* After need to change onTextChange(it)*/
            text.value = it
        },
        placeholder = {
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = "Search your favorite cake here ..."
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colors.descriptionColor
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colors.descriptionColor
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (text.value.isNotEmpty()){
                        text.value = ""
                    } else {
                        onCloseClicked()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = MaterialTheme.colors.descriptionColor
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked(text.value)
                focusManager?.clearFocus()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Purple500
        )
    )
}

@Composable
fun UsernameInputField(
    modifier: Modifier,
    text: MutableState<String>,
    focusManager: FocusManager?,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        modifier = modifier.padding(vertical = 16.dp).fillMaxWidth(),
        value = text.value,
        onValueChange = { text.value = it},
        placeholder = {
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = "Username"
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colors.descriptionColor
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person Icon",
                tint = MaterialTheme.colors.descriptionColor
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Purple500
        )
    )
}

@Composable
fun EmailInputField(
    modifier: Modifier,
    text: MutableState<String>,
    focusManager: FocusManager?,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        modifier = modifier.padding(vertical = 16.dp).fillMaxWidth(),
        value = text.value,
        onValueChange = { text.value = it},
        placeholder = {
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = "Email"
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colors.descriptionColor
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                tint = MaterialTheme.colors.descriptionColor
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Purple500
        )
    )
}

@Composable
fun PhoneInputField(
    modifier: Modifier,
    text: MutableState<String>,
    focusManager: FocusManager?,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        modifier = modifier.padding(vertical = 16.dp).fillMaxWidth(),
        value = text.value,
        onValueChange = { text.value = it},
        placeholder = {
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = "Phone number"
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colors.descriptionColor
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Person Icon",
                tint = MaterialTheme.colors.descriptionColor
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Purple500
        )
    )
}

@Composable
fun PasswordInputField(
    modifier: Modifier,
    text: MutableState<String>,
    focusManager: FocusManager?,
    isVisiblePassword: MutableState<Boolean>,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        modifier = modifier.padding(vertical = 16.dp).fillMaxWidth(),
        value = text.value,
        onValueChange = { text.value = it},
        placeholder = {
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = "Password"
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colors.descriptionColor
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock Icon",
                tint = MaterialTheme.colors.descriptionColor
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { isVisiblePassword.value = !isVisiblePassword.value }
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Visibility Icon",
                    tint = MaterialTheme.colors.descriptionColor
                )
            }
        },
        visualTransformation = if (isVisiblePassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Purple500
        )
    )
}



@Preview(showBackground = true)
@Composable
fun SearchCakeInputFieldPreview() {
    SearchCakeInputField(
        text = rememberSaveable { mutableStateOf("") },
        //onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {},
        focusManager = null
    )
}