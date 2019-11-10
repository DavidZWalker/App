package de.hdmstuttgart.kotlinapp.model

interface IAutoClicker : Runnable {

    val price : Int
    val name : String
    val baseClicksPerSec : Double
    var currentClicksPerSec : Double
    fun collectClicks() : Int
    fun addClicker()
}