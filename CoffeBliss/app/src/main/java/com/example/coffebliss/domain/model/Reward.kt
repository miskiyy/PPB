package com.example.coffebliss.domain.model

data class Reward(
    val name: String,
    val pointCost: Int,
    val description: String
)

val availableRewards = listOf(
    Reward(name = "Espresso", pointCost = 50, description = "A bold, rich shot of espresso"),
    Reward(name = "Cappuccino", pointCost = 100, description = "Creamy cappuccino with steamed milk"),
    Reward(name = "Latte Gratis", pointCost = 150, description = "Free latte, our signature blend")
)
