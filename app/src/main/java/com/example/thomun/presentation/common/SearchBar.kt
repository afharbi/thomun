package com.example.thomun.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SearchBar(
    input: String = "",
    hint: String = "Search for episode, article, or something else..",
    onValueChange: (String) -> Unit = {},
    onClear: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(top = 14.dp, bottom = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EditedTextWithIcon(
            input = input,
            onValueChange = onValueChange,
            hint = hint,
            onClear = onClear,
            showLeadingIcon = true
        )
        Spacer(modifier = Modifier.width(20.dp))
    }

}

@Composable
fun EditedTextWithIcon(
    input: String,
    hint: String,
    onValueChange: (String) -> Unit,
    openKeyboard: Boolean = false,
    leadingIcon: ImageVector = Icons.Default.Search,
    showLeadingIcon: Boolean = false,
    onClear: () -> Unit
) {
    var inputValue by rememberSaveable(input) { mutableStateOf(input) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        modifier = Modifier
            .height(40.dp)
            .border(1.dp, Color(0xFF4C4C4C), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = Color(0xFF4C4C4C),
                shape = RoundedCornerShape(8.dp)
            )
            .focusRequester(focusRequester),
        value = inputValue,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions { keyboardController?.hide() },
        onValueChange = {
            inputValue = it
            onValueChange(inputValue.trim())
        },
        singleLine = true,
        cursorBrush = SolidColor(Color(0xFFAEB8C5)),
        textStyle = TextStyle(
            color = Color(0xFF697077),
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (showLeadingIcon)
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Color(0x0FFFFFFF),
                        modifier = Modifier
                            .padding(start = 12.dp, end = 6.dp)
                            .size(14.dp)
                    )
                Box(Modifier.weight(1f)) {
                    Text(
                        text = if (inputValue.isEmpty() && !openKeyboard) hint else "",
                        fontSize = 14.sp,
                        color = Color(0xFF9E9E9E),
                        fontWeight = FontWeight.W400
                    )
                    innerTextField()
                }
                if (inputValue != "") {
                    IconButton(onClick = {
                        inputValue = ""
                        onClear()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    )
}