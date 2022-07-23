package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.vector.ImageVector
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
    onTextChange: (String) -> Unit,
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
            //text.value = it
            onTextChange(it)
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
fun InputField(
    modifier: Modifier,
    text: MutableState<String>,
    placeholder: String = "",
    leadingIconImageVector: ImageVector,
    leadingIconDescription: String,
    isPasswordField: Boolean = false,
    isVisiblePassword: MutableState<Boolean> = mutableStateOf(false),
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    isError: Boolean = false,
    errorMessage: String = "",
    maxLength: Int? = null
) {
    Column {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = text.value,
            onValueChange = {
                maxLength?.let { maxLength ->
                    if (it.length <= maxLength) text.value = it
                } ?: run {
                    text.value = it
                }
                            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    text = placeholder
                )
            },
            isError = isError,
            textStyle = TextStyle(
                color = MaterialTheme.colors.descriptionColor
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = leadingIconImageVector,
                    contentDescription = leadingIconDescription,
                    tint = MaterialTheme.colors.descriptionColor
                )
            },
            trailingIcon = {
                if (isError && !isPasswordField) Icon(imageVector = Icons.Filled.Error, contentDescription = "Icon Error")
                if (isPasswordField) {
                    IconButton(
                        onClick = { isVisiblePassword.value = !isVisiblePassword.value }
                    ) {
                        Icon(
                            imageVector = if (isVisiblePassword.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Visibility Icon",
                            tint = MaterialTheme.colors.descriptionColor
                        )
                    }
                }
            },
            visualTransformation = if (isPasswordField && !isVisiblePassword.value) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Purple500
            )
        )
        if (isError) {
            Text(
                modifier = Modifier.padding(top = 1.dp),
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchCakeInputFieldPreview() {
    SearchCakeInputField(
        text = rememberSaveable { mutableStateOf("") },
        onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {},
        focusManager = null,
    )
}