package de.hdmstuttgart.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var clicks = 0
    var clickerCount = 0
    val clickerPrice = 10
    val threads = mutableListOf<Thread>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addClicks(0)
        mainButton.setOnClickListener { v -> addClicks(1)}
        buyButton.setOnClickListener { v -> buyClicker() }
    }

    fun addClicks(amount: Int)
    {
        clicks += amount
        tvClicks.post {
            tvClicks.setText(clicks.toString())
        }
    }

    fun buyClicker()
    {
        if (clicks >= clickerPrice)
        {
            clicks -= clickerPrice
            addClicker()
        }
    }

    fun addClicker()
    {
        clickerCount++
        // TODO: should maybe limit this to a max number of threads and then distribute among them
        val thread = Thread {
            while(true) {
                Thread.sleep(1000)
                addClicks(1)
            }
        }
        threads.add(threads.size, thread)
        thread.start()
    }
}
