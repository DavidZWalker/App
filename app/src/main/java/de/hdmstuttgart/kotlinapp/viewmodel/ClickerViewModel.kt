package de.hdmstuttgart.kotlinapp.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.model.IAutoClicker
import de.hdmstuttgart.kotlinapp.util.AutoClickerFactory
import de.hdmstuttgart.kotlinapp.util.BigIntegerToShortStringConverter
import java.math.BigDecimal
import java.math.BigInteger

class ClickerViewModel : BaseObservable() {

    var clicks : BigDecimal = 0.toBigDecimal()
        set(value) {
            field = value
            clicksString = BigIntegerToShortStringConverter.getStringForClicks(field)
        }

    private var clicksPerSec = 0.0.toBigDecimal()
        set(value) {
            field = String.format("%.2f", value).toBigDecimal()
            clicksPerSecString = field.toString()
        }

    @Bindable
    var clicksString = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.clicksString)
        }

    @Bindable
    var clicksPerSecString = ""
        set(value) {
            val prefix = "+"
            val suffix = "/sec"
            field = prefix + value + suffix
            notifyPropertyChanged(BR.clicksPerSecString)
        }

    private val clickers = mutableListOf<IAutoClicker>()

    init {
        startCollectingClicks()
        clicksPerSec = 0.0.toBigDecimal()
        clicks = 0.toBigDecimal()
    }

    fun addClick()
    {
        clicks++
    }

    fun addClicks(amount : BigDecimal)
    {
        clicks += amount
    }

    fun removeClicks(amount : BigDecimal)
    {
        addClicks(-amount)
    }

    fun addAutoClicker(clickerType : AutoClickers)
    {
        val clicker = AutoClickerFactory.getAutoClicker(clickerType)

        if (clicker != null && clicks >= clicker.price) {
            removeClicks(clicker.price)

            // limit the amount of threads that are created to the number of distinct clicker types
            val c = clickers.find { x -> x.name == clicker.name }
            if (c == null) {
                clickers.add(clicker)
                Thread(clicker).start()
            }
            else c.addClicker()

            clicksPerSec += clicker.currentClicksPerSec
        }
    }

    private fun startCollectingClicks()
    {
        Thread(Runnable {
            while (true)
            {
                Thread.sleep(50)
                clickers.forEach { x -> addClicks(x.collectClicks()) }
            }
        }).start()
    }

}
