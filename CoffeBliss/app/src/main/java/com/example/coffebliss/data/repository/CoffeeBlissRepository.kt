package com.example.coffebliss.data.repository

import com.example.coffebliss.data.local.dao.MemberDao
import com.example.coffebliss.data.local.dao.TransactionDao
import com.example.coffebliss.data.local.entity.Member
import com.example.coffebliss.data.local.entity.Transaction
import kotlinx.coroutines.flow.Flow

class CoffeeBlissRepository(
    private val memberDao: MemberDao,
    private val transactionDao: TransactionDao
) {

    fun getAllMembers(): Flow<List<Member>> = memberDao.getAllMembers()

    fun getMemberById(id: Int): Flow<Member?> = memberDao.getMemberById(id)

    suspend fun insertMember(member: Member) = memberDao.insert(member)

    suspend fun updateMember(member: Member) = memberDao.update(member)

    suspend fun deleteMember(member: Member) = memberDao.delete(member)

    fun getTransactionsByMemberId(memberId: Int): Flow<List<Transaction>> =
        transactionDao.getTransactionsByMemberId(memberId)

    suspend fun insertTransaction(transaction: Transaction) = transactionDao.insert(transaction)
}
