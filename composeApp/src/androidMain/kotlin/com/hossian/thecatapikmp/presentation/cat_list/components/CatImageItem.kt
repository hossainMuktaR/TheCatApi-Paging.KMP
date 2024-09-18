package com.hossian.thecatapikmp.presentation.cat_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hossian.thecatapikmp.domain.model.Cat


@Composable
fun CatImageItem(
    cat: Cat,
    onCatClick: (Cat) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(8.dp)
            .clickable { onCatClick(cat) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Circular Image
        AsyncImage(
            model = cat.url,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Inside,
            placeholder = rememberVectorPainter(Icons.Default.Refresh)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            cat.breeds.firstOrNull()?.let { catBreed ->
                Text(
                    text = catBreed.name,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = catBreed.origin, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}
