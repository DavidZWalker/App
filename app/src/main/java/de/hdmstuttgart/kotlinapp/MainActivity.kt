package de.hdmstuttgart.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val clickerManager = ClickerManager()
    val collector = Thread {
        while(true) {
            addClicks(clickerManager.collectClicks())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addClicks(0)
        mainButton.setOnClickListener { v -> addClicks(1)}
        buyButton.setOnClickListener { v -> buyClicker() }
        collector.start()
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
