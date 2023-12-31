package com.example.network_db.screens.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.network_db.databinding.ItemUserBinding
import com.example.network_db.screens.entity.User

class UserListAdapter(private val onUserClick: ((uuid: String) -> Unit)) : PagingDataAdapter<User, UserListAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onUserClick)
        }
    }

    class VH(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, onUserClick: ((uuid: String) -> Unit)?) {
            with(binding) {
                tvName.text = user.firstName
                root.setOnClickListener {
                    onUserClick?.invoke(user.uuid)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldUser: User, newUser: User): Boolean =
            oldUser.firstName == newUser.firstName

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            oldItem == newItem
    }
}