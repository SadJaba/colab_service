package com.example.colab_service.support.websocket

import com.example.colab_service.service.CollaborationService
import org.json.JSONObject
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class DocumentWebSocketHandler(
    private val collaborationService: CollaborationService
) : TextWebSocketHandler() {

    // Маппинг сессий: documentId -> userId (пользователь, редактирующий документ)
    private val activeEditors: MutableMap<UUID, String> = ConcurrentHashMap()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("WebSocket connected: ${session.id}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = JSONObject(message.payload)
        val documentId = UUID.fromString(payload.getString("documentId"))
        val userId = payload.getString("userId")
        val action = payload.getString("action") // "EDIT" или "VIEW"

        when (action) {
            "EDIT" -> {
                if (canEditDocument(documentId, userId)) {
                    activeEditors[documentId] = userId
                    collaborationService.notifyEditStart(documentId, userId)
                    session.sendMessage(TextMessage("Editing granted for $userId"))
                } else {
                    session.sendMessage(TextMessage("Editing denied: Document locked by another user"))
                }
            }
            "END_EDIT" -> {
                activeEditors.remove(documentId)
                collaborationService.notifyEditEnd(documentId, userId)
                session.sendMessage(TextMessage("Editing released by $userId"))
            }
            else -> session.sendMessage(TextMessage("Invalid action"))
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("WebSocket disconnected: ${session.id}")
    }

    private fun canEditDocument(documentId: UUID, userId: String): Boolean {
        return activeEditors[documentId]?.let { it == userId } ?: true
    }
}
