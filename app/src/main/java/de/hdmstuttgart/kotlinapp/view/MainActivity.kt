package de.hdmstuttgart.kotlinapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import de.hdmstuttgart.kotlinapp.R
import de.hdmstuttgart.kotlinapp.databinding.ActivityMainBinding
import de.hdmstuttgart.kotlinapp.model.AutoClickers
import de.hdmstuttgart.kotlinapp.viewmodel.ClickerViewModel
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : ClickerViewModel
    private lateinit var mBinding : ActivityMainBinding
    private val clicksFileName = "clicks"
    private val clicksPerSecFilename = "clicksPerSec"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ClickerViewModel()
        mBinding.viewModel = viewModel
        // DEV_RESETONSTART()
        load()
    }

    override fun onStop() {
        save()
        super.onStop()
    }

    private fun DEV_RESETONSTART()
    {
        applicationContext.deleteFile(clicksFileName)
        applicationContext.deleteFile(clicksPerSecFilename)
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

    private fun load() {
        var loadedClicksString = "0"
        if (applicationContext.fileList().contains("clicks"))
            applicationContext.openFileInput("clicks").use {

                loadedClicksString = String(it.readBytes())
                it.close()
            }

        var loadedClicksPerSecString = "0"
        if (applicationContext.fileList().contains("clicksPerSec"))
            applicationContext.openFileInput("clicksPerSec").use {

                loadedClicksPerSecString = String(it.readBytes())
                it.close()
            }

        if (loadedClicksString != "0" || loadedClicksPerSecString != "0") {
            val loadedClicks = BigDecimal(loadedClicksString)
            val loadedClicksPerSec = BigDecimal(loadedClicksPerSecString)
            viewModel.loadFromData(loadedClicks, loadedClicksPerSec)
        }
    }

    private fun save() {
        applicationContext.openFileOutput(clicksFileName, Context.MODE_PRIVATE).use {
            it.write(viewModel.clicks.toString().toByteArray())
            it.close()
        }
        applicationContext.openFileOutput(clicksPerSecFilename, Context.MODE_PRIVATE).use {
            it.write(viewModel.clicksPerSec.toString().toByteArray())
            it.close()
        }
    }
}
