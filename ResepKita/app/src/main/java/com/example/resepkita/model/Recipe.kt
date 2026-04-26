package com.example.resepkita.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val time: String,
    val difficulty: String,
    val rating: Double,
    val author: String,
    val category: String = "Main Course",
    val isFavorite: Boolean = false,
    val isTrending: Boolean = false,
    val isRecommended: Boolean = false,
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList()
)

val dummyRecipes = listOf(
    Recipe(
        id = 1,
        title = "Ayam Bakar Madu Spesial Wangi",
        imageUrl = "https://images.unsplash.com/photo-1598514982205-f36b96d1e8d4?q=80&w=2070&auto=format&fit=crop",
        time = "25 mins",
        difficulty = "Medium",
        rating = 4.8,
        author = "Chef Wijaya",
        category = "Makan Siang",
        isFavorite = false,
        isTrending = true,
        ingredients = listOf(
            "1 ekor ayam kampung, potong 8 bagian",
            "4 sdm madu murni",
            "3 sdm kecap manis",
            "1 buah jeruk nipis, ambil airnya",
            "Bumbu halus: 8 butir bawang merah, 4 siung bawang putih"
        ),
        instructions = listOf(
            "Cuci bersih ayam, lumuri dengan perasan jeruk nipis dan garam. Diamkan 15 menit.",
            "Tumis bumbu halus hingga harum, masukkan ayam, aduk hingga berubah warna.",
            "Tambahkan air, madu, dan kecap. Ungkep dengan api kecil hingga bumbu meresap dan air menyusut.",
            "Panggang ayam di atas bara api atau teflon sambil sesekali diolesi sisa bumbu ungkepan.",
            "Angkat dan sajikan selagi hangat."
        )
    ),
    Recipe(
        id = 2,
        title = "Ayam Rica-Rica Pedas Manado",
        imageUrl = "https://images.unsplash.com/photo-1604908176997-125f25cc6f3d?q=80&w=1913&auto=format&fit=crop",
        time = "40 mins",
        difficulty = "Expert",
        rating = 4.9,
        author = "Mama Ina",
        category = "Makan Malam",
        isFavorite = true,
        isRecommended = true,
        ingredients = listOf(
            "1/2 ekor ayam potong sesuai selera",
            "Bumbu Rica: 15 buah cabai merah, 10 rawit merah",
            "2 batang serai, memarkan",
            "1 lembar daun pandan, iris kasar",
            "Segenggam daun kemangi"
        ),
        instructions = listOf(
            "Goreng ayam setengah matang, angkat dan tiriskan.",
            "Tumis bumbu rica yang ditumbuk kasar bersama serai, daun jeruk, dan pandan hingga wangi dan matang.",
            "Masukkan ayam yang sudah digoreng, aduk rata. Tambahkan sedikit air.",
            "Masak hingga bumbu meresap dan air asat.",
            "Sesaat sebelum diangkat, masukkan daun kemangi. Aduk cepat."
        )
    ),
    Recipe(
        id = 3,
        title = "Summer Harvest Buddha Bowl",
        imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=2070&auto=format&fit=crop",
        time = "15 mins",
        difficulty = "Easy",
        rating = 4.9,
        author = "Healthy Foodie",
        category = "Menu Diet",
        isFavorite = false,
        isTrending = true,
        ingredients = listOf(
            "1 mangkuk quinoa matang",
            "1/2 buah alpukat",
            "Sayuran segar secukupnya",
            "Saus tahini"
        ),
        instructions = listOf(
            "Siapkan mangkuk, tata quinoa sebagai alas.",
            "Potong sayuran dan alpukat, letakkan di atas quinoa.",
            "Siram dengan saus tahini.",
            "Sajikan segera."
        )
    ),
    Recipe(
        id = 4,
        title = "Miso Glazed Salmon",
        imageUrl = "https://images.unsplash.com/photo-1467003909585-2f8a72700288?q=80&w=1974&auto=format&fit=crop",
        time = "20 mins",
        difficulty = "Easy",
        rating = 4.7,
        author = "Seafood Lvr",
        category = "Makan Siang",
        isFavorite = true,
        isRecommended = true,
        ingredients = listOf(
            "2 potong fillet salmon",
            "2 sdm pasta miso",
            "1 sdm mirin",
            "1 sdm kecap asin"
        ),
        instructions = listOf(
            "Campurkan pasta miso, mirin, dan kecap asin.",
            "Lumuri fillet salmon dengan campuran tersebut.",
            "Panggang dalam oven selama 12-15 menit.",
            "Sajikan dengan nasi hangat."
        )
    ),
    Recipe(
        id = 5,
        title = "Creamy Garlic Herb Pasta",
        imageUrl = "https://images.unsplash.com/photo-1473093295043-cdd812d0e601?q=80&w=2070&auto=format&fit=crop",
        time = "25 mins",
        difficulty = "Medium",
        rating = 4.8,
        author = "Chef Wijaya",
        category = "Makan Malam",
        isFavorite = true,
        ingredients = listOf(
            "250g pasta penne",
            "3 siung bawang putih, cincang",
            "200ml cooking cream",
            "Garam dan lada secukupnya"
        ),
        instructions = listOf(
            "Rebus pasta hingga al dente.",
            "Tumis bawang putih hingga harum.",
            "Masukkan cream, aduk hingga mendidih. Tambahkan garam dan lada.",
            "Masukkan pasta, aduk rata dengan saus.",
            "Sajikan dengan taburan keju parmesan."
        )
    ),
    Recipe(
        id = 6,
        title = "Sate Ayam Bumbu Kacang",
        imageUrl = "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?q=80&w=1974&auto=format&fit=crop",
        time = "30 mins",
        difficulty = "Medium",
        rating = 4.9,
        author = "Sate Master",
        category = "Makan Siang",
        isFavorite = true,
        ingredients = listOf(
            "500g daging ayam fillet",
            "Tusuk sate secukupnya",
            "250g kacang tanah goreng, haluskan",
            "Kecap manis"
        ),
        instructions = listOf(
            "Potong dadu ayam, tusuk dengan tusuk sate.",
            "Buat bumbu kacang dengan merebus kacang halus, kecap, dan air secukupnya.",
            "Lumuri sate dengan sedikit bumbu kacang sebelum dipanggang.",
            "Panggang sate hingga matang.",
            "Sajikan dengan siraman bumbu kacang pekat dan lontong."
        )
    ),
    Recipe(
        id = 7,
        title = "Ayam Goreng Kalasan Asli",
        imageUrl = "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?q=80&w=2070&auto=format&fit=crop",
        time = "20 mins",
        difficulty = "Easy",
        rating = 4.7,
        author = "Dapur Umami",
        category = "Makan Siang",
        isFavorite = false,
        ingredients = listOf(
            "1 ekor ayam, potong sesuai selera",
            "Air kelapa untuk mengungkep",
            "Bumbu halus: Bawang merah, bawang putih, ketumbar",
            "Gula merah secukupnya"
        ),
        instructions = listOf(
            "Ungkep ayam bersama bumbu halus, air kelapa, dan gula merah.",
            "Tunggu hingga air asat dan ayam empuk.",
            "Goreng ayam dalam minyak panas hingga kecoklatan.",
            "Angkat dan sajikan dengan sambal bajak."
        )
    )
)
