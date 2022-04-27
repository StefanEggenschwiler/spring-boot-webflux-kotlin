package com.example.reactive.kotlin.repo

import com.example.reactive.kotlin.model.Person
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PersonRepositoryCoroutine : CoroutineCrudRepository<Person, Int>