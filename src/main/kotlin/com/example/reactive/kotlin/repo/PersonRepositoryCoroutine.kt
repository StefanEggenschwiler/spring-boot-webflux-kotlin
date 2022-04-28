package com.example.reactive.kotlin.repo

import com.example.reactive.kotlin.model.Person
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Tag(name="Persons")
@Repository
interface PersonRepositoryCoroutine : CoroutineCrudRepository<Person, Int>