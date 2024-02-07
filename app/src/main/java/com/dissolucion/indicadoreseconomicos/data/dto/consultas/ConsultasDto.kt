package com.dissolucion.indicadoreseconomicos.data.dto.consultas

import com.google.gson.annotations.SerializedName

data class ConsultasDto(
    val codigo: String,
    val nombre: String,
    @SerializedName("unidad_medida")
    val unidadMedida: String,
    val serie: List<DiaDto>
)