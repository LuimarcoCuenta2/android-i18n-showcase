package com.example.android_unam

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.LocaleListCompat
import com.example.android_unam.ui.theme.AndroidunamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar español por defecto si no hay idioma previo

        if (AppCompatDelegate.getApplicationLocales().isEmpty) {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags("es")
            )
        }
        enableEdgeToEdge()
        setContent {
            AndroidunamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WelcomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // 1. Fondo Nine-Patch renderizado nativamente con AndroidView
        AndroidView(
            factory = { context ->
                ImageView(context).apply {
                    setBackgroundResource(R.drawable.fondo_marcianito)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // 2. Elementos distribuidos encima del fondo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Espacio superior para el título "Bienvenido"
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = stringResource(id = R.string.welcome_title),
                fontSize = 42.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            // Botón central con borde blanco
            OutlinedButton(
                onClick = { /* Acción al entrar */ },
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_message),
                    fontSize = 18.sp
                )
            }

            // Espacio inferior para dejar respirar al marcianito en las nubes
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}