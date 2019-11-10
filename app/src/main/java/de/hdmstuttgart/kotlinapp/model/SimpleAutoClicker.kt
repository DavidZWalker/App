package de.hdmstuttgart.kotlinapp.model

class SimpleAutoClicker : IAutoClicker {

    override val price = 25
    override val name = "Simple"
    override val baseClicksPerSec = 0.1
    override var currentClicksPerSec = 0.1
    private var collectedClicks = 0

    override fun run() {
        while (true)
        {
            Thread.sleep((1000 / currentClicksPerSec).toLong())
            collectedClicks++
        }
    }

    override fun collectClicks() : Int {
        val clicksToReturn = collectedClicks
        collectedClicks = 0
        return clicksToReturn
    }

    override fun addClicker() {
        Thread.currentThread().interrupt()
        currentClicksPerSec += baseClicksPerSec
    }

}