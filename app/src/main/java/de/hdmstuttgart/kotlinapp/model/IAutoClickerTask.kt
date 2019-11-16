package de.hdmstuttgart.kotlinapp.model

import java.math.BigDecimal

interface IAutoClickerTask : Runnable {

    val baseClicksPerSec : BigDecimal
    var currentClicksPerSec : BigDecimal
    fun collectClicks() : BigDecimal
    fun addClicker(type : AutoClickers)
}