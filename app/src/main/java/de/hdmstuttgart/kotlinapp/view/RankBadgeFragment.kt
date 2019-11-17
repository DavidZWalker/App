package de.hdmstuttgart.kotlinapp.view

        import android.os.Bundle
        import androidx.fragment.app.Fragment
                import android.view.LayoutInflater
                import android.view.View
                import android.view.ViewGroup
        import androidx.databinding.DataBindingUtil
                import de.hdmstuttgart.kotlinapp.R
        import de.hdmstuttgart.kotlinapp.databinding.RankBadgeFragmentBinding
                import de.hdmstuttgart.kotlinapp.viewmodel.RankViewModel


class RankBadgeFragment : Fragment() {

            private lateinit var viewModel : RankViewModel
            private lateinit var binding: RankBadgeFragmentBinding

            override fun onCreateView(
                inflater: LayoutInflater, container: ViewGroup?,
                savedInstanceState: Bundle?) : View? {
                binding = DataBindingUtil.inflate(inflater, R.layout.rank_badge_fragment, container, false)
                viewModel = RankViewModel()
                binding.viewModel = viewModel
                binding.root.setOnClickListener { openRankPopUp() }

                return binding.root
            }

            private fun openRankPopUp() {
                fragmentManager?.let { RankPopUp(viewModel).show(it, "HALLO") }
            }


}
