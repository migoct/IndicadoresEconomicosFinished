package com.dissolucion.indicadoreseconomicos.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dissolucion.indicadoreseconomicos.core.common.Resource
import com.dissolucion.indicadoreseconomicos.doamin.usecase.UseCases
import com.dissolucion.indicadoreseconomicos.presentation.state.ConsultasState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultasViewModel @Inject constructor(
    private val useCase: UseCases
): ViewModel() {
    val consultasState = mutableStateOf(ConsultasState())

    suspend fun getUltimoMes(codigo: String) {
        consultasState.value = ConsultasState(isLoading = true)
        when (val result = useCase.getUltimoMesUseCase(codigo)) {
            is Resource.Error -> {
                consultasState.value = ConsultasState(error = result.message ?: "Ocurrió un error inesperado.")
            }
            is Resource.Loading -> {
                consultasState.value = ConsultasState(isLoading = true)
            }
            is Resource.Success -> {
                result.data?.let {
                    consultasState.value = ConsultasState(consultas = it)
                }
            }
        }
    }

    suspend fun getIndicadoresAnio(codigo: String, anio: String) {
        consultasState.value = ConsultasState(isLoading = true)
        when (val result = useCase.getIndicadoresAnioUseCase(codigo, anio)) {
            is Resource.Error -> {
                consultasState.value = ConsultasState(error = result.message ?: "Ocurrió un error inesperado.")
            }
            is Resource.Loading -> {
                consultasState.value = ConsultasState(isLoading = true)
            }
            is Resource.Success -> {
                result.data?.let {
                    consultasState.value = ConsultasState(consultas = it)
                }
            }
        }
    }

    suspend fun getIndicadoresFecha(codigo: String, fecha: String) {
        consultasState.value = ConsultasState(isLoading = true)
        when (val result = useCase.getIndicadoresFechaUseCase(codigo, fecha)) {
            is Resource.Error -> {
                consultasState.value = ConsultasState(error = result.message ?: "Ocurrió un error inesperado.")
            }
            is Resource.Loading -> {
                consultasState.value = ConsultasState(isLoading = true)
            }
            is Resource.Success -> {
                result.data?.let {
                    consultasState.value = ConsultasState(consultas = it)
                }
            }
        }
    }
}