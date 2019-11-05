package de.hdmstuttgart.kotlinapp

import kotlinx.coroutines.*
import java.lang.Runnable
import kotlin.concurrent.thread

class ClickerManager {

    var clickCount = 0
    private var clickers = mutableListOf<AutoClicker>()
    var maxClickerThreads = 50

    @Synchronized
    fun addClicks(amount: Int)
    {
        clickCount += amount
    }

    @Synchronized
    fun buyAutoClicker() : Boolean
    {
        var autoClicker = AutoClicker()

        if (clickCount >= autoClicker.price) {
            clickers.add(autoClicker)
            Thread(autoClicker).start()
            clickCount -= autoClicker.price
            return true
        }

        return false
    }

    @Synchronized
    fun collectClicks() : Int {
        var collectedClicks = 0
        clickers.forEach { x -> collectedClicks += x.collectClicks() }
        return collectedClicks
    }
}
