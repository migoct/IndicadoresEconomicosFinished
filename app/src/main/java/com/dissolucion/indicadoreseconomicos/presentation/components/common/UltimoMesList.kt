package com.dissolucion.indicadoreseconomicos.presentation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dissolucion.indicadoreseconomicos.core.utils.toDate
import com.dissolucion.indicadoreseconomicos.core.utils.toMoneda
import com.dissolucion.indicadoreseconomicos.doamin.model.consultas.Dia
import com.dissolucion.indicadoreseconomicos.presentation.ui.theme.IndicadoresEconomicosTheme

@Composable
fun UltimoMesList(
    unidadMedida: String,
    dias: List<Dia>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(dias) {dia ->
            Card(
                modifier = Modifier.fillMaxWidth(.9f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = dia.fecha.toDate(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = dia.valor.toMoneda(unidadMedida),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    IndicadoresEconomicosTheme {
        UltimoMesList(
            unidadMedida = "Pesos",
            dias = listOf(
                Dia(
                    fecha = "2024-02-01T03:00:00.000Z",
                    valor = 123.0
                ),
                Dia(
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 123.0
                ),
                Dia(
                    fecha = "2024-02-03T03:00:00.000Z",
                    valor = 123.0
                ),
                Dia(
                    fecha = "2024-02-04T03:00:00.000Z",
                    valor = 123.0
                ),
                Dia(
                    fecha = "2024-02-05T03:00:00.000Z",
                    valor = 123.0
                ),
                Dia(
                    fecha = "2024-02-06T03:00:00.000Z",
                    valor = 123.0
                ),
                Dia(
                    fecha = "2024-02-07T03:00:00.000Z",
                    valor = 123.0
                ),
            )
        )
    }
}