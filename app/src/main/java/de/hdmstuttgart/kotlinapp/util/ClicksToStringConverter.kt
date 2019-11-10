package de.hdmstuttgart.kotlinapp.util

class ClicksToStringConverter {
    companion object {

        private const val suffixThousand = "K"
        private const val suffixMillion = "M"
        private const val suffixBillion = "B"
        private const val suffixTrillion = "T"

        fun getStringForClicks(clicks : Long) : String {

            return when {
                clicks < 100_000 -> clicks.toString()
                clicks < 1_000_000 -> String.format("%.2f", clicks / 1000.0) + suffixThousand
                clicks < 1_000_000_000 -> String.format("%.2f", clicks / 1_000_000.0) + suffixMillion
                clicks < 1_000_000_000_000 -> String.format("%.2f", clicks / 1_000_000_000.0) + suffixBillion
                clicks < 1_000_000_000_000_000 -> String.format("%.2f", clicks / 1_000_000_000_000.0) + suffixTrillion
                else -> "Over 9000"
            }
        }
    }
}