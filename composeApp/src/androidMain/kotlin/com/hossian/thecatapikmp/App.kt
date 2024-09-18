package com.hossian.thecatapikmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hossian.thecatapikmp.domain.model.Cat
import com.hossian.thecatapikmp.presentation.cat_detail.CatDetailScreen
import com.hossian.thecatapikmp.presentation.cat_list.CatListScreen
import com.hossian.thecatapikmp.presentation.cat_list.CatListViewModel
import kotlinx.serialization.json.Json
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "cat_list"
        ) {
            composable(
                route = "cat_list"
            ) {
                val vm: CatListViewModel = viewModel()
                CatListScreen(navController, vm)
            }
            composable(
                route = "cat_detail/{cat}",
                arguments = listOf(navArgument("cat") { type = NavType.StringType })
            ) { backStackEntry ->
                // Deserialize the Cat object from the JSON string
                val catJson = backStackEntry.arguments?.getString("cat")
                val decodedCatJson = URLDecoder.decode(catJson, StandardCharsets.UTF_8.toString())
                val cat = Json.decodeFromString<Cat>(decodedCatJson)
                CatDetailScreen(navController, cat)
            }
        }
    }
}