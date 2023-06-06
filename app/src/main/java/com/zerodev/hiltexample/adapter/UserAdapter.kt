package com.zerodev.hiltexample.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zerodev.hiltexample.databinding.UserItemBinding
import com.zerodev.hiltexample.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val userList = ArrayList<User>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class ViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvName.text = user.login
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .into(imgUser)
            }
        }
    }
}