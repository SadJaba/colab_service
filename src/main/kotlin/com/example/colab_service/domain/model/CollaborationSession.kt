package com.example.colab_service.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "collaboration_session")
data class CollaborationSession(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val sessionId: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val documentId: UUID,

    @Column(nullable = false)
    val userId: String,

    @Column(nullable = false)
    val startTime: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = true)
    var endTime: LocalDateTime? = null
)
