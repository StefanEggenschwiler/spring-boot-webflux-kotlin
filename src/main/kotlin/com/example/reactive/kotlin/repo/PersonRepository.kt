package com.example.reactive.kotlin.repo

import com.example.reactive.kotlin.model.Person
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Tag(name="Persons")
@Repository
interface PersonRepository : ReactiveCrudRepository<Person, Int>