package com.example.colab_service.domain.repository

import com.example.colab_service.domain.model.CollaborationSession
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CollaborationSessionRepository : JpaRepository<CollaborationSession, UUID> {
    fun findByDocumentIdAndEndTimeIsNull(documentId: UUID): List<CollaborationSession>
}