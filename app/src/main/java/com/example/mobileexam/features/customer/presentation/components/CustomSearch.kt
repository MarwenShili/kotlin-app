package com.example.mobileexam.features.customer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileexam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearch(
    modifier: Modifier = Modifier,
    state: String,
    onTextChanged: (String) -> Unit,
    focusManager: FocusManager,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .scale(scaleY = 0.9F, scaleX = 1F)
            .border(
                width = 0.5.dp,
                color = Color(0xFF2A2B30),
                shape = RoundedCornerShape(15.dp)
            ),
        shape = RoundedCornerShape(15.dp),
        value = state,
        onValueChange = {
            onTextChanged(it)
        },
        textStyle = TextStyle(color = Color(0xFF2A2B30), fontSize = 14.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .size(18.dp),
                tint = Color(0xFF2A2B30)
            )
        },
        trailingIcon = {
            if (state != "") {
                IconButton(
                    onClick = {
                        onTextChanged("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "",
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                color = Color(0xFF2A2B30),
                style = LocalTextStyle.current.copy(textAlign = TextAlign.Start)
            )
        },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF2A2B30),
            cursorColor = Color(0xFF2A2B30),
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}