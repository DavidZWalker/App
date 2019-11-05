package de.hdmstuttgart.kotlinapp

class ClickerManager {

    var clickCount = 0
    var clickers = mutableListOf<AutoClicker>()

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