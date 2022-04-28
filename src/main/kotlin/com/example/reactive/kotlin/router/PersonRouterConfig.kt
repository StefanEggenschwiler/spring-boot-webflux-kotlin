package com.example.reactive.kotlin.router

import com.example.reactive.kotlin.handler.PersonHandler
import com.example.reactive.kotlin.handler.PersonHandlerCoroutine
import com.example.reactive.kotlin.repo.PersonRepository
import com.example.reactive.kotlin.repo.PersonRepositoryCoroutine
import kotlinx.coroutines.FlowPreview
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class PersonRouterConfig {

        @RouterOperations(
            RouterOperation(path = "/v1/persons", beanClass = PersonRepository::class, beanMethod = "findAll"),
            RouterOperation(path = "/v1/persons/{id}", beanClass = PersonRepository::class, beanMethod = "findById"),
            RouterOperation(path = "/v1/persons", beanClass = PersonRepository::class, beanMethod = "save"),
            RouterOperation(path = "/v1/persons/{id}", beanClass = PersonRepository::class, beanMethod = "deleteById")
    )
    @Bean
    fun personRouter(handler: PersonHandler) = router {
        accept(MediaType.APPLICATION_JSON).and("/v1/persons").nest {
            GET("", handler::findAll)
            GET("/{id}", handler::findById)
            POST("", handler::save)
            DELETE("/{id}", handler::delete)
        }
    }

    @RouterOperations(
        RouterOperation(path = "/v2/persons", beanClass = PersonRepositoryCoroutine::class, beanMethod = "findAll"),
        RouterOperation(path = "/v2/persons/{id}", beanClass = PersonRepositoryCoroutine::class, beanMethod = "findById"),
        RouterOperation(path = "/v2/persons", beanClass = PersonRepositoryCoroutine::class, beanMethod = "save"),
        RouterOperation(path = "/v2/persons/{id}", beanClass = PersonRepositoryCoroutine::class, beanMethod = "deleteById")
    )
    @FlowPreview
    @Bean
    fun personRouterCoroutine(handler: PersonHandlerCoroutine) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/v2/persons", handler::findAll)
            GET("/v2/persons/{id}", handler::findById)
            POST("/v2/persons", handler::save)
            DELETE("/v2/persons/{id}", handler::delete)
        }
    }
}