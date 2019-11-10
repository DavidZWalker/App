package de.hdmstuttgart.kotlinapp.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.model.IAutoClicker
import de.hdmstuttgart.kotlinapp.util.AutoClickerFactory
import java.math.RoundingMode
import java.text.DecimalFormat

class ClickerViewModel : BaseObservable() {

    @Bindable
    var clicks = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.clicks)
        }

    private var clicksPerSec = 0.0
        set(value) {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            field = df.format(value).toDouble()
            clicksPerSecString = field.toString()
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
        clicksPerSec = 0.0
    }

    fun addClick()
    {
        clicks++
    }

    fun addClicks(amount : Int)
    {
        clicks += amount
    }

    fun removeClicks(amount : Int)
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
