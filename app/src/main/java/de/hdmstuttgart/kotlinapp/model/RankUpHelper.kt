package de.hdmstuttgart.kotlinapp.model

import java.math.BigDecimal

class RankUpHelper {

    companion object {
        fun getNeededClicksForNextRank(currentRank: Int): BigDecimal {
            return getNeededClicksForRank(currentRank + 1)
        }

        private fun getNeededClicksForRank(rank: Int): BigDecimal {
            return when (rank) {
                2 -> BigDecimal(100_000) // 100K
                3 -> BigDecimal(10_000_000) // 10M
                4 -> BigDecimal(1_000_000_000) // 1B
                5 -> BigDecimal(1_000_000_000_000) // 1T
                6 -> BigDecimal(100_000_000_000_000_000) // 100AA
                7 -> BigDecimal("10000000000000000000") // 10BB
                8 -> BigDecimal("1000000000000000000000000") // 1DD
                9 -> BigDecimal("100000000000000000000000000000") // 100EE
                10 -> BigDecimal("1000000000000000000000000000000000000") // 1HH
                else -> getNeededClicksForRank(rank - 1) * BigDecimal(rank) * BigDecimal(1000)
            }
        }
    }
}