package com.nguyendo.lift.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nguyendo.lift.data.model.Workout
import com.nguyendo.lift.ui.theme.TextType
import com.nguyendo.lift.ui.theme.getTextStyle

@Composable
fun WorkoutCell(workout: Workout) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(12.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(12.dp)
                .height(300.dp),
    ) {
        Text(workout.title, style = getTextStyle(TextType.TITLE))
        Spacer(modifier = Modifier.size(6.dp))
        Text(workout.description, style = getTextStyle(TextType.BODY))
        Spacer(modifier = Modifier.size(12.dp))
        Text(workout.timestamp.toLocaleString())
    }
}