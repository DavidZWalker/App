package de.hdmstuttgart.kotlinapp.util

import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.model.IAutoClicker
import de.hdmstuttgart.kotlinapp.model.SimpleAutoClicker

class AutoClickerFactory {

    companion object {
        fun getAutoClicker(type: AutoClickers): IAutoClicker? {
            when (type) {
                AutoClickers.Simple -> return SimpleAutoClicker()
            }

            return null
        }
    }
}