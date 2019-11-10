package de.hdmstuttgart.kotlinapp.model

import java.math.BigDecimal

class ImprovedAutoClicker : IAutoClicker {
    override val price = 250.toBigDecimal()
    override val name = "Improved"
    override val baseClicksPerSec = 2.0.toBigDecimal()
    override var currentClicksPerSec = 2.0.toBigDecimal()
    private var collectedClicks = 0.toBigDecimal()

    override fun run() {
        while (true)
        {
            Thread.sleep((1000.toBigDecimal() / currentClicksPerSec).toLong())
            collectedClicks++
        }
    }

    override fun collectClicks() : BigDecimal {
        val clicksToReturn = collectedClicks
        collectedClicks = 0.toBigDecimal()
        return clicksToReturn
    }

    override fun addClicker() {
        Thread.currentThread().interrupt()
        currentClicksPerSec += baseClicksPerSec
    }
}