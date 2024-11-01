package com.nguyendo.lift.ui.components

import androidx.annotation.ColorLong
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nguyendo.lift.data.model.Workout
import com.nguyendo.lift.ui.theme.TextType
import com.nguyendo.lift.ui.theme.getTextStyle

@Composable
fun WorkoutCell(workout: Workout) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(color = Color.LightGray)
    ) {
        Text(workout.title, style = getTextStyle(TextType.TITLE))
        Text(workout.description, style = getTextStyle(TextType.BODY))
        Text(workout.timestamp.toLocaleString())
    }
}