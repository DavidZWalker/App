package de.hdmstuttgart.kotlinapp.model

import de.hdmstuttgart.kotlinapp.model.clickerValues.ClickerValueHolder
import de.hdmstuttgart.kotlinapp.util.Constants
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class AutoClickerTask : IAutoClickerTask {

    override val baseClicksPerSec = BigDecimal(0)
    override var currentClicksPerSec = BigDecimal(0)
    private var collectedClicks = BigDecimal(0)

    override fun run() {
        while (true)
        {
            Thread.sleep((1000 / Constants.FRAMES_PER_SECOND).toLong())
            collectedClicks += currentClicksPerSec.divide(BigDecimal(Constants.FRAMES_PER_SECOND), MathContext.DECIMAL32)
        }
    }

    override fun collectClicks() : BigDecimal {
        val floor = collectedClicks.setScale(0, RoundingMode.FLOOR)
        collectedClicks -= floor
        return floor
    }

    override fun addClicker(type : AutoClickers) {
        Thread.currentThread().interrupt()
        currentClicksPerSec += getClicksPerSecForClickerType(type)
    }

    fun getPriceForClickerType(type: AutoClickers) : BigDecimal {
        return ClickerValueHolder.priceOf(type)
    }

    fun getClicksPerSecForClickerType(type : AutoClickers) : BigDecimal {
        return ClickerValueHolder.clicksPerSecOf(type)
    }
}