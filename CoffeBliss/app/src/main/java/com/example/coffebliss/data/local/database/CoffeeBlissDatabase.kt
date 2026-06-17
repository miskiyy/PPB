package com.example.coffebliss.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffebliss.data.local.dao.MemberDao
import com.example.coffebliss.data.local.dao.TransactionDao
import com.example.coffebliss.data.local.entity.Member
import com.example.coffebliss.data.local.entity.Transaction

@Database(
    entities = [Member::class, Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class CoffeeBlissDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: CoffeeBlissDatabase? = null

        fun getDatabase(context: Context): CoffeeBlissDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoffeeBlissDatabase::class.java,
                    "coffee_bliss_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
