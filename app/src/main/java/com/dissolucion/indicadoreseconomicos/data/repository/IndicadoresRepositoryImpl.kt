package com.dissolucion.indicadoreseconomicos.data.repository

import com.dissolucion.indicadoreseconomicos.core.common.Resource
import com.dissolucion.indicadoreseconomicos.data.api.IndicadoresApi
import com.dissolucion.indicadoreseconomicos.data.mapper.toDomainIndicadores
import com.dissolucion.indicadoreseconomicos.data.mapper.toDomainConsultas
import com.dissolucion.indicadoreseconomicos.doamin.model.todos.Indicadores
import com.dissolucion.indicadoreseconomicos.doamin.model.consultas.Consultas
import com.dissolucion.indicadoreseconomicos.doamin.repository.IndicadoresRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@ViewModelScoped
class IndicadoresRepositoryImpl @Inject constructor(
    private val apiService: IndicadoresApi
) : IndicadoresRepository {

    override suspend fun getIndicadores(): Resource<Indicadores> {
        return try {
            coroutineScope {
                val api = apiService.getIndicadores()
                val result = api.toDomainIndicadores()
                Resource.Success(result)
            }
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    override suspend fun getUltimoMes(tipoIndicador: String): Resource<Consultas> {
        return try {
            coroutineScope {
                val api = apiService.getTipoIndicador(tipoIndicador)
                val result = api.toDomainConsultas()
                Resource.Success(result)
            }
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    override suspend fun getAnio(tipoIndicador: String, anio: String): Resource<Consultas> {
        return try {
            coroutineScope {
                val api = apiService.getTipoIndicadorAnio(tipoIndicador, anio)
                val result = api.toDomainConsultas()
                Resource.Success(result)
            }
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    override suspend fun getFecha(tipoIndicador: String, fecha: String): Resource<Consultas> {
        return try {
            coroutineScope {
                val api = apiService.getTipoIndicadorFecha(tipoIndicador, fecha)
                val result = api.toDomainConsultas()
                Resource.Success(result)
            }
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}
