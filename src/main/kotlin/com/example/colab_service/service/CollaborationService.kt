package com.example.colab_service.service

import com.example.colab_service.api.response.SessionResponse
import java.util.*

interface CollaborationService {

    fun notifyEditStart(documentId: UUID, userId: String)

    fun notifyEditEnd(documentId: UUID, userId: String)
}