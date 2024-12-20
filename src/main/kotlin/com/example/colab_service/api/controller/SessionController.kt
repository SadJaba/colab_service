package com.example.colab_service.api.controller

import com.example.colab_service.api.request.StartSessionRequest
import com.example.colab_service.api.response.SessionResponse
import com.example.colab_service.service.SessionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/collaboration")
class SessionController(
    private val sessionService: SessionService
) {

    @PostMapping("/sessions/start")
    fun startSession(@RequestBody request: StartSessionRequest): ResponseEntity<SessionResponse> {
        val session = sessionService.startSession(request)
        return ResponseEntity.ok(session)
    }

    @GetMapping("/sessions/{documentId}")
    fun getSessions(@PathVariable documentId: UUID): ResponseEntity<List<SessionResponse>> {
        val sessions = sessionService.getActiveSessions(documentId)
        return ResponseEntity.ok(sessions)
    }

    @PostMapping("/sessions/{sessionId}/end")
    fun endSession(@PathVariable sessionId: UUID): ResponseEntity<Void> {
        sessionService.endSession(sessionId)
        return ResponseEntity.noContent().build()
    }
}
