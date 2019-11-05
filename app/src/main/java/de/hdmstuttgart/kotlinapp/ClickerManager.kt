package de.hdmstuttgart.kotlinapp

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
        val c = getAllClickers()

        if (clickCount >= autoClicker.price) {
            if (c.size < maxClickerThreads) {
                c.add(autoClicker)
                Thread(autoClicker).start()
                clickCount -= autoClicker.price
            }
            else {
                val randomClickerNumber = (0 until maxClickerThreads).random()
                c[randomClickerNumber].incrementClicksPerTick()
            }
            return true
        }

        return false
    }

    @Synchronized
    fun collectClicks() : Int {
        val c = getAllClickers()
        var collectedClicks = 0
        c.forEach { x -> collectedClicks += x.collectClicks() }
        return collectedClicks
    }

    @Synchronized
    private fun getAllClickers() : MutableList<AutoClicker>
    {
        return clickers;
    }

    @Synchronized
    private fun getClicks() : Int {
        return clickCount
    }
}