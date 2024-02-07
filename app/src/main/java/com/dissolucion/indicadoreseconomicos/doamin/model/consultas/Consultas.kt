package com.dissolucion.indicadoreseconomicos.doamin.model.consultas

data class Consultas(
    val codigo: String,
    val nombre: String,
    val unidadMedida: String,
    val serie: List<Dia>
)