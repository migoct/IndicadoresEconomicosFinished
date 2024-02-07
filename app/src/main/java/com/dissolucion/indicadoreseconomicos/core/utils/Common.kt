package com.dissolucion.indicadoreseconomicos.core.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MySpacer(size: Dp): Unit = Spacer(modifier = Modifier.size(size))

fun String.trimStartEnd(): String = this.trimStart().trimEnd()

fun Double.toMoneda(unidadMedida: String): String {
    val monto = this
    // Formatear moneda con dos decimales y separador de miles
    val valor = NumberFormat.getInstance().format(monto)

    return when (unidadMedida) {
        "Pesos" -> {
            "$ $valor"
        }
        "Dólar" -> {
            "USD $valor"
        }
        else -> {
            "$valor%"
        }
    }
}

fun String.toDate(): String {
    val dateStr = this
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(dateStr)
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date!!)
}