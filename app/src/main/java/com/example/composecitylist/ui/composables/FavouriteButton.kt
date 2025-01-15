package com.example.composecitylist.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavouriteButton(modifier: Modifier = Modifier, onClick: (enabled: Boolean) -> Unit = {}) {
    val filterEnabled = remember { mutableStateOf(false) }
    Button(
        onClick = {
            filterEnabled.value = !filterEnabled.value
            onClick(filterEnabled.value)
        },
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .width(100.dp)
            .height(55.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = if (filterEnabled.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favourite Icon"
            )
            Text("Favourites", fontSize = 10.sp)
        }
    }
}