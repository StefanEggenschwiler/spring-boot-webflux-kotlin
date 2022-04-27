package com.example.reactive.kotlin.handler

import com.example.reactive.kotlin.repo.PersonRepositoryCoroutine
import kotlinx.coroutines.FlowPreview
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class PersonHandlerCoroutine(@Autowired var repository: PersonRepositoryCoroutine) {

    @FlowPreview
    suspend fun findAll(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyAndAwait(repository.findAll())
    }

    suspend fun findById(request: ServerRequest): ServerResponse {
        val person = repository.findById(request.pathVariable("id").toInt())
        return when {
            person != null -> ServerResponse.ok().bodyValueAndAwait(person)
            else -> ServerResponse.notFound().buildAndAwait()
        }
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        return ServerResponse.created(
            URI.create("/v2/persons/${repository.save(request.awaitBody()).id}")
        ).buildAndAwait()
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        repository.deleteById(request.pathVariable("id").toInt())
        return ServerResponse.noContent().buildAndAwait()
    }
}