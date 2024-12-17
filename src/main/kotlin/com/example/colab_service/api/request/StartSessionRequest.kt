package com.example.colab_service.api.request

import java.util.*

data class StartSessionRequest(
    val documentId: UUID,
    val userId: String
)
