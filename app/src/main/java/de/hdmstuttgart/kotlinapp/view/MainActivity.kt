package de.hdmstuttgart.kotlinapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import de.hdmstuttgart.kotlinapp.R
import de.hdmstuttgart.kotlinapp.databinding.ActivityMainBinding
import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.viewmodel.ClickerViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel = ClickerViewModel()
    lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = viewModel
    }

    fun addClick(v : View)
    {
        viewModel.addClick()
    }

    fun buyAutoClicker(v : View)
    {
        when (v.id)
        {
            R.id.buySimple -> viewModel.addAutoClicker(AutoClickers.Simple)
            R.id.buyImproved -> viewModel.addAutoClicker(AutoClickers.Improved)
            R.id.buyAdvanced -> viewModel.addAutoClicker(AutoClickers.Advanced)
            R.id.buyPro -> viewModel.addAutoClicker(AutoClickers.Pro)
            R.id.buyElite -> viewModel.addAutoClicker(AutoClickers.Elite)
            R.id.buyLegendary -> viewModel.addAutoClicker(AutoClickers.Legendary)
            R.id.buyExotic -> viewModel.addAutoClicker(AutoClickers.Exotic)
            R.id.buyMythic -> viewModel.addAutoClicker(AutoClickers.Mythic)
        }
    }

}
