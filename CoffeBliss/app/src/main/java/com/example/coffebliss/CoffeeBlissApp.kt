package com.example.coffebliss

import android.app.Application
import com.example.coffebliss.data.local.database.CoffeeBlissDatabase
import com.example.coffebliss.data.repository.CoffeeBlissRepository

class CoffeeBlissApp : Application() {

    val database by lazy { CoffeeBlissDatabase.getDatabase(this) }

    val repository by lazy {
        CoffeeBlissRepository(
            memberDao = database.memberDao(),
            transactionDao = database.transactionDao()
        )
    }
}
