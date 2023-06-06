package com.zerodev.hiltexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.hiltexample.model.User
import com.zerodev.hiltexample.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _userList: MutableLiveData<List<User>> = MutableLiveData()

    fun setUsers() {
        viewModelScope.launch {
            repository.getUsers().flowOn(Dispatchers.IO).collect {
                _userList.postValue(it)
            }
        }
    }

    fun getUsers(): LiveData<List<User>> = _userList
}