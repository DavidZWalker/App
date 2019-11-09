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

    @Bindable
    var clicksPerSec = 0.0
        set(value) {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            field = df.format(value).toDouble()
            notifyPropertyChanged(BR.clicksPerSec)
        }

    private val clickers = mutableListOf<IAutoClicker>()

    init {
        startCollectingClicks()
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

        if (clicker != null && clicks >= clicker.price)
        {
            removeClicks(clicker.price)
            clickers.add(clicker)
            Thread(clicker).start()
            clicksPerSec += clicker.clicksPerSecond
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
