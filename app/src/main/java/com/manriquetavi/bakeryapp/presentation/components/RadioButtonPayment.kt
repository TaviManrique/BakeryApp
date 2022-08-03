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
fun RadioButtonPayments(
    radioOptions: List<String>,
    selectedItem: MutableState<String>
) {
    Column(modifier = Modifier.selectableGroup()) {
        radioOptions.forEach { label ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (selectedItem.value == label),
                        onClick = { selectedItem.value = label },
                        role = Role.RadioButton,
                        // disable the debit card
                        enabled = (label != radioOptions[2])
                    )
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.padding(end = 16.dp),
                    selected = (selectedItem.value == label),
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
    }
}
