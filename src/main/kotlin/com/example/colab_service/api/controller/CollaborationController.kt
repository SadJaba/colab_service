package com.example.colab_service.api.controller

import com.example.colab_service.api.request.StartSessionRequest
import com.example.colab_service.api.response.SessionResponse
import com.example.colab_service.service.CollaborationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/collaboration")
class CollaborationController(
    private val collaborationService: CollaborationService
) {

    @PostMapping("/sessions/start")
    fun startSession(@RequestBody request: StartSessionRequest): ResponseEntity<SessionResponse> {
        val session = collaborationService.startSession(request.documentId, request.userId)
        return ResponseEntity.ok(session)
    }

    @GetMapping("/sessions/{documentId}")
    fun getSessions(@PathVariable documentId: UUID): ResponseEntity<List<SessionResponse>> {
        val sessions = collaborationService.getActiveSessions(documentId)
        return ResponseEntity.ok(sessions)
    }

    @PostMapping("/sessions/{sessionId}/end")
    fun endSession(@PathVariable sessionId: UUID): ResponseEntity<Void> {
        collaborationService.endSession(sessionId)
        return ResponseEntity.noContent().build()
    }
}
