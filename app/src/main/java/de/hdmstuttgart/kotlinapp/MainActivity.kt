package de.hdmstuttgart.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val clickerManager = ClickerManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addClicks(0)
        mainButton.setOnClickListener { v -> addClicks(1)}
        buyButton.setOnClickListener { v -> buyClicker() }
        thread(start = true) {
            while(true) {
                addClicks(clickerManager.collectClicks())
            }
        }
    }

    fun addClicks(amount: Int)
    {
        clickerManager.addClicks(amount)
        tvClicks.text = clickerManager.clickCount.toString()
    }

    fun buyClicker()
    {
        clickerManager.buyAutoClicker()
        tvClicks.text = clickerManager.clickCount.toString()
    }
}
