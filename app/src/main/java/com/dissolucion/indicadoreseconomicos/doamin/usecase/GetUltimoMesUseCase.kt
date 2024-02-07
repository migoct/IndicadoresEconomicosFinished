package com.dissolucion.indicadoreseconomicos.doamin.usecase

import com.dissolucion.indicadoreseconomicos.doamin.repository.IndicadoresRepository
import javax.inject.Inject

class GetUltimoMesUseCase @Inject constructor(
    private val repository: IndicadoresRepository
) {

    suspend operator fun invoke(tipoIndicador: String) = repository.getUltimoMes(tipoIndicador)
}