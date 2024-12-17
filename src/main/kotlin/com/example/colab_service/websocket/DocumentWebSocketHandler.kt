package com.example.colab_service.websocket

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class DocumentWebSocketHandler : TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        //TODO Логика обработки изменений в документе
        println("Received: ${message.payload}")
        session.sendMessage(TextMessage("Change applied: ${message.payload}"))
    }
}