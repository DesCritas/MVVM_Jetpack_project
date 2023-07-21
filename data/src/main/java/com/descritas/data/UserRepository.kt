package com.descritas.data



import javax.inject.Inject
import javax.inject.Singleton


@Singleton

class UserRepository @Inject constructor() {
    fun getUser(id: Long): User? {
        return getUsers().find { it.id == id }
    }

    fun getUsers(): List<User> {
        return listOf(
            User(id = 123, name = "James Bond", "jamesbond@007.com"),
            User(id = 345, name = "Batman", "batman@cave.com"),
            User(id = 111, name = "Batman1", "batman1@cave.com"),
            User(id = 222, name = "Batman2", "batman2@cave.com"),
            User(id = 333, name = "Batman3", "batman3@cave.com"),
            User(id = 444, name = "Batman4", "batman4@cave.com"),
            User(id = 555, name = "Batman5", "batman5@cave.com"),
            User(id = 999, name = "Arya Stark", "arya@winterfell.com")
        )
    }
}

data class User(
    val id: Long,
    val name: String,
    val email: String
    )
