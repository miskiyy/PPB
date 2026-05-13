package com.example.marketsiswa.ui.typography

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.marketsiswa.R

private val googleFontsProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

val HeadlineFontFamily = FontFamily(
    Font(GoogleFont("Plus Jakarta Sans"), googleFontsProvider, weight = FontWeight.Normal),
    Font(GoogleFont("Plus Jakarta Sans"), googleFontsProvider, weight = FontWeight.Medium),
    Font(GoogleFont("Plus Jakarta Sans"), googleFontsProvider, weight = FontWeight.SemiBold),
    Font(GoogleFont("Plus Jakarta Sans"), googleFontsProvider, weight = FontWeight.Bold),
    Font(GoogleFont("Plus Jakarta Sans"), googleFontsProvider, weight = FontWeight.ExtraBold),
)

val BodyFontFamily = FontFamily(
    Font(GoogleFont("Source Sans 3"), googleFontsProvider, weight = FontWeight.Normal),
    Font(GoogleFont("Source Sans 3"), googleFontsProvider, weight = FontWeight.SemiBold),
)
