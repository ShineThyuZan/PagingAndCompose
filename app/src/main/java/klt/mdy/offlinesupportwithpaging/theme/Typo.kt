package klt.mdy.offlinesupportwithpaging.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val offlineSupportTypography = Typography()

val Roboto = FontFamily.Default

val Typography.displayLarge: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = (-0.25).sp,
            lineHeight = 64.sp,
            fontSize = 57.sp
        )
    }
val Typography.displayMedium: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 52.sp,
            fontSize = 45.sp
        )
    }
val Typography.displaySmall: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 44.sp,
            fontSize = 36.sp
        )
    }

val Typography.headlineLarge: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 40.sp,
            fontSize = 32.sp
        )
    }

val Typography.headlineMedium: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 36.sp,
            fontSize = 28.sp
        )
    }

val Typography.headlineSmall: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 32.sp,
            fontSize = 24.sp
        )
    }

val Typography.titleLarge: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            lineHeight = 28.sp,
            fontSize = 22.sp
        )
    }

val Typography.titleMedium: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.15000000596046448.sp,
            lineHeight = 24.sp,
            fontSize = 16.sp
        )
    }

val Typography.titleSmall: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.10000000149011612.sp,
            lineHeight = 20.sp,
            fontSize = 14.sp
        )
    }

val Typography.labelLarge: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.10000000149011612.sp,
            lineHeight = 20.sp,
            fontSize = 14.sp
        )
    }

val Typography.labelMedium: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            lineHeight = 16.sp,
            fontSize = 12.sp
        )
    }

val Typography.labelSmall: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            lineHeight = 16.sp,
            fontSize = 11.sp
        )
    }

val Typography.bodyLarge: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.5.sp,
            lineHeight = 24.sp,
            fontSize = 16.sp
        )
    }

val Typography.bodyMedium: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.25.sp,
            lineHeight = 20.sp,
            fontSize = 14.sp
        )
    }

val Typography.bodySmall: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.4000000059604645.sp,
            lineHeight = 16.sp,
            fontSize = 12.sp
        )
    }





