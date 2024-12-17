package com.example.colab_service.service.impl

import com.example.colab_service.api.response.SessionResponse
import com.example.colab_service.domain.model.CollaborationSession
import com.example.colab_service.domain.repository.CollaborationSessionRepository
import com.example.colab_service.service.CollaborationService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CollaborationServiceImpl(
    private val sessionRepository: CollaborationSessionRepository
) : CollaborationService {

    override fun startSession(documentId: UUID, userId: String): SessionResponse {
        val session = CollaborationSession(
            documentId = documentId,
            userId = userId
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