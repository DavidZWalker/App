package de.hdmstuttgart.kotlinapp.model

import java.math.BigDecimal

class SimpleAutoClicker : IAutoClicker {

    override val price = 25.toBigDecimal()
    override val name = "Simple"
    override val baseClicksPerSec = 0.1.toBigDecimal()
    override var currentClicksPerSec = 0.1.toBigDecimal()
    private var collectedClicks = 0.toBigDecimal()
    private val clicksPerTick = 1.toBigDecimal()

    override fun run() {
        while (true)
        {
            Thread.sleep((1000.toBigDecimal() / currentClicksPerSec).toLong())
            collectedClicks += clicksPerTick
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