package com.dissolucion.indicadoreseconomicos.doamin.usecase

import com.dissolucion.indicadoreseconomicos.doamin.repository.IndicadoresRepository
import javax.inject.Inject

class GetIndicadoresFechaUseCase @Inject constructor(
    private val repository: IndicadoresRepository
){
    suspend operator fun invoke(tipoIndicador: String, fecha: String) =
        repository.getFecha(tipoIndicador, fecha)
}