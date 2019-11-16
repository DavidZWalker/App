package de.hdmstuttgart.kotlinapp.util

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class BigDecimalToShortStringConverter {
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

        fun getShortStringFor(clicks : BigDecimal, toPlainString : Boolean = false) : String {

            var suffix = ""
            val tempValue = when {
                clicks < BigDecimal_OneHundredThousand -> clicks
                clicks < BigDecimal_OneMillion
                    -> {
                    suffix = suffixThousand
                    clicks.divide(BigDecimal_OneThousand)
                }
                clicks < BigDecimal_OneBillion
                    -> {
                    suffix = suffixMillion
                    clicks.divide(BigDecimal_OneMillion)
                }
                clicks < BigDecimal_OneTrillion
                    -> {
                    suffix = suffixBillion
                    clicks.divide(BigDecimal_OneBillion)
                }
                clicks < BigDecimal_OneAA
                    -> {
                    suffix = suffixTrillion
                    clicks.divide(BigDecimal_OneTrillion)
                }
                else -> BigDecimal(-1)
            }

            return when {
                toPlainString -> tempValue.setScale(2, RoundingMode.HALF_UP).toPlainString() + suffix
                clicks < BigDecimal_OneHundredThousand -> clicks.toString()
                else -> String.format("%.2f", tempValue) + suffix
            }
        }
    }
}
