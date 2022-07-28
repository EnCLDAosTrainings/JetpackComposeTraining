package com.example.netflicks.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.netflicks.R

val AllertaStencil = FontFamily(Font(R.font.allerta_stencil_regular))
val Roboto = FontFamily(Font(R.font.roboto_medium))

val Typo = Typography(
    // E.g. tutorial title, home app title
    h1 = TextStyle(
        fontFamily = AllertaStencil,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp,
    ),
    // E.g. tutorial sub title, home categories title, details title
    h2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = FadedTextApplication
    ),
    // E.g. movie details tickets
    h5 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    // E.g. home "view all". This will not change color based on phone mode (light/dark)
    h6 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = FadedTextApplication
    ),
    // E.g. movie details, description text
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    // E.g. home movie names
    body2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    // E.g. tutorial button
    button = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    )
)

//val TypographyHome = Typography(
//    h1 = TextStyle(
//        fontFamily = AllertaStencil,
//        fontWeight = FontWeight.Medium,
//        fontSize = 40.sp,
//        color = WhiteApplication
//    ),
//    h2 = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 25.sp,
//        color = WhiteApplication
//    ),
//    h3 = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 15.sp,
//        color = FadedTextApplication
//    ),
//    h4 = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 15.sp,
//        color = WhiteApplication
//    )
//)
