package de.hdmstuttgart.kotlinapp.util

import java.math.BigDecimal

class ClickerAPI {
    companion object {
        var instnc : ClickerAPI? = null

        fun getInstance() : ClickerAPI {
            if (instnc == null)
                instnc = ClickerAPI()
            return instnc as ClickerAPI
        }
    }

    lateinit var clicks : BigDecimal

    lateinit var clicksPerSec : BigDecimal
}