package de.hdmstuttgart.kotlinapp.util

import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.model.IAutoClicker
import de.hdmstuttgart.kotlinapp.model.ImprovedAutoClicker
import de.hdmstuttgart.kotlinapp.model.SimpleAutoClicker

class AutoClickerFactory {

    companion object {
        fun getAutoClicker(type: AutoClickers): IAutoClicker? {
            return when (type) {
                AutoClickers.Simple -> SimpleAutoClicker()
                AutoClickers.Improved -> ImprovedAutoClicker()
            }
        }
    }
}