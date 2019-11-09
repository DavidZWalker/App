package de.hdmstuttgart.kotlinapp.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ClickerViewModel : BaseObservable() {

    @Bindable
    var clicks = 0

    fun addClick()
    {
        clicks++
        notifyPropertyChanged(androidx.databinding.library.baseAdapters.BR.clicks)
    }

}
