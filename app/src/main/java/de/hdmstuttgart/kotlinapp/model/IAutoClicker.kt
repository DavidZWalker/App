package de.hdmstuttgart.kotlinapp.model

interface IAutoClicker : Runnable {

    val price : Int
    var clicksPerSecond : Double
    fun collectClicks() : Int
}