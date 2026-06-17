package com.example.coffebliss.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coffebliss.data.local.entity.Member
import com.example.coffebliss.data.local.entity.Transaction
import com.example.coffebliss.data.repository.CoffeeBlissRepository
import com.example.coffebliss.domain.model.Reward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class TransactionResult {
    object Idle : TransactionResult()
    data class Success(val pointsEarned: Int, val totalPoints: Int) : TransactionResult()
    data class Error(val message: String) : TransactionResult()
}

sealed class RewardResult {
    object Idle : RewardResult()
    data class Success(val rewardName: String, val remainingPoints: Int) : RewardResult()
    object InsufficientPoints : RewardResult()
}

class MemberDetailViewModel(
    private val repository: CoffeeBlissRepository,
    val memberId: Int
) : ViewModel() {

    val member: StateFlow<Member?> = repository.getMemberById(memberId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val transactions: StateFlow<List<Transaction>> = repository.getTransactionsByMemberId(memberId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _transactionResult = MutableStateFlow<TransactionResult>(TransactionResult.Idle)
    val transactionResult: StateFlow<TransactionResult> = _transactionResult.asStateFlow()

    private val _rewardResult = MutableStateFlow<RewardResult>(RewardResult.Idle)
    val rewardResult: StateFlow<RewardResult> = _rewardResult.asStateFlow()

    fun addTransaction(amountInput: String) {
        val amount = amountInput.replace(",", "").replace(".", "").toDoubleOrNull()
        if (amount == null || amount <= 0) {
            _transactionResult.value = TransactionResult.Error("Please enter a valid amount")
            return
        }
        val currentMember = member.value
        if (currentMember == null) {
            _transactionResult.value = TransactionResult.Error("Member data not found")
            return
        }
        val pointEarned = (amount / 10_000).toInt()
        if (pointEarned == 0) {
            _transactionResult.value = TransactionResult.Error("Minimum purchase is Rp 10.000")
            return
        }
        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val today = dateFormat.format(Date())
            repository.insertTransaction(
                Transaction(
                    memberId = memberId,
                    amount = amount,
                    pointEarned = pointEarned,
                    date = today
                )
            )
            val newTotal = currentMember.points + pointEarned
            repository.updateMember(currentMember.copy(points = newTotal))
            _transactionResult.value = TransactionResult.Success(
                pointsEarned = pointEarned,
                totalPoints = newTotal
            )
        }
    }

    fun redeemReward(reward: Reward) {
        val currentMember = member.value ?: return
        if (currentMember.points < reward.pointCost) {
            _rewardResult.value = RewardResult.InsufficientPoints
            return
        }
        viewModelScope.launch {
            val remaining = currentMember.points - reward.pointCost
            repository.updateMember(currentMember.copy(points = remaining))
            _rewardResult.value = RewardResult.Success(
                rewardName = reward.name,
                remainingPoints = remaining
            )
        }
    }

    fun resetTransactionResult() {
        _transactionResult.value = TransactionResult.Idle
    }

    fun resetRewardResult() {
        _rewardResult.value = RewardResult.Idle
    }

    companion object {
        fun factory(repository: CoffeeBlissRepository, memberId: Int): ViewModelProvider.Factory =
            viewModelFactory {
                initializer { MemberDetailViewModel(repository, memberId) }
            }
    }
}

fun getMemberLevel(points: Int): String = when {
    points < 50 -> "Bronze"
    points < 100 -> "Silver"
    points < 150 -> "Gold"
    else -> "Platinum"
}

fun formatMemberId(id: Int): String = "MBR%05d".format(id)

fun formatCurrency(amount: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale("id", "ID"))
    return "Rp ${formatter.format(amount.toLong())}"
}
