package de.hdmstuttgart.kotlinapp.model

class SimpleAutoClicker : Runnable {
    val price = 25
    private val clicksPerSecond = 1

    var collectedClicks = 0

    override fun run() {
        while (true)
        {
            Thread.sleep((1000 / clicksPerSecond).toLong())
            collectedClicks++
        }
    }

    fun collectClicks() : Int {
        val clicksToReturn = collectedClicks
        collectedClicks = 0
        return clicksToReturn
    }

}