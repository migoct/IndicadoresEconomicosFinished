package com.dissolucion.indicadoreseconomicos.data.mapper

import com.dissolucion.indicadoreseconomicos.data.dto.todos.DatosDto
import com.dissolucion.indicadoreseconomicos.data.dto.todos.IndicadoresDto
import com.dissolucion.indicadoreseconomicos.data.dto.consultas.DiaDto
import com.dissolucion.indicadoreseconomicos.data.dto.consultas.ConsultasDto
import com.dissolucion.indicadoreseconomicos.doamin.model.todos.Datos
import com.dissolucion.indicadoreseconomicos.doamin.model.todos.Indicadores
import com.dissolucion.indicadoreseconomicos.doamin.model.consultas.Dia
import com.dissolucion.indicadoreseconomicos.doamin.model.consultas.Consultas

fun IndicadoresDto.toDomainIndicadores(): Indicadores {
    return Indicadores(
        fecha = fecha,
        uf = uf.toDomainDatos(),
        ivp = ivp.toDomainDatos(),
        dolar = dolar.toDomainDatos(),
        dolarIntercambio = dolarIntercambio.toDomainDatos(),
        euro = euro.toDomainDatos(),
        ipc = ipc.toDomainDatos(),
        utm = utm.toDomainDatos(),
        imacec = imacec.toDomainDatos(),
        tpm = tpm.toDomainDatos(),
        libraCobre = libraCobre.toDomainDatos(),
        tasaDesempleo = tasaDesempleo.toDomainDatos(),
        bitcoin = bitcoin.toDomainDatos(),
    )
}

fun DatosDto.toDomainDatos(): Datos {
    return Datos(
        codigo = codigo,
        nombre = nombre,
        unidadMedida = unidadMedida,
        fecha = fecha,
        valor = valor,
    )
}

fun DiaDto.toDomainDia(): Dia {
    return Dia(
        fecha = fecha,
        valor = valor,
    )
}

fun ConsultasDto.toDomainConsultas(): Consultas {
    return Consultas(
        codigo = codigo,
        nombre = nombre,
        unidadMedida = unidadMedida,
        serie = serie.map { it.toDomainDia() }
    )
}
