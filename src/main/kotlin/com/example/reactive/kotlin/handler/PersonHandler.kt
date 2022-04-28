package com.example.reactive.kotlin.handler

import com.example.reactive.kotlin.model.Person
import com.example.reactive.kotlin.repo.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class PersonHandler(private val repository: PersonRepository) {

    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(repository.findAll(), Person::class.java)
    }

    fun findById(request: ServerRequest): Mono<ServerResponse> {
        return repository.findById(request.pathVariable("id").toInt()).flatMap { p ->
            ServerResponse.ok().bodyValue(p)
            // or ServerResponse.ok().body(p.toMono(), Person::class.java)
        }.switchIfEmpty(
            ServerResponse.notFound().build()
        )
    }

    fun save(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Person::class.java)
            .flatMap(this.repository::save)
            .flatMap { p -> ServerResponse.created(URI.create("/v1/persons/${p.id}")).build() }
    }

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
            .noContent()
            .build(this.repository.deleteById(request.pathVariable("id").toInt()))
    }

    fun deleteJust(request: ServerRequest): Mono<ServerResponse> {
        return Mono.just(repository.deleteById(request.pathVariable("id").toInt()))
            .flatMap { ServerResponse.noContent().build() }
    }
}