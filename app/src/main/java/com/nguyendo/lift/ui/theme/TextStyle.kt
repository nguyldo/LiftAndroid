package com.nguyendo.lift.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.nguyendo.lift.ui.theme.TextType.TITLE
import com.nguyendo.lift.ui.theme.TextType.BODY

enum class TextType {
    TITLE,
    BODY
}

fun getTextStyle(textType: TextType): TextStyle {
    return when (textType) {
        TITLE -> {
            TextStyle(
                fontSize = 24.sp
            )
        }
        BODY -> {
            TextStyle(
                fontSize = 16.sp
            )
        }
    }
}