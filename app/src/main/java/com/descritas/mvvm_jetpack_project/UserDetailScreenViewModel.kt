package com.descritas.mvvm_jetpack_project

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.descritas.data.User
import com.descritas.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserDetailScreen(navController: NavController, vm: UserDetailScreenViewModel, userId: Long) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "User detail")
                }
            )
        },
        content = {
            Column {
                vm.load(userId = userId)
                val user by vm.user.collectAsState()
                Column(Modifier.padding(all = 16.dp)) {
                    Text(text = "Hello, I'm ${user?.name}")
                    Text(text = "My email is ${user?.email}")
                }
            }
        }
    )

}

@HiltViewModel
class UserDetailScreenViewModel @Inject constructor(val userRepository: UserRepository) :
    ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun load(userId: Long) {
        _user.value = userRepository.getUser(id = userId)
    }
}