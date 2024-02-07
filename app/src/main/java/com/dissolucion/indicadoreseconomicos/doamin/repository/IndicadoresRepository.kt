package com.dissolucion.indicadoreseconomicos.doamin.repository

import com.dissolucion.indicadoreseconomicos.core.common.Resource
import com.dissolucion.indicadoreseconomicos.doamin.model.todos.Indicadores
import com.dissolucion.indicadoreseconomicos.doamin.model.consultas.Consultas

interface IndicadoresRepository {
    suspend fun getIndicadores(): Resource<Indicadores>
    suspend fun getUltimoMes(tipoIndicador: String): Resource<Consultas>
    suspend fun getAnio(tipoIndicador: String, anio: String): Resource<Consultas>
    suspend fun getFecha(tipoIndicador: String, fecha: String): Resource<Consultas>
}
