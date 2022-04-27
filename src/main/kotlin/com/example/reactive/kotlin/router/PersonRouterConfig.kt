package com.example.reactive.kotlin.router

import com.example.reactive.kotlin.handler.PersonHandler
import com.example.reactive.kotlin.handler.PersonHandlerCoroutine
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class PersonRouterConfig {

    @Bean
    fun personRouter(handler: PersonHandler) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/v1/persons", handler::findAll)
            GET("/v1/persons/{id}", handler::findById)
            POST("/v1/persons", handler::save)
            DELETE("/v1/persons/{id}", handler::delete)
        }
    }

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