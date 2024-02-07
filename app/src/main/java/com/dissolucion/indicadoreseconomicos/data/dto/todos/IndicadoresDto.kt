package com.dissolucion.indicadoreseconomicos.data.dto.todos

import com.google.gson.annotations.SerializedName

data class IndicadoresDto(
    val fecha: String,
    val uf: DatosDto,
    val ivp: DatosDto,
    val dolar: DatosDto,
    @SerializedName("dolar_intercambio")
    val dolarIntercambio: DatosDto,
    val euro: DatosDto,
    val ipc: DatosDto,
    val utm: DatosDto,
    val imacec: DatosDto,
    val tpm: DatosDto,
    @SerializedName("libra_cobre")
    val libraCobre: DatosDto,
    @SerializedName("tasa_desempleo")
    val tasaDesempleo: DatosDto,
    val bitcoin: DatosDto
)