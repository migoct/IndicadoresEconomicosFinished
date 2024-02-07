package com.dissolucion.indicadoreseconomicos.presentation.components.indicadores_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dissolucion.indicadoreseconomicos.core.utils.MySpacer
import com.dissolucion.indicadoreseconomicos.core.utils.toMoneda
import com.dissolucion.indicadoreseconomicos.doamin.model.todos.Datos
import com.dissolucion.indicadoreseconomicos.doamin.model.todos.Indicadores
import com.dissolucion.indicadoreseconomicos.presentation.ui.theme.IndicadoresEconomicosTheme

@Composable
fun IndicadoresList(
    indicadores: Indicadores,
    onItemClick: (String) -> Unit,
    onFechaClick: (String) -> Unit,
    onAnioClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            CardItem(
                codigo = indicadores.uf.codigo,
                nombre = indicadores.uf.nombre,
                valor = indicadores.uf.valor.toMoneda(indicadores.uf.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.utm.codigo,
                nombre = indicadores.utm.nombre,
                valor = indicadores.utm.valor.toMoneda(indicadores.utm.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.dolar.codigo,
                nombre = indicadores.dolar.nombre,
                valor = indicadores.dolar.valor.toMoneda(indicadores.dolar.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.dolarIntercambio.codigo,
                nombre = indicadores.dolarIntercambio.nombre,
                valor = indicadores.dolarIntercambio.valor.toMoneda(indicadores.dolarIntercambio.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.euro.codigo,
                nombre = indicadores.euro.nombre,
                valor = indicadores.euro.valor.toMoneda(indicadores.euro.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.bitcoin.codigo,
                nombre = indicadores.bitcoin.nombre,
                valor = indicadores.bitcoin.valor.toMoneda(indicadores.bitcoin.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.libraCobre.codigo,
                nombre = indicadores.libraCobre.nombre,
                valor = indicadores.libraCobre.valor.toMoneda(indicadores.libraCobre.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.ipc.codigo,
                nombre = indicadores.ipc.nombre,
                valor = indicadores.ipc.valor.toMoneda(indicadores.ipc.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.ivp.codigo,
                nombre = indicadores.ivp.nombre,
                valor = indicadores.ivp.valor.toMoneda(indicadores.ivp.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.imacec.codigo,
                nombre = indicadores.imacec.nombre,
                valor = indicadores.imacec.valor.toMoneda(indicadores.imacec.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.tpm.codigo,
                nombre = indicadores.tpm.nombre,
                valor = indicadores.tpm.valor.toMoneda(indicadores.tpm.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
        item {
            CardItem(
                codigo = indicadores.tasaDesempleo.codigo,
                nombre = indicadores.tasaDesempleo.nombre,
                valor = indicadores.tasaDesempleo.valor.toMoneda(indicadores.tasaDesempleo.unidadMedida),
                fecha = indicadores.fecha,
                onItemClick = onItemClick,
                onFechaClick = onFechaClick,
                onAnioClick = onAnioClick
            )
        }
    }
}

@Composable
fun CardItem(
    codigo: String,
    nombre: String,
    valor: String,
    fecha: String,
    onItemClick: (String) -> Unit,
    onFechaClick: (String) -> Unit,
    onAnioClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clickable { onItemClick(codigo) },
        elevation = CardDefaults.cardElevation(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = nombre,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            MySpacer(size = 8.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = valor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                ElevatedAssistChip(
                    onClick = { onItemClick(codigo) },
                    label = { Text("Mes") }
                )
                MySpacer(size = 8.dp)
                ElevatedAssistChip(
                    onClick = { onFechaClick(codigo) },
                    label = { Text("Fecha") }
                )
                MySpacer(size = 8.dp)
                ElevatedAssistChip(
                    onClick = { onAnioClick(codigo) },
                    label = { Text("Año") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    IndicadoresEconomicosTheme {
        IndicadoresList(
            indicadores = Indicadores(
                fecha = "2024-02-02T03:00:00.000Z",
                uf = Datos(
                    codigo = "uf",
                    nombre = "Unidad de fomento (UF)",
                    unidadMedida = "Pesos",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 36721.16
                ),
                ivp = Datos(
                    codigo = "ivp",
                    nombre = "Indice de valor promedio (IVP)",
                    unidadMedida = "Pesos",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 38180.46
                ),
                dolar = Datos(
                    codigo = "dolar",
                    nombre = "Dólar observado",
                    unidadMedida = "Pesos",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 936.01
                ),
                dolarIntercambio = Datos(
                    codigo = "dolar_intercambio",
                    nombre = "Dólar acuerdo",
                    unidadMedida = "Pesos",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 758.87
                ),
                euro = Datos(
                    codigo = "euro",
                    nombre = "Euro",
                    unidadMedida = "Pesos",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 1017.18
                ),
                ipc = Datos(
                    codigo = "ipc",
                    nombre = "Indice de Precios al Consumidor (IPC)",
                    unidadMedida = "Porcentaje",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = -0.5
                ),
                utm = Datos(
                    codigo = "utm",
                    nombre = "Unidad Tributaria Mensual (UTM)",
                    unidadMedida = "Pesos",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 64343.0
                ),
                imacec = Datos(
                    codigo = "imacec",
                    nombre = "Imacec",
                    unidadMedida = "Porcentaje",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = -1.0
                ),
                tpm = Datos(
                    codigo = "tpm",
                    nombre = "Tasa Política Monetaria (TPM)",
                    unidadMedida = "Porcentaje",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 7.25
                ),
                libraCobre = Datos(
                    codigo = "libra_cobre",
                    nombre = "Libra de Cobre",
                    unidadMedida = "Dólar",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 3.86
                ),
                tasaDesempleo = Datos(
                    codigo = "tasa_desempleo",
                    nombre = "Tasa de desempleo",
                    unidadMedida = "Porcentaje",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 8.48
                ),
                bitcoin = Datos(
                    codigo = "bitcoin",
                    nombre = "Bitcoin",
                    unidadMedida = "Dólar",
                    fecha = "2024-02-02T03:00:00.000Z",
                    valor = 42249.69
                )
            ),
            onItemClick = {},
            onFechaClick = {},
            onAnioClick = {}
        )
    }
}
