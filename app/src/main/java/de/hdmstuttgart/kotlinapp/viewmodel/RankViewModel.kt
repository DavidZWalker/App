package de.hdmstuttgart.kotlinapp.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class RankViewModel : BaseObservable() {

    @Bindable
    var rank = "1"
        set(value) {
            field = value
            notifyPropertyChanged(BR.rank)
        }
}