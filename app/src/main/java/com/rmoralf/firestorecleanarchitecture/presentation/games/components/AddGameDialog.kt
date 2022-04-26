package com.rmoralf.firestorecleanarchitecture.presentation.games.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmoralf.firestorecleanarchitecture.R
import com.rmoralf.firestorecleanarchitecture.presentation.games.GamesViewModel

@Composable
fun AddGameDialog(
    viewModel: GamesViewModel = hiltViewModel()
) {

    var title by remember { mutableStateOf("") }
    var platform by remember { mutableStateOf("") }
    var finished by remember { mutableStateOf(false) }

    if (viewModel.openDialogState.value) {
        AlertDialog(
            onDismissRequest = {
                viewModel.openDialogState.value = false
            },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.dialog_title)
                            )
                        }
                    )
                    TextField(
                        value = platform,
                        onValueChange = { platform = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.dialog_platform)
                            )
                        }
                    )

                    Row(
                        modifier = Modifier.height(48.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = finished,
                            onCheckedChange = { finished = it },
                        )
                        Spacer(Modifier.width(32.dp))
                        Text(
                            text = stringResource(id = R.string.dialog_finished)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (title.isNotEmpty()) {
                            viewModel.openDialogState.value = false
                            viewModel.addGame(
                                title = title,
                                platform = platform,
                                finished = finished
                            )
                        }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_add)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.openDialogState.value = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_cancel)
                    )
                }
            }
        )
    }

}
