package com.example.newsapp.ui.commonUi

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.newsapp.utils.constants.TestingSemantics

@Composable
fun ShowLoading(msg: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                strokeWidth = 1.dp, modifier = Modifier.semantics{
                    contentDescription= TestingSemantics.LOADER
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(msg)
        }

    }
}
@Composable
fun ShowError(message: String?) {
    Log.e("error","show error called")
    var visible by remember { mutableStateOf(true) }

    if (visible) {
        AlertDialog(
            onDismissRequest = { visible = false },
            title = { Text(text = "Error", style = MaterialTheme.typography.titleMedium) },
            text = { Text(text = message ?: "Internal server error", Modifier.semantics{
                contentDescription= TestingSemantics.ERROR_MESSAGE
            }) },
            confirmButton = {
                TextButton(onClick = { visible = false }, ) {
                    Text("OK")
                }
            }
        )
    }
}
