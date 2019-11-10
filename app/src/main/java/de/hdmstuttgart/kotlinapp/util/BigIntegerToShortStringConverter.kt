package de.hdmstuttgart.kotlinapp.util

import java.math.BigDecimal
import java.math.BigInteger

class BigIntegerToShortStringConverter {
    companion object {

        private const val suffixThousand = "K"
        private const val suffixMillion = "M"
        private const val suffixBillion = "B"
        private const val suffixTrillion = "T"

        fun getStringForClicks(clicks : BigDecimal) : String {
            return when {
                clicks < 100_000.toBigDecimal()
                    -> clicks.toString()
                clicks < 1_000_000.toBigDecimal()
                    -> String.format("%.2f", clicks / 1000.0.toBigDecimal()) + suffixThousand
                clicks < 1_000_000_000.toBigDecimal()
                    -> String.format("%.2f", clicks / 1_000_000.0.toBigDecimal()) + suffixMillion
                clicks < 1_000_000_000_000.toBigDecimal()
                    -> String.format("%.2f", clicks / 1_000_000_000.0.toBigDecimal()) + suffixBillion
                clicks < 1_000_000_000_000_000.toBigDecimal()
                    -> String.format("%.2f", clicks / 1_000_000_000_000.0.toBigDecimal()) + suffixTrillion
                else -> "Over 9000"
            }
        }
    }
}
