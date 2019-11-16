package de.hdmstuttgart.kotlinapp.model.clickerValues

import de.hdmstuttgart.kotlinapp.model.AutoClickers
import java.math.BigDecimal

class ClickerValues {
    companion object {
        private val simple = SimpleClickerValues()
        private val improved = ImprovedClickerValues()

        fun priceOf(type : AutoClickers) : BigDecimal {
            return when (type) {
                AutoClickers.Simple -> simple.price
                AutoClickers.Improved -> improved.price
            }
        }

        fun clicksPerSecOf(type : AutoClickers) : BigDecimal {
            return when (type) {
                AutoClickers.Simple -> simple.clicksPerSec
                AutoClickers.Improved -> improved.clicksPerSec
            }
        }
    }
}