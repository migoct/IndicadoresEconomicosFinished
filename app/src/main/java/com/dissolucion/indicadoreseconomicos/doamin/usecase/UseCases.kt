package com.dissolucion.indicadoreseconomicos.doamin.usecase

data class UseCases(
    val getIndicadoresUseCase: GetIndicadoresUseCase,
    val getUltimoMesUseCase: GetUltimoMesUseCase,
    val getIndicadoresAnioUseCase: GetIndicadoresAnioUseCase,
    val getIndicadoresFechaUseCase: GetIndicadoresFechaUseCase
)
