package com.example.colab_service.support.config

import com.example.colab_service.support.websocket.DocumentWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val documentWebSocketHandler: DocumentWebSocketHandler // Внедряем бин
) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(documentWebSocketHandler, "/ws/documents")
            .setAllowedOrigins("*")
    }
}