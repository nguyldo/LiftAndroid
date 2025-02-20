package com.nguyendo.lift.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nguyendo.lift.data.model.Exercise
import com.nguyendo.lift.data.model.Measurement
import com.nguyendo.lift.data.model.Set
import com.nguyendo.lift.ui.theme.TextType
import com.nguyendo.lift.ui.theme.getTextStyle
import com.nguyendo.lift.ui.viewmodel.CreateWorkoutViewModel

@Composable
fun ExerciseCell(
    exercise: Exercise,
    index: Int,
    createWorkoutViewModel: CreateWorkoutViewModel,
    measurements: List<Measurement>
) {
    // var sets: List<Set> by remember { mutableStateOf(listOf(exercise.emptySet())) }
    val exerciseState by createWorkoutViewModel.exercisesState.collectAsState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(12.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(12.dp),
    ) {
        Text(exercise.exerciseName)

        /*
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Set")
            if (measurements.contains("reps")) { Text("Reps") }
            if (measurements.contains("weight")) { Text("Weight") }
            if (measurements.contains("distance")) { Text("Distance") }
            if (measurements.contains("time")) { Text("Time") }
            if (measurements.contains("incline")) { Text("Incline") }
            /*
            exercise.reps?.let
            exercise.weight?.let { Text("Weight") }
            exercise.distance?.let { Text("Distance") }
            exercise.time?.let { Text("Time") }
            exercise.incline?.let { Text("Incline") }*/
        }*/

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Sets")
                exerciseState[index].sets.mapIndexed { i, _ ->
                    Text((i + 1).toString())
                }
            }
            measurements.map { measurement ->
                Column {
                    Text(measurement)
                    exerciseState[index].sets.mapIndexed { i, set ->
                        when (measurement) {
                            "reps" -> {
                                TextField(
                                    value = exerciseState[index].sets[i].rep?.let { it.toString() } ?: "",
                                    onValueChange = {
                                        createWorkoutViewModel.updateSet(
                                            exerciseIndex = index,
                                            setIndex = i,
                                            rep = it.toInt()
                                        )
                                    },
                                    label = {},
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    textStyle = getTextStyle(TextType.BODY),
                                    modifier = Modifier.width(40.dp)
                                )
                            }
                            else -> {}
                        }
                    }
                }
            }
        }

        /*
        sets.map { set ->
            set.rep?.let { TextField(
                value = set.rep.toString(),
                onValueChange = { set.rep = it.toIntOrNull() },
                label = {},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            ) }
        }

        Column {
            exerciseState[index].sets.mapIndexed { i, set ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${i + 1}")
                }
            }
        }*/

        Button(onClick = {
            createWorkoutViewModel.addSet(index)
        }) {
            Text("Add set", style = getTextStyle(TextType.BODY))
        }
    }
}

fun emptySet(): Set {
    return Set()
    /*
    return Set(
        rep = this.reps?.let { 0 },
        weight = this.weight?.let { 0 },
        distance = this.distance?.let { 0.0f },
        time = this.time?.let { 0 },
        incline = this.incline?.let { 0.0f },
    )*/
}
