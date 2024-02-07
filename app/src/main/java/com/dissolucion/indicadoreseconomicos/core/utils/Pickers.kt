package com.dissolucion.indicadoreseconomicos.core.utils

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dissolucion.indicadoreseconomicos.presentation.ui.theme.IndicadoresEconomicosTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedDateTimePicker(
    modifier: Modifier = Modifier,
    initialDateTime: Date = Date(),
    label: String = "Fecha y Hora",
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    format: String = "dd/MM/yyyy  -  HH:mm",
    onDateTimeChanged: (Date) -> Unit = {}
) {
    var selectedDate by remember {
        val localDate =
            LocalDateTime.ofInstant(initialDateTime.toInstant(), ZoneOffset.systemDefault())
                .toLocalDate()
        mutableStateOf<LocalDate?>(localDate)
    }
    var selectedTime by remember {
        val localTime =
            LocalDateTime.ofInstant(initialDateTime.toInstant(), ZoneOffset.systemDefault())
                .toLocalTime()
        mutableStateOf<LocalTime?>(localTime)
    }
    val state = rememberDatePickerState(
        // in UTC
        initialSelectedDateMillis = initialDateTime.time
    )
    var showTime by remember { mutableStateOf(false) }
    var showDate by remember { mutableStateOf(false) }
    var dateTime by remember { mutableStateOf<Date?>(initialDateTime) }
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    var text by remember {
        val initial = dateTime?.let { sdf.format(it) }
        mutableStateOf(initial ?: sdf.format(Date()))
    }
    val dateStr: String? = dateTime?.let { sdf.format(it) }

    val calendar = Calendar.getInstance()
    calendar.time = dateTime!!
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)
    
    val timePicker = TimePickerDialog(
        LocalContext.current,
        { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)
            dateTime = calendar.time
            selectedTime = LocalTime.of(hour, minute)
            onDateTimeChanged(dateTime!!)
            showTime = false
        },
        hourOfDay,
        minutes,
        true
    )

    OutlinedTextField(
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {
                    showDate = true
                }
            },
        value = dateStr ?: sdf.format(Date()),
        onValueChange = { text = it },
        shape = shape,
        colors = colors,
        label = { Text(label) },
        textStyle = textStyle.copy(textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold),
        readOnly = true,
        enabled = enabled,
        maxLines = 1,
        leadingIcon = {
            IconButton(
                onClick = {
                    showDate = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { showTime = true }) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = null
                )
            }
        }
    )
    if (showDate) {
        DatePickerDialog(
            onDismissRequest = { showDate = false },
            confirmButton = {
                Button(onClick = { showDate = false }) {
                    Text(text = "Acceptar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDate = false }) {
                    Text(text = "Cancelar")
                }
            }
        ) {
            DatePicker(
                state = state
            )
        }
    }
    state.selectedDateMillis?.let { dm ->
        val date = Date(dm)

        // millis to local date UTC
        selectedDate = LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC).toLocalDate()
        dateTime = LocalDateTime.of(selectedDate, selectedTime ?: LocalTime.now())
            .atZone(ZoneOffset.systemDefault())
            .toInstant()
            .let { Date.from(it) }
        onDateTimeChanged(dateTime!!)
    }
    if (showTime) {
        timePicker.show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedDatePicker(
    modifier: Modifier = Modifier,
    initialDate: Date = Date(),
    label: String = "Fecha",
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    format: String = "dd/MM/yyyy",
    onDateTimeChanged: (Date) -> Unit = {}
) {
    var selectedDate by remember {
        val localDate =
            LocalDateTime.ofInstant(initialDate.toInstant(), ZoneOffset.systemDefault())
                .toLocalDate()
        mutableStateOf<LocalDate?>(localDate)
    }
    val state = rememberDatePickerState(
        // in UTC
        initialSelectedDateMillis = initialDate.time
    )
    var showDate by remember { mutableStateOf(false) }
    var dateTime by remember { mutableStateOf<Date?>(initialDate) }
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    var text by remember {
        val initial = dateTime?.let { sdf.format(it) }
        mutableStateOf(initial ?: sdf.format(Date()))
    }
    val dateStr: String? = dateTime?.let { sdf.format(it) }

    OutlinedTextField(
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {
                    showDate = true
                }
            },
        value = dateStr ?: sdf.format(Date()),
        onValueChange = { text = it },
        shape = shape,
        colors = colors,
        label = { Text(label) },
        textStyle = textStyle.copy(fontWeight = FontWeight.SemiBold),
        readOnly = true,
        enabled = enabled,
        maxLines = 1,
        leadingIcon = {
            IconButton(
                onClick = {
                    showDate = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = null
                )
            }
        }
    )
    if (showDate) {
        DatePickerDialog(
            onDismissRequest = { showDate = false },
            confirmButton = {
                Button(onClick = { showDate = false }) {
                    Text(text = "Acceptar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDate = false }) {
                    Text(text = "Cancelar")
                }
            }
        ) {
            DatePicker(
                state = state
            )
        }
    }
    state.selectedDateMillis?.let { dm ->
        val date = Date(dm)
        // millis to local date UTC
        selectedDate = LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC).toLocalDate()
        dateTime = Date.from(selectedDate?.atStartOfDay(ZoneOffset.systemDefault())?.toInstant())
        onDateTimeChanged(dateTime!!)
    }
}

