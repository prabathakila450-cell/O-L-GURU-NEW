package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.EyeCareThemeMode

private val DarkColorScheme = darkColorScheme(
  primary = Purple80,
  secondary = PurpleGrey80,
  tertiary = Pink80,
  background = Color(0xFF0F172A),
  surface = Color(0xFF1E293B)
)

private val LightColorScheme = lightColorScheme(
  primary = PrimaryBlue,
  secondary = PurpleGrey40,
  tertiary = Pink40,
  background = Color(0xFFF8FAFC),
  surface = Color.White
)

@Composable
fun MyApplicationTheme(
  eyeCareMode: EyeCareThemeMode = EyeCareThemeMode.LIGHT,
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit
) {
  val colorScheme = when (eyeCareMode) {
    EyeCareThemeMode.DARK, EyeCareThemeMode.NIGHT, EyeCareThemeMode.OLED -> DarkColorScheme
    EyeCareThemeMode.SEPIA -> lightColorScheme(
      primary = Color(0xFF854D0E),
      background = Color(0xFFFEF9C3),
      surface = Color(0xFFFEF08A)
    )
    EyeCareThemeMode.AMBER -> lightColorScheme(
      primary = Color(0xFFB45309),
      background = Color(0xFFFFFBEB),
      surface = Color(0xFFFEF3C7)
    )
    EyeCareThemeMode.BLUE_LIGHT, EyeCareThemeMode.BLUE_LIGHT_SHIELD -> lightColorScheme(
      primary = Color(0xFF1D4ED8),
      background = Color(0xFFFFFDF7),
      surface = Color.White
    )
    else -> if (darkTheme) DarkColorScheme else LightColorScheme
  }

  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as? Activity)?.window
      if (window != null) {
        window.statusBarColor = colorScheme.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
      }
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
