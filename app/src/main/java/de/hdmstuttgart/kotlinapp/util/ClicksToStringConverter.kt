package de.hdmstuttgart.kotlinapp.util

class ClicksToStringConverter {
    companion object {

        private const val suffixThousand = "K"
        private const val suffixMillion = "M"
        private const val suffixBillion = "B"
        private const val suffixTrillion = "T"
        private const val divisor = 1000.0

        fun getStringForClicks(clicks : Long) : String {

            return when {
                clicks < 100_000 -> clicks.toString()
                clicks < 1_000_000 -> String.format("%.2f", clicks / divisor) + suffixThousand
                clicks < 1_000_000_000 -> String.format("%.2f", clicks / divisor) + suffixMillion
                clicks < 1_000_000_000_000 -> String.format("%.2f", clicks / divisor) + suffixBillion
                clicks < 1_000_000_000_000_000 -> String.format("%.2f", clicks / divisor) + suffixTrillion
                else -> "Over 9000"
            }
        }
    }
}