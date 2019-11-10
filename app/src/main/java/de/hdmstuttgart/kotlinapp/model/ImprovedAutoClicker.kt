package de.hdmstuttgart.kotlinapp.model

class ImprovedAutoClicker : IAutoClicker {
    override val price = 250
    override val name = "Improved"
    override val baseClicksPerSec = 2.0
    override var currentClicksPerSec = 2.0
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