package com.rmoralf.firestorecleanarchitecture.presentation.games.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rmoralf.firestorecleanarchitecture.R
import com.rmoralf.firestorecleanarchitecture.presentation.ui.theme.HeaderBg

@Preview
@Composable
fun GamesListHeader() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBg)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.header_title),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(id = R.string.header_platform),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(id = R.string.header_finished),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(id = R.string.header_delete),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}