package com.dissolucion.indicadoreseconomicos.presentation.components.consultaanio_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dissolucion.indicadoreseconomicos.core.utils.MySpacer
import com.dissolucion.indicadoreseconomicos.presentation.components.common.Company
import com.dissolucion.indicadoreseconomicos.presentation.components.common.UltimoMesList
import com.dissolucion.indicadoreseconomicos.presentation.viewmodel.ConsultasViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaAnioScreen(
    navController: NavHostController,
    codigo: String,
    modifier: Modifier = Modifier,
    viewModel: ConsultasViewModel = hiltViewModel()
) {
    // añp actual
    var anio by remember { mutableIntStateOf(
        // get year from date
        Date().year + 1900
    ) }
    LaunchedEffect(
        key1 = anio,
        block = {
            viewModel.getIndicadoresAnio(codigo = codigo, anio = anio.toString())
        }
    )
    val consultaAnioState = viewModel.consultasState.value
    val data = consultaAnioState.consultas

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    data?.let {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = it.nombre,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIos,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            Company()
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (consultaAnioState.isLoading) {
                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else if (consultaAnioState.error?.isNotEmpty() == true) {
                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = consultaAnioState.error.toString(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            if (consultaAnioState.consultas != null) {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { anio-- }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    MySpacer(size = 16.dp)
                    Text(
                        text = "Año: $anio",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    MySpacer(size = 16.dp)
                    Button(onClick = { anio++ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = null
                        )
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                UltimoMesList(
                    unidadMedida = data!!.unidadMedida,
                    dias = data.serie,
                )
            }
        }
    }
}
