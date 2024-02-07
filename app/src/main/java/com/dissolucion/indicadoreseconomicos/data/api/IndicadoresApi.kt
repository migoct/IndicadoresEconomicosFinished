package com.dissolucion.indicadoreseconomicos.data.api

import com.dissolucion.indicadoreseconomicos.data.dto.todos.IndicadoresDto
import com.dissolucion.indicadoreseconomicos.data.dto.consultas.ConsultasDto
import retrofit2.http.GET
import retrofit2.http.Path

interface IndicadoresApi {

    @GET("api/")
    suspend fun getIndicadores(): IndicadoresDto

    @GET("api/{tipoIndicador}")
    suspend fun getTipoIndicador(
        @Path("tipoIndicador") tipoIndicador: String
    ): ConsultasDto

    @GET("api/{tipoIndicador}/{fecha}")
    suspend fun getTipoIndicadorFecha(
        @Path("tipoIndicador") tipoIndicador: String,
        @Path("fecha") fecha: String
    ): ConsultasDto

    @GET("api/{tipoIndicador}/{anio}")
    suspend fun getTipoIndicadorAnio(
        @Path("tipoIndicador") tipoIndicador: String,
        @Path("anio") anio: String
    ): ConsultasDto
}
