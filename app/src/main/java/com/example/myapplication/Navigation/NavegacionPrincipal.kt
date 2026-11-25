package com.example.myapplication.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.CarritoScreen
import com.example.myapplication.screens.CatalogoScreen
import com.example.myapplication.screens.InicioScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.screens.PerfilScreen

data class Pestana(val ruta: String, val titulo: String, val icono: ImageVector)

val pestanas = listOf(
    Pestana("inicio", "Inicio", Icons.Filled.Home),
    Pestana("catalogo", "Catálogo", Icons.AutoMirrored.Filled.List),
    Pestana("carrito", "Carrito", Icons.Filled.ShoppingCart),
    Pestana("perfil", "Perfil", Icons.Filled.Person)
)

@Composable
fun NavegacionPrincipal() {
    val navController = rememberNavController()
    val rutaActual = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                pestanas.forEach { pestana ->
                    NavigationBarItem(
                        selected = rutaActual == pestana.ruta,
                        onClick = {
                            navController.navigate(pestana.ruta) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(pestana.icono, contentDescription = pestana.titulo) },
                        label = { Text(pestana.titulo) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") { InicioScreen() }
            composable("catalogo") { CatalogoScreen() }
            composable("carrito") { CarritoScreen() }
            composable("perfil") { LoginScreen() }
        }
    }
}