// Created by Jani Hyrkäs at 11.02.2019

import java.util.*
import kotlin.collections.HashSet

interface  ChatObservable{
    fun registerObserver(observer: ChatObserver)
    fun deregisterObserver(observer: ChatObserver)
    fun notifyObservers()
}

object ChatHistory: ChatObservable{ //Singleton object/class which holds all messages sent to the chat server. The server and users observe this class and are able to new messages and the history.
    private val historyLog = ArrayList<ChatMessage>()
    private val observerLog = HashSet<ChatObserver>()

    override fun registerObserver(observer: ChatObserver) {
        observerLog.add(observer)
    }

    override fun deregisterObserver(observer: ChatObserver) {
        observerLog.remove(observer)
    }

    override fun notifyObservers() {
        observerLog.forEach {
            it.update(historyLog.last())
        }
    }

    fun newLog(textLog: ChatMessage) {
        historyLog.add(textLog)
        notifyObservers()
    }

    fun getHistory(): ArrayList<ChatMessage> {
        return historyLog
    }

}