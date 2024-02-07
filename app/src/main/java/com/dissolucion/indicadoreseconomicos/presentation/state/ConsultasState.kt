package com.dissolucion.indicadoreseconomicos.presentation.state

import com.dissolucion.indicadoreseconomicos.doamin.model.consultas.Consultas

data class ConsultasState(
    val consultas: Consultas? = null,
    val error: String? = "",
    val isLoading: Boolean = false

)
