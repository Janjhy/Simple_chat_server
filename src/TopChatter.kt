// Created by Jani Hyrkäs at 11.02.2019

object TopChatter: ChatObserver { //Singleton object/class which holds information of the chat users with most messages.
    private val hiChatList = hashMapOf<String, Int>()

    override fun update(text: ChatMessage) {
        println(text)
        if(!hiChatList.containsKey(text.getUsername())) {
            println("New entry to chat list: ${text.getUsername()}")
            hiChatList[text.getUsername()] = 1
        }
        else{
            var i: Int = hiChatList.getValue(text.getUsername())
            i++
            hiChatList.replace(text.getUsername(), i)
        }
        getTopChatters()
    }

    private fun mapToTopList(): List<Pair<String, Int>> {
        val list = hiChatList.toList().sortedByDescending { (_,value)-> value }
        if(list.size < 4)
        {
            return list
        }
        return list.subList(0, 3)
    }

    private fun getTopChatters(){
        val x = mapToTopList()
        for(i in 0 until x.size) {
            println("${i + 1}: ${x[i].first} with ${x[i].second} messages.")
        }
    }

    fun registerTop() {
        ChatHistory.registerObserver(this)
    }

    fun deregisterTop( ){
        ChatHistory.deregisterObserver(this)
    }
}