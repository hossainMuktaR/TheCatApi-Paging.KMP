package com.hossian.thecatapikmp.presentation.cat_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hossian.thecatapikmp.presentation.cat_list.components.CatImageItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CatListScreen(
    navController: NavController,
    vm: CatListViewModel
) {

    LaunchedEffect(true) {
        vm.getCatImages()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cat") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(vm.state.value.cats) { cat ->
                    CatImageItem(cat = cat, onCatClick = { cat ->
                        val catJson = Json.encodeToString(cat)
                        val encodedCatJson = URLEncoder.encode(catJson, StandardCharsets.UTF_8.toString())
                        navController.navigate("cat_detail/$encodedCatJson")
                    }
                    )
                    Divider()
                }

                if (vm.state.value.isLoading) {
                    item {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(16.dp))
                            CircularProgressIndicator()
                        }
                    }

                } else {
                    item {
                        LaunchedEffect(true) {
                            vm.getCatImages()
                        }
                    }
                }
            }
        }
    }
}
