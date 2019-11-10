package de.hdmstuttgart.kotlinapp.util

import java.math.BigDecimal

class BigIntegerToShortStringConverter {
    companion object {

        private val BigDecimal_OneThousand = BigDecimal(1000)
        private val BigDecimal_OneHundredThousand = BigDecimal(100_000)
        private val BigDecimal_OneMillion = BigDecimal(1_000_000)
        private val BigDecimal_OneBillion = BigDecimal(1_000_000_000)
        private val BigDecimal_OneTrillion = BigDecimal(1_000_000_000_000)
        private val BigDecimal_OneAA = BigDecimal(1_000_000_000_000_000)

        private const val suffixThousand = "K"
        private const val suffixMillion = "M"
        private const val suffixBillion = "B"
        private const val suffixTrillion = "T"

        fun getStringForClicks(clicks : BigDecimal) : String {
            return when {
                clicks < BigDecimal_OneHundredThousand
                    -> clicks.toString()
                clicks < BigDecimal_OneMillion
                    -> String.format("%.2f", clicks.divide(BigDecimal_OneThousand)) + suffixThousand
                clicks < BigDecimal_OneBillion
                    -> String.format("%.2f", clicks.divide(BigDecimal_OneMillion)) + suffixMillion
                clicks < BigDecimal_OneTrillion
                    -> String.format("%.2f", clicks.divide(BigDecimal_OneBillion)) + suffixBillion
                clicks < BigDecimal_OneAA
                    -> String.format("%.2f", clicks.divide(BigDecimal_OneTrillion)) + suffixTrillion
                else -> "Over 9000"
            }
        }
    }
}
