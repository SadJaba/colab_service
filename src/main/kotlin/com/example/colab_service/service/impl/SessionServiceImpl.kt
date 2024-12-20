package com.example.colab_service.service.impl

import com.example.colab_service.api.request.StartSessionRequest
import com.example.colab_service.api.response.SessionResponse
import com.example.colab_service.domain.model.CollaborationSession
import com.example.colab_service.domain.repository.CollaborationSessionRepository
import com.example.colab_service.service.SessionService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class SessionServiceImpl(
    private val sessionRepository: CollaborationSessionRepository
) : SessionService {

    override fun startSession(request: StartSessionRequest): SessionResponse {
        val session = CollaborationSession(
            documentId = request.documentId,
            userId = request.userId
        )
        sessionRepository.save(session)
        return SessionResponse(session.sessionId, session.documentId, session.userId, session.startTime)
    }

    override fun getActiveSessions(documentId: UUID): List<SessionResponse> {
        return sessionRepository.findByDocumentIdAndEndTimeIsNull(documentId)
            .map { session ->
                SessionResponse(session.sessionId, session.documentId, session.userId, session.startTime)
            }
    }

    override fun endSession(sessionId: UUID) {
        val session = sessionRepository.findById(sessionId)
            .orElseThrow { IllegalArgumentException("Session not found") }
        session.endTime = LocalDateTime.now()
        sessionRepository.save(session)
    }
}