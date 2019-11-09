package de.hdmstuttgart.kotlinapp.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ClickerViewModel : BaseObservable() {

    @Bindable
    var clicks = 0

    fun addClick()
    {
        clicks++
        notifyPropertyChanged(BR.clicks)
    }

    fun addClicks(amount : Int)
    {
        clicks += amount
        notifyPropertyChanged(BR.clicks)
    }

    fun addAutoClicker()
    {
        Thread(Runnable {
            while(true) {
                Thread.sleep(1000)
                addClicks(1)
            }
        }).start()
    }

}
