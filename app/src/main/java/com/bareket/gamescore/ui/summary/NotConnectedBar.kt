package com.bareket.gamescore.ui.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.tooling.preview.Preview
import com.bareket.gamescore.R

@Composable
fun NotConnectedBar() {
    Text(text = stringResource(id = R.string.not_connected_bar),
        modifier = Modifier.fillMaxWidth().background(color = Color.Red),
        textAlign = TextAlign.Center,
        )
}

@Preview
@Composable
fun previewNotConnectedBar(){
    NotConnectedBar()
}