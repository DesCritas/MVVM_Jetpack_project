package com.descritas.mvvm_jetpack_project

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.descritas.data.User
import com.descritas.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, vm: HomeScreenViewModel) {
    Scaffold(
        topBar = {
            TopAppBar( title = { Text(text = "Users")}
            )
        },
        content = {
            Column {
                val users by vm.users.collectAsState()
                users.forEach { user ->
                    ClickableText(text = AnnotatedString(user.name), Modifier.padding(all = 16.dp),
                        onClick = {
                            navController.navigate("users/${user.id}")
                        })
                }
            }
        }
    )
}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {
    private val _users = MutableStateFlow(userRepository.getUsers())
    val users: StateFlow<List<User>> = _users
}