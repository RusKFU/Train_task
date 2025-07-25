package com.example.train_task.presentation.Screens.searchscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.train_task.domain.entities.Bin
import com.example.train_task.presentation.Screens.searchscreen.InputMask
import com.example.train_task.presentation.Screens.searchscreen.SearchScreenViewModel

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    viewModel: SearchScreenViewModel
) {
    val focusManager = LocalFocusManager.current
    val maxNum = 8
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color.Transparent,
        elevation = AppBarDefaults.TopAppBarElevation,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.DarkGray
            ),
            value = text,
            onValueChange = {
                onTextChange(it.take(maxNum))
                if (it.length > maxNum) {
                    focusManager.moveFocus(FocusDirection.Down)
                    try {
                        viewModel.getBinfo(text.toInt())
                    } catch (e: NumberFormatException) {
                    }
                }
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    color = Color.DarkGray,
                    text = "Введите BIN карты",
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.DarkGray
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                            viewModel.getBinfo(bin = null)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = Color.DarkGray
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (text.isNotEmpty()) {
                        try {
                            viewModel.getBinfo(text.toInt())
                            viewModel.insertBin(bin = Bin(number = text.toInt()))
                        } catch (e: NumberFormatException) {
                        }
                    }
                }
            ),
            visualTransformation = InputMask(),
        )
    }
}