package de.hdmstuttgart.kotlinapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.hdmstuttgart.kotlinapp.BR
import de.hdmstuttgart.kotlinapp.R
import de.hdmstuttgart.kotlinapp.databinding.ActivityMainBinding
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

    fun buyClicker(v : View) {
        viewModel.addAutoClicker()
    }

}
