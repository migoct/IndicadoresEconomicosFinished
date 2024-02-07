package com.dissolucion.indicadoreseconomicos.doamin.usecase

import com.dissolucion.indicadoreseconomicos.doamin.repository.IndicadoresRepository
import javax.inject.Inject

class GetIndicadoresAnioUseCase @Inject constructor(
    private val repository: IndicadoresRepository
){
    suspend operator fun invoke(tipoIndicador: String, anio: String) =
        repository.getAnio(tipoIndicador, anio)
}