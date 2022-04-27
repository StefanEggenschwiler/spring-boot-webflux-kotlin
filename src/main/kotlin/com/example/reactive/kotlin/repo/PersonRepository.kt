package com.example.reactive.kotlin.repo

import com.example.reactive.kotlin.model.Person
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface PersonRepository : ReactiveCrudRepository<Person, Int>