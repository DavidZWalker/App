package de.hdmstuttgart.kotlinapp

class AutoClicker : Runnable {

    var price = 10
    var sleepTime = 2000L
    var clicksPerTick = 1
    private var collectibleClicks = 0

    override fun run(){
        while (true) {
            Thread.sleep(sleepTime)
            collectibleClicks += clicksPerTick
        }
    }

    fun collectClicks() : Int {
        val currentClicks = collectibleClicks
        collectibleClicks = 0
        return currentClicks
    }

}