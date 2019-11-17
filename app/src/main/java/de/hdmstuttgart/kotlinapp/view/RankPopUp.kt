package de.hdmstuttgart.kotlinapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import de.hdmstuttgart.kotlinapp.R
import de.hdmstuttgart.kotlinapp.databinding.RankPopupBinding
import de.hdmstuttgart.kotlinapp.viewmodel.RankViewModel

class RankPopUp(private var viewModel: RankViewModel? = null) : DialogFragment() {

    private lateinit var binding : RankPopupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.rank_popup, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(1000, 1000)
    }
}