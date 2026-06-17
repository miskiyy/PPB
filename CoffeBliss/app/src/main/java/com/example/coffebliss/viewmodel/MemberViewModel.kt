package com.example.coffebliss.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coffebliss.data.local.entity.Member
import com.example.coffebliss.data.repository.CoffeeBlissRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class AddMemberFormState(
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false
)

class MemberViewModel(private val repository: CoffeeBlissRepository) : ViewModel() {

    val members: StateFlow<List<Member>> = repository.getAllMembers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _formState = MutableStateFlow(AddMemberFormState())
    val formState: StateFlow<AddMemberFormState> = _formState.asStateFlow()

    fun addMember(name: String, email: String, phone: String) {
        val nameError = if (name.isBlank()) "Name cannot be empty" else null
        val emailError = when {
            email.isBlank() -> "Email cannot be empty"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email format"
            else -> null
        }
        val phoneError = if (phone.isBlank()) "Phone number cannot be empty" else null

        if (nameError != null || emailError != null || phoneError != null) {
            _formState.value = AddMemberFormState(
                nameError = nameError,
                emailError = emailError,
                phoneError = phoneError
            )
            return
        }

        _formState.value = AddMemberFormState(isLoading = true)
        viewModelScope.launch {
            repository.insertMember(
                Member(name = name.trim(), email = email.trim(), phone = phone.trim())
            )
            _formState.value = AddMemberFormState(isSuccess = true)
        }
    }

    fun resetFormState() {
        _formState.value = AddMemberFormState()
    }

    companion object {
        fun factory(repository: CoffeeBlissRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer { MemberViewModel(repository) }
        }
    }
}
