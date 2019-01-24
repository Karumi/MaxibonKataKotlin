package com.karumi.maxibonkata

data class Developer internal constructor(
    val name: String = "",
    private val numberOfMaxibons: Int = 0
) {
    val maxibonsToGrab: Int = Math.max(0, numberOfMaxibons)

    companion object {
        val pedro = Developer("Pedro", numberOfMaxibons = 3)
        val fran = Developer("Fran", numberOfMaxibons = 1)
        val davide = Developer("Davide", numberOfMaxibons = 0)
        val sergio = Developer("Sergio", numberOfMaxibons = 2)
        val jorge = Developer("Jorge", numberOfMaxibons = 1)
    }
}
