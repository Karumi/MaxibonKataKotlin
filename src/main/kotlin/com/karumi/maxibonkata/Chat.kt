package com.karumi.maxibonkata

interface Chat {
    fun sendMessage(message: String)
}

class ConsoleChat : Chat {
    override fun sendMessage(message: String) {
        println(message)
    }
}