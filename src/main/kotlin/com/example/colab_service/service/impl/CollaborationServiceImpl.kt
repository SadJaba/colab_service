package com.example.colab_service.service.impl

import com.example.colab_service.service.CollaborationService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class CollaborationServiceImpl(
    private val restTemplate: RestTemplate
) : CollaborationService {

    private val documentServiceUrl = "http://document-service/api/documents"

    override fun notifyEditStart(documentId: UUID, userId: String) {
        println("User $userId started editing document $documentId")
        //TODO Дополнительная логика: запись в базу данных или уведомления
    }

    override fun notifyEditEnd(documentId: UUID, userId: String) {
        println("User $userId finished editing document $documentId")
        saveDocumentChanges(documentId, "Document updated by $userId")
    }

    private fun saveDocumentChanges(documentId: UUID, changes: String) {
        val request = mapOf("updates" to changes)
        restTemplate.put("$documentServiceUrl/$documentId", request)
        println("Changes saved for document $documentId")
    }

    fun getDocumentContent(documentId: UUID): String {
        val response = restTemplate.getForEntity("$documentServiceUrl/$documentId", String::class.java)
        return response.body ?: "No content"
    }
}