package com.example.colab_service.websocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(documentWebSocketHandler(), "/ws/documents")
            .setAllowedOrigins("*")
    }

    @Bean
    fun documentWebSocketHandler(): WebSocketHandler {
        return DocumentWebSocketHandler()
    }
}