package com.example.network_db.screens.detail_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.network_db.core.BaseFragment
import com.example.network_db.databinding.FragmentUserDetailBinding
import com.example.network_db.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {
    private val args by navArgs<UserDetailFragmentArgs>()
    @Inject
    lateinit var factory: UserDetailViewModel.Factory

    private val viewModel by viewModels<UserDetailViewModel> {
        UserDetailViewModel.provideUserDetailViewModelFactory(factory, args.uuid)
    }


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentUserDetailBinding {
        return FragmentUserDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    setInfo(state)
                }
            }
        }
    }

    private fun setInfo(state: DetailState) {
        with(binding) {
            if (state.user != null) {
                with(state.user) {
                    tvGender.text = getString(com.example.network_db.R.string.gender, gender)
                    tvName.text =
                        getString(com.example.network_db.R.string.name, title, firstName, lastName)
                    tvAddress.text = getString(
                        com.example.network_db.R.string.address, city, this.state, country, postCode
                    )
                    tvMail.text = getString(com.example.network_db.R.string.mail, email)
                    ivAvatar.loadImage(picture)
                }
            }
        }
    }
}
