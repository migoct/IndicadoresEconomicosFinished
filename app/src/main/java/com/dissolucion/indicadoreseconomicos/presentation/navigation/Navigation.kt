package com.dissolucion.indicadoreseconomicos.presentation.navigation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dissolucion.indicadoreseconomicos.presentation.components.consultaanio_screen.ConsultaAnioScreen
import com.dissolucion.indicadoreseconomicos.presentation.components.consultafecha_screen.ConsultaFechaScreen
import com.dissolucion.indicadoreseconomicos.presentation.components.indicadores_screen.IndicadoresScreen
import com.dissolucion.indicadoreseconomicos.presentation.components.ultimomes_screen.UltimoMesScreen
import com.dissolucion.indicadoreseconomicos.presentation.viewmodel.IndicadoresViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.IndicadoresScreen.route) {
        composable(Screen.IndicadoresScreen.route) {
            val indicadoresViewModel = hiltViewModel<IndicadoresViewModel>()
            val indicadoresState = indicadoresViewModel.indicadoresState.value

            IndicadoresScreen(
                indicadoresState = indicadoresState,
                modifier = Modifier,
                onIndicadorClick = { codigo ->
                    navController.navigate(
                        route = "${Screen.UltimoMesScreen.route}/$codigo"
                    )
                },
                onFechaClick = { codigo ->
                    navController.navigate(
                        route = "${Screen.ConsultaFechaScreen.route}/$codigo"
                    )
                },
                onAnioClick = { codigo ->
                    navController.navigate(
                        route = "${Screen.ConsultaAnioScreen.route}/$codigo"
                    )
                }
            )
        }

        composable(
            route = "${Screen.UltimoMesScreen.route}/{codigo}",
            arguments = listOf(navArgument("codigo") { type = NavType.StringType }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutLinearInEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutLinearInEasing
                    )
                )
            }
        ) {navBackStackEntry ->
            navBackStackEntry.arguments?.getString("codigo").let { codigo ->
                UltimoMesScreen(
                    navController = navController,
                    codigo = codigo!!
                )
            }
        }

        composable(
            route = "${Screen.ConsultaAnioScreen.route}/{codigo}",
            arguments = listOf(navArgument("codigo") { type = NavType.StringType }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutLinearInEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutLinearInEasing
                    )
                )
            }
        ) {navBackStackEntry ->
            navBackStackEntry.arguments?.getString("codigo").let { codigo ->
                ConsultaAnioScreen(
                    navController = navController,
                    codigo = codigo!!
                )
            }
        }

        composable(
            route = "${Screen.ConsultaFechaScreen.route}/{codigo}",
            arguments = listOf(navArgument("codigo") { type = NavType.StringType }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutLinearInEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutLinearInEasing
                    )
                )
            }
        ) {navBackStackEntry ->
            navBackStackEntry.arguments?.getString("codigo").let { codigo ->
                ConsultaFechaScreen(
                    navController = navController,
                    codigo = codigo!!
                )
            }
        }
    }
}
