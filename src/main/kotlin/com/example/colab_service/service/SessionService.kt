package com.example.colab_service.service

import com.example.colab_service.api.request.StartSessionRequest
import com.example.colab_service.api.response.SessionResponse
import java.util.*

interface SessionService {

    fun startSession(request: StartSessionRequest): SessionResponse

    fun getActiveSessions(documentId: UUID): List<SessionResponse>

    fun endSession(sessionId: UUID)
}