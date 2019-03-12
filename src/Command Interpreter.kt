// Created by Jani Hyrkäs at 11.02.2019

import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.*

interface ChatObserver{
    fun update(text: ChatMessage)
}

class CommandInterpreter(x: InputStream, y: OutputStream): ChatObserver, Runnable { //Class which is created for each chat user, and mainly takes the user input and interprets it.
    private val input = Scanner(x)
    private val output = PrintStream(y, true)
    private var username = ""
    private var finished = true

    override fun run(){
        output.println("Server: Hello and welcome. Commands are :history, :exit, :user and :users.")
        ChatHistory.registerObserver(this)
        loop@ while(finished){
            var text = input.nextLine()
            println(text) //for testing purposes

            if(text.startsWith(":"))
            {
                text = text.removePrefix(":")
                var splitText = (text.split(" "))
                when(splitText[0]){
                    "exit" -> {
                        exitChat()
                        break@loop
                    }
                    "user" -> {
                        if(Users.checkIsTaken(splitText[1]) || splitText[1] == "Server") {
                            output.println("Server: Username in use.")
                        }
                        else {
                            Users.newUser(splitText[1])
                            username = splitText[1]
                            output.println("Server: Hello $username")
                        }
                    }
                    "history" -> printHistory()
                    "users" -> printUsers()
                    else -> output.println("Server: Invalid command.")
                }
            }
            else if (username.isBlank()){
                output.println("Server: No username set. Use \":user [username]\" as command to set your username.")
            }
            else {
                var x = ChatMessage(text, username)
                ChatHistory.newLog(x)
            }
        }
    }

    override fun update(text: ChatMessage) {
        output.println(text)
    }

    private fun printHistory() {
        ChatHistory.getHistory().forEach {
            output.println("History: $it")
        }
    }

    private fun printUsers() {
        Users.getUserLog().forEach {
            output.println(it)
        }
    }

    private fun exitChat() {
        Users.removeUser(username)
        ChatHistory.deregisterObserver(this)
        output.println("Goodbye $username")
        input.close()
    }
}