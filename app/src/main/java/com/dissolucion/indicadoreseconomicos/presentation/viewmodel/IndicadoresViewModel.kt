package com.dissolucion.indicadoreseconomicos.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dissolucion.indicadoreseconomicos.core.common.Resource
import com.dissolucion.indicadoreseconomicos.doamin.usecase.UseCases
import com.dissolucion.indicadoreseconomicos.presentation.state.IndicadoresState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndicadoresViewModel @Inject constructor(
    private val useCase: UseCases
) : ViewModel() {

    val indicadoresState = mutableStateOf(IndicadoresState())

    init {
        getIndicadores()
    }

    private fun getIndicadores() = viewModelScope.launch {
        indicadoresState.value = IndicadoresState(isLoading = true)
        when (val result = useCase.getIndicadoresUseCase()) {
            is Resource.Loading -> {
                indicadoresState.value = IndicadoresState(isLoading = true)
            }
            is Resource.Error -> {
                indicadoresState.value = IndicadoresState(error = result.message ?: "Ocurrió un error inesperado.")
            }
            is Resource.Success -> {
                result.data?.let {
                    indicadoresState.value = IndicadoresState(indicadores = it)
                }
            }
        }
    }
}
