package com.rmoralf.firestorecleanarchitecture.domain.model.samples

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rmoralf.firestorecleanarchitecture.domain.model.Game

class SampleGameProvider : PreviewParameterProvider<Game> {
    override val values = sequenceOf(
        Game(
            title = "Super Mario 64",
            platform = "Nintendo 64",
            finished = true
        ),
        Game(
            title = "Sekiro",
            platform = "PC",
            finished = false
        ),
        Game(
            title = "Animal Crossing: New Horizons",
            platform = "Nintendo Switch",
            finished = false
        ),
        Game(
            title = "Hellblade",
            platform = "Xbox Series X",
            finished = true
        )

    )
}