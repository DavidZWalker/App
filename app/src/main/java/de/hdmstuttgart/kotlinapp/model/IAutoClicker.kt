package de.hdmstuttgart.kotlinapp.model

import java.math.BigDecimal

interface IAutoClicker : Runnable {

    val price : BigDecimal
    val name : String
    val baseClicksPerSec : BigDecimal
    var currentClicksPerSec : BigDecimal
    fun collectClicks() : BigDecimal
    fun addClicker()
}