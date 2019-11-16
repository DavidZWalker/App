package de.hdmstuttgart.kotlinapp.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import de.hdmstuttgart.kotlinapp.model.AutoClickerTask
import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.model.IAutoClickerTask
import de.hdmstuttgart.kotlinapp.util.BigIntegerToShortStringConverter
import de.hdmstuttgart.kotlinapp.util.Constants
import java.math.BigDecimal
import java.math.RoundingMode

class ClickerViewModel : BaseObservable() {

    private var clickerTask = AutoClickerTask()

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

    @Bindable
    val simpleButtonString = "+ " +
            clickerTask.getClicksPerSecForClickerType(AutoClickers.Simple).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString() +
            "/sec \n" +
            "(" +
            clickerTask.getPriceForClickerType(AutoClickers.Simple).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString() +
            ")"

    @Bindable
    val improvedButtonString = "+ " +
            clickerTask.getClicksPerSecForClickerType(AutoClickers.Improved).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString() +
            "/sec \n" +
            "(" +
            clickerTask.getPriceForClickerType(AutoClickers.Improved).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString() +
            ")"

    init {
        startCollectingClicks()
        Thread(clickerTask).start()
        clicksPerSec = BigDecimal(0.0)
        clicks = BigDecimal(0)
    }

    fun addClick()
    {
        clicks += BigDecimal(100_000)
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
        val clickerPrice = clickerTask.getPriceForClickerType(clickerType)
        if (clicks >= clickerPrice) {
            clickerTask.addClicker(clickerType)
            clicks -= clickerPrice
            clicksPerSec += clickerTask.getClicksPerSecForClickerType(clickerType)
        }
    }

    private fun startCollectingClicks()
    {
        Thread(Runnable {
            while (true)
            {
                Thread.sleep((1000 / Constants.FRAMES_PER_SECOND).toLong())
                addClicks(clickerTask.collectClicks())
            }
        }).start()
    }

}
