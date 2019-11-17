package de.hdmstuttgart.kotlinapp.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import de.hdmstuttgart.kotlinapp.model.AutoClickerTask
import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.util.BigDecimalToShortStringConverter
import de.hdmstuttgart.kotlinapp.util.ClickerAPI
import de.hdmstuttgart.kotlinapp.util.Constants
import java.math.BigDecimal
import java.math.RoundingMode

class ClickerViewModel : BaseObservable() {

    private var clickerTask = AutoClickerTask()
    private val clickerApi = ClickerAPI.getInstance()

    var clicks : BigDecimal = BigDecimal(0)
        set(value) {
            field = value
            clickerApi.clicks = field
            clicksString = BigDecimalToShortStringConverter.getShortStringFor(field)
        }

    var clicksPerSec = BigDecimal(0)
        set(value) {
            field = value
            clickerApi.clicksPerSec = field
            clicksPerSecString = BigDecimalToShortStringConverter.getShortStringFor(field, true)
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

    private fun getButtonStringForClickerType(type : AutoClickers) : String {
        return "+ " +
                BigDecimalToShortStringConverter.getShortStringFor(clickerTask.getClicksPerSecForClickerType(type).setScale(2, RoundingMode.HALF_UP)) +
                "/sec \n" +
                "($" +
                BigDecimalToShortStringConverter.getShortStringFor(clickerTask.getPriceForClickerType(type).setScale(2, RoundingMode.HALF_UP), true) +
                ")"
    }

    @Bindable
    val simpleButtonString = getButtonStringForClickerType(AutoClickers.Simple)

    @Bindable
    val improvedButtonString = getButtonStringForClickerType(AutoClickers.Improved)

    @Bindable
    val advancedButtonString = getButtonStringForClickerType(AutoClickers.Advanced)

    @Bindable
    val proButtonString = getButtonStringForClickerType(AutoClickers.Pro)

    @Bindable
    val eliteButtonString = getButtonStringForClickerType(AutoClickers.Elite)

    @Bindable
    val legendaryButtonString = getButtonStringForClickerType(AutoClickers.Legendary)

    @Bindable
    val exoticButtonString = getButtonStringForClickerType(AutoClickers.Exotic)

    @Bindable
    val mythicButtonString = getButtonStringForClickerType(AutoClickers.Mythic)

    init {
        startCollectingClicks()
        Thread(clickerTask).start()
        clicksPerSec = BigDecimal(0.0)
        clicks = BigDecimal(0)
    }

    fun addClick()
    {
        clicks += BigDecimal(1)
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

    fun loadFromData(clicks : BigDecimal, clicksPerSec : BigDecimal) {
        this.clicks = clicks

        this.clicksPerSec = clicksPerSec
        clickerTask.currentClicksPerSec = clicksPerSec
    }

}
