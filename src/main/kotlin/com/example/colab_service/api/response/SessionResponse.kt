package com.example.colab_service.api.response

import java.time.LocalDateTime
import java.util.*

data class SessionResponse(
    val sessionId: UUID,
    val documentId: UUID,
    val userId: String,
    val startTime: LocalDateTime
)