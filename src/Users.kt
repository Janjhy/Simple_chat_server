// Created by Jani Hyrkäs at 11.02.2019

object Users {
    private val userLog = HashSet<String>() //Singleton object/class which holds all the current users connected to the chat server with usernames.

    fun getUserLog(): HashSet<String> {
        return userLog
    }

    fun newUser(text: String): Boolean {
        if(userLog.contains(text)) return false
        userLog.add(text)
        return true
    }

    fun checkIsTaken(text: String): Boolean {
        return userLog.contains(text)
    }

    fun removeUser(text: String): Boolean {
        return userLog.remove(text)

    }
}