package de.hdmstuttgart.kotlinapp.viewmodel

import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import de.hdmstuttgart.kotlinapp.R
import de.hdmstuttgart.kotlinapp.model.RankUpHelper
import de.hdmstuttgart.kotlinapp.util.ClickerAPI
import de.hdmstuttgart.kotlinapp.util.ResourceProvider

class RankViewModel : BaseObservable() {

    private val maxRank = 3

    @Bindable
    var rank = 1
        set(value) {
            field = value
            rankImage = getRankImageForCurrentRank()
            notifyPropertyChanged(BR.rank)
        }

    @Bindable
    var rankImage : Drawable? = null
        get() {
            return getRankImageForCurrentRank()
        }
        set(value) {
        field = value
        notifyPropertyChanged(BR.rankImage)
    }

    @Bindable
    var canRankUp : Boolean = false
        get() {
            return ClickerAPI.getInstance().clicks >= RankUpHelper.getNeededClicksForNextRank(rank)
        }

    fun rankUp() {
        if (rank < maxRank)
            rank++
    }

    private fun getRankImageForCurrentRank() : Drawable {
        val drawableId = ResourceProvider.getResourceIdByName("ic_rank_$rank", "drawable")
        return ResourcesCompat.getDrawable(ResourceProvider.getResources(), drawableId, null)!!
    }
}