package de.hdmstuttgart.kotlinapp.model

class SimpleAutoClicker : IAutoClicker {
    override val price = 25

    override var clicksPerSecond = 0.1
    private var collectedClicks = 0

    override fun run() {
        while (true)
        {
            Thread.sleep((1000 / clicksPerSecond).toLong())
            collectedClicks++
        }
    }

    override fun collectClicks() : Int {
        val clicksToReturn = collectedClicks
        collectedClicks = 0
        return clicksToReturn
    }

}