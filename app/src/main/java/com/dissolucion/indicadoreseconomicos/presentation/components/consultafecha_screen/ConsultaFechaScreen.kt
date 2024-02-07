package com.dissolucion.indicadoreseconomicos.presentation.components.consultafecha_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dissolucion.indicadoreseconomicos.core.utils.MySpacer
import com.dissolucion.indicadoreseconomicos.core.utils.OutlinedDatePicker
import com.dissolucion.indicadoreseconomicos.core.utils.toMoneda
import com.dissolucion.indicadoreseconomicos.presentation.components.common.Company
import com.dissolucion.indicadoreseconomicos.presentation.viewmodel.ConsultasViewModel
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaFechaScreen(
    navController: NavHostController,
    codigo: String,
    modifier: Modifier = Modifier,
    viewModel: ConsultasViewModel = hiltViewModel()
) {
    val hoy = Date()
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    var date by rememberSaveable {
        val localDate =
            hoy.toInstant().atZone(ZoneOffset.systemDefault()).toLocalDate().atStartOfDay()
        val iDate =
            Date.from(localDate.toInstant(ZoneOffset.systemDefault().rules.getOffset(localDate)))
        mutableStateOf(iDate)
    }
    LaunchedEffect(
        key1 = date,
        block = {
            viewModel.getIndicadoresFecha(codigo = codigo, fecha = sdf.format(date))
        }
    )
    val consultaFechaState = viewModel.consultasState.value
    val data = consultaFechaState.consultas

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
            if (consultaFechaState.isLoading) {
                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else if (consultaFechaState.error?.isNotEmpty() == true) {
                Box(
                    modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = consultaFechaState.error.toString(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            if (consultaFechaState.consultas != null) {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedDatePicker(
                        modifier = Modifier.fillMaxWidth(.7f),
                        initialDate = date,
                        shape = MaterialTheme.shapes.large,
                        textStyle = TextStyle(fontSize = 20.sp).copy(textAlign = TextAlign.Center)
                    ) { newDate ->
                        date = newDate
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    MySpacer(size = 24.dp)
                    if (data?.serie!!.isNotEmpty()) {
                        Text(
                            text = data.serie[0].valor.toMoneda(data.unidadMedida),
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}