package com.zerodev.hiltexample

import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerodev.hiltexample.adapter.UserAdapter
import com.zerodev.hiltexample.databinding.ActivityMainBinding
import com.zerodev.hiltexample.utils.getConnectivity
import com.zerodev.hiltexample.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel by viewModels<UserViewModel>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()

        showData()
        setupRV()

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            showData()
        }

        binding.apply {
            ivShowHide.setOnClickListener {
                rvUser.visibility = if (rvUser.visibility == View.VISIBLE) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        }
    }

    private fun showData() {
        val connectivity = this.getConnectivity()
        if (connectivity != null) {
            if (connectivity.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                setupData()
            }
            if (connectivity.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                setupData()
            }
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupData() {
        userViewModel.setUsers()
        userViewModel.getUsers().observe(this) { users ->
            userAdapter.setData(users)
        }
    }

    private fun setupRV() {
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = userAdapter
        }
    }
}