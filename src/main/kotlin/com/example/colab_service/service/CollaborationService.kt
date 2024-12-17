package com.example.colab_service.service

import com.example.colab_service.api.response.SessionResponse
import java.util.*

interface CollaborationService {

    fun startSession(documentId: UUID, userId: String): SessionResponse

    fun getActiveSessions(documentId: UUID): List<SessionResponse>

    fun endSession(sessionId: UUID)
}