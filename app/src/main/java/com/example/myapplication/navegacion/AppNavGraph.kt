package com.example.myapplication.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.data.Sesion
import com.example.myapplication.screens.CarritoScreen
import com.example.myapplication.screens.CatalogoScreen
import com.example.myapplication.screens.InicioScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.PerfilScreen
import com.example.myapplication.screens.RegistroScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.INICIO,
        modifier = modifier
    ) {
        composable(Routes.INICIO) { InicioScreen() }
        composable(Routes.CATALOGO) { CatalogoScreen() }
        composable(Routes.CARRITO) { CarritoScreen() }
        composable(Routes.PERFIL) {
            if (Sesion.usuario == null) {
                LoginScreen(
                    onIrARegistro = { navController.navigate(Routes.REGISTRO) }
                )
            } else {
                PerfilScreen()
            }
        }

        composable(Routes.REGISTRO) {
            RegistroScreen(
                onIrALogin = { navController.popBackStack() },
                onRegistrado = { navController.popBackStack() }
            )
        }
    }
}