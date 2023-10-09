package com.example.network_db.screens.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network_db.core.BaseFragment
import com.example.network_db.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersListFragment : BaseFragment<FragmentUserListBinding>() {
    private val viewModel by viewModels<UserViewModel>()
    private val adapter by lazy {
        UserListAdapter {
            val action = UsersListFragmentDirections.actionUsersListFragmentToUserDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentUserListBinding {
        return FragmentUserListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvRecycler.layoutManager = LinearLayoutManager(requireContext())
            rvRecycler.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pager.collect { users ->
                    adapter.submitData(users)
                }
            }
        }
    }
}