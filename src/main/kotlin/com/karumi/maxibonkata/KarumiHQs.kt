package com.karumi.maxibonkata

class KarumiHQs(val chat: Chat = ConsoleChat()) {
    companion object {
        val minMaxibons = 2
        val maxMaxibons = 10
    }
    var maxibonsLeft: Int = maxMaxibons

    fun openFridge(developer: Developer) {
        grabMaxibons(developer)
        if (shouldBuyMoreMaxibons()) {
            notifyWeShouldBuyMaxibon(developer)
            buyMaxibons()
        }
    }

    fun openFridge(developers: List<Developer>) {
        developers.forEach { developer ->
            openFridge(developer)
        }
    }

    private fun grabMaxibons(developer: Developer) {
        maxibonsLeft -= developer.maxibonsToGrab
        maxibonsLeft = Math.max(maxibonsLeft, 0)
    }

    private fun shouldBuyMoreMaxibons(): Boolean = maxibonsLeft <= minMaxibons

    private fun notifyWeShouldBuyMaxibon(developer: Developer) =
            chat.sendMessage("Hi guys, I'm ${developer.name}. We need more maxibons!")

    private fun buyMaxibons() {
        maxibonsLeft += maxMaxibons
    }
}