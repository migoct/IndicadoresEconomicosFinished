package com.dissolucion.indicadoreseconomicos.presentation.state

import com.dissolucion.indicadoreseconomicos.doamin.model.todos.Indicadores

data class IndicadoresState(
    val indicadores: Indicadores? = null,
    val error: String? = "",
    val isLoading: Boolean = false
)
