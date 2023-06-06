package com.zerodev.hiltexample.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.zerodev.hiltexample.model.User
import com.zerodev.hiltexample.repository.UserRepository
import com.zerodev.hiltexample.utils.DataDummy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class UserViewModelTest {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userRepository: UserRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        userViewModel = UserViewModel(userRepository)
    }

    @Test
    fun get_users() {
        val data = flowOf(DataDummy.getUsers())
        val expectedOutput = MutableLiveData<Flow<List<User>>>()
        expectedOutput.value = data
        `when`(userRepository.getUsers()).thenReturn(data)

        userViewModel.getUsers()

        Assert.assertNotNull(userViewModel.getUsers())
    }
}