package com.example.netflicks.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.netflicks.R

val AllertaStencil = FontFamily(Font(R.font.allerta_stencil_regular))

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = WhiteApplication
    ),
    h1 = TextStyle(
        fontFamily = AllertaStencil,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp,
        color = WhiteApplication
    ),
    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = WhiteApplication
    )
)

val TypographyHome = Typography(
    h1 = TextStyle(
        fontFamily = AllertaStencil,
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp,
        color = WhiteApplication
    ),
    h2  = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        color = WhiteApplication
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = FadedTextApplication
    ),
    h4 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = WhiteApplication
    )
)
