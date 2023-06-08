package com.example.reachyou.ui.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemBodyProfile(
    modifier: Modifier = Modifier,
    type: String,
    value: String,
    openModalBottomSheet: (String) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = type)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = modifier.padding(end = 7.dp ), text = value, style = MaterialTheme.typography.displayMedium)
            IconButton(
                modifier = modifier.size(15.dp),
                onClick = { openModalBottomSheet(type) }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit $type")
            }
        }
    }
}