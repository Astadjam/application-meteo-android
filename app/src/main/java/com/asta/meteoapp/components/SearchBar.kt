package com.asta.meteoapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

// Research bar component
@Composable
fun searchBar(
    modifier: Modifier=Modifier,
    inputState: MutableState<String>,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
){
    Row(
        modifier= modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = inputState.value,
            onValueChange = onValueChange
        )
        IconButton(onClick = onClick) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
        }
    }
}

@Composable
@Preview
fun searchBarPreview(){
    var input = remember { mutableStateOf("Hello") }
    searchBar(
        inputState = input,
        onValueChange = {
            input.value = it

        },
        onClick = {

        }

    )
}