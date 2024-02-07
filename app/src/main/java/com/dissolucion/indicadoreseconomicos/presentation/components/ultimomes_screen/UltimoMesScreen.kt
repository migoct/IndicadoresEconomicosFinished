package com.dissolucion.indicadoreseconomicos.presentation.components.ultimomes_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UltimoMesScreen(
    navController: NavHostController,
    codigo: String,
    modifier: Modifier = Modifier,
    viewModel: ConsultasViewModel = hiltViewModel()
) {
    LaunchedEffect(
        key1 = true,
        block = {
            viewModel.getUltimoMes(codigo = codigo)
        }
    )
    val ultimoMesState = viewModel.consultasState.value
    val data = ultimoMesState.consultas

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
            if (ultimoMesState.isLoading) {
                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else if (ultimoMesState.error?.isNotEmpty() == true) {
                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = ultimoMesState.error.toString(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            if (ultimoMesState.consultas != null) {
                MySpacer(size = 8.dp)
                UltimoMesList(
                    unidadMedida = data!!.unidadMedida,
                    dias = data.serie,
                )
            }
        }
    }
}
