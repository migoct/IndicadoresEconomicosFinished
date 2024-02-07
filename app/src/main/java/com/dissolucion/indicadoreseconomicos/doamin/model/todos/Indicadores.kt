package com.dissolucion.indicadoreseconomicos.doamin.model.todos

data class Indicadores(
    val fecha: String,
    val uf: Datos,
    val ivp: Datos,
    val dolar: Datos,
    val dolarIntercambio: Datos,
    val euro: Datos,
    val ipc: Datos,
    val utm: Datos,
    val imacec: Datos,
    val tpm: Datos,
    val libraCobre: Datos,
    val tasaDesempleo: Datos,
    val bitcoin: Datos
)
