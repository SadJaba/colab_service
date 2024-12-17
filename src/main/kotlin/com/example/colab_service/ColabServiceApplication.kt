package com.example.colab_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ColabServiceApplication

fun main(args: Array<String>) {
    runApplication<ColabServiceApplication>(*args)
}
