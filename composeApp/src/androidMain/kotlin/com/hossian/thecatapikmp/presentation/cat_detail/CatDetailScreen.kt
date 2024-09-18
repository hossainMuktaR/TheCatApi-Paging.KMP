package com.hossian.thecatapikmp.presentation.cat_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hossian.thecatapikmp.domain.model.Cat

@Composable
fun CatDetailScreen(
    navController: NavController,
    cat: Cat,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(cat.breeds.firstOrNull()?.name ?: "Cat Detail") },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = null, // You can add a back button here if needed
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Image
            Image(
                painter = rememberAsyncImagePainter(cat.url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f), // Ensures the image maintains a proper aspect ratio
                contentScale = ContentScale.FillWidth
            )

            // Cat details
            cat.breeds.firstOrNull()?.let { catBreed ->
                Column(modifier = Modifier.padding(16.dp)) {
                    // Name
                    DetailRow("Name :", catBreed.name)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Origin
                    DetailRow("Origin :", catBreed.origin)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Temperament
                    DetailRow("Temperament :", catBreed.temperament)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Description
                    Text("Description", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(catBreed.description, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row {
        Text(label, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        Text(value, style = MaterialTheme.typography.body1)
    }
}