// Created by Jani Hyrkäs at 11.02.2019

import java.lang.Exception
import java.net.ServerSocket
import java.net.SocketTimeoutException

class ChatServer{ //The class which takes user connections with ServerSocket and creates the CommandInterpreter class for each into a new thread.
    fun serve() {
        TopChatter.registerTop()
        val x = ServerSocket(30043)
        while(true) {
            try {
                val s = x.accept()
                val cI = CommandInterpreter(s.getInputStream(), s.getOutputStream())
                val newThread = Thread(cI)
                println("New connection")
                newThread.start()
            }
            catch (e: Exception) {
                println("Exception: ${e.message}")
            }
        }
        TopChatter.deregisterTop()
    }
}
