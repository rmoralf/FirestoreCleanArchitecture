package com.rmoralf.firestorecleanarchitecture.domain.model

data class Game(
    var id: String? = null,
    var title: String? = null,
    var platform: String? = null,
    var finished: Boolean? = false
)