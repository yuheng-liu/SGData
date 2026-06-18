package com.liuyuheng.sgdata.core.presentation.components.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.core.presentation.components.BasePreviewComposable
import com.liuyuheng.sgdata.core.presentation.theme.Dimensions

@Composable
fun Searchbar(
    query: TextFieldValue = TextFieldValue(),
    hintText: String = "Search",
    onQueryChanged: (TextFieldValue) -> Unit = {},
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(hintText) },
        singleLine = true,
        shape = RoundedCornerShape(Dimensions.cornerRadiusMedium),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (query.text.isNotEmpty()) {
                IconButton(onClick = { onQueryChanged(TextFieldValue()) }) {
                    Icon(imageVector = Icons.Default.Close, null)
                }
            }
        }
    )
}

@Preview
@Composable
private fun SearchbarPreview() {
    BasePreviewComposable {
        Searchbar()
    }
}