@Composable
fun OutlinedTimePicker(
    modifier: Modifier = Modifier,
    initialTime: Date = Date(),
    label: String = "Hora",
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    format: String = "HH:mm",
    onDateTimeChanged: (Date) -> Unit = {}
) {
    var selectedTime by remember {
        val localTime =
            LocalDateTime.ofInstant(initialTime.toInstant(), ZoneOffset.systemDefault())
                .toLocalTime()
        mutableStateOf<LocalTime?>(localTime)
    }
    var showTime by remember { mutableStateOf(false) }
    var dateTime by remember { mutableStateOf<Date?>(initialTime) }
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    var text by remember {
        val initial = dateTime?.let { sdf.format(it) }
        mutableStateOf(initial ?: sdf.format(Date()))
    }
    val dateStr: String? = dateTime?.let { sdf.format(it) }

    val calendar = Calendar.getInstance()
    calendar.time = dateTime!!
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)

    val timePicker = TimePickerDialog(
        LocalContext.current,
        { _, hour, minute ->
            calendar.set(Calendar.YEAR , 2023)
            calendar.set(Calendar.MONTH , 10)
            calendar.set(Calendar.DAY_OF_MONTH , 16)
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            dateTime = calendar.time
            selectedTime = LocalTime.of(hour, minute, 0, 0)
            onDateTimeChanged(dateTime!!)
            showTime = false
        },
        hourOfDay,
        minutes,
        true
    )

    OutlinedTextField(
        modifier = modifier
            .onFocusChanged {
                if (it.isFocused) {
                    showTime = true
                }
            },
        value = dateStr ?: sdf.format(Date()),
        onValueChange = { text = it },
        shape = shape,
        colors = colors,
        label = { Text(label) },
        textStyle = textStyle.copy(fontWeight = FontWeight.SemiBold),
        readOnly = true,
        enabled = enabled,
        maxLines = 1,
        leadingIcon = {
            IconButton(
                onClick = {
                    showTime = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = null
                )
            }
        }
    )
    if (showTime) {
        timePicker.show()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    IndicadoresEconomicosTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedDateTimePicker(
                shape = MaterialTheme.shapes.large,
                textStyle = TextStyle(fontSize = 24.sp)
            )
            OutlinedDatePicker(
                modifier = Modifier.fillMaxWidth(.5f),
                shape = MaterialTheme.shapes.large,
                textStyle = TextStyle(fontSize = 24.sp)
            )
            OutlinedTimePicker(
                modifier = Modifier.fillMaxWidth(.5f),
                shape = MaterialTheme.shapes.large,
                textStyle = TextStyle(fontSize = 24.sp)
            )
        }
    }
}