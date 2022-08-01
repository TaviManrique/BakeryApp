package com.manriquetavi.bakeryapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonPayments() {

    val radioOptions = listOf("Cash", "Plin or Yape", "Debit or Credit Card (not available)")
    var selectedItem by remember {
        mutableStateOf(radioOptions[0])
    }

    Column(modifier = Modifier.selectableGroup()) {
        radioOptions.forEach { label ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (selectedItem == label),
                        onClick = { selectedItem = label },
                        role = Role.RadioButton,
                        // disable the debit card
                        enabled = (label != radioOptions[2])
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.padding(end = 16.dp),
                    selected = (selectedItem == label),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colors.primary,
                        unselectedColor = Color.DarkGray,
                        disabledColor = Color.LightGray
                    ),
                    // disable the debit card
                    enabled = (label != radioOptions[2]),
                    onClick = null
                )
                Text(
                    text = label,
                    color = if (label != radioOptions[2]) Color.DarkGray else Color.LightGray
                )
            }
        }
        //CashOptionContent()
    }
}

@Composable
fun CashOptionContent() {
    val focusManager = LocalFocusManager.current
    val text = remember {
        mutableStateOf("")
    }
    CashInputField(
        modifier = Modifier.padding(top = 48.dp),
        text = text,
        focusManager = focusManager
    )
}