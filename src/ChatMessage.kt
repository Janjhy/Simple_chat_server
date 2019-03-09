// Created by Jani Hyrkäs at 11.02.2019

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class ChatMessage(private val chatText: String, private val user: String){ //Class which overrides the toString function and creates a special ChatMessage class of sent user messages.
    private val time = LocalDateTime.now()
    private val timeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss.SSS")
    private val timeFormatted = time.format(timeFormat)
    private val username = user

    fun getUsername(): String {
        return username
    }

    override fun toString(): String {
        return "[$timeFormatted] $user: $chatText"
    }
}