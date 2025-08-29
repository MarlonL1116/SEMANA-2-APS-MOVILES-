package com.example.sem_02ejercicio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sem_02ejercicio.ui.theme.ActividadTheme // Asegúrate de tener este Theme en tu proyecto

// 🔹 Enum para distinguir tipo de transacción
enum class TipoTransaccion {
    GASTO, INGRESO
}

// 🔹 Clase de datos para la transacción
data class Transaccion(
    val titulo: String,
    val fecha: String,
    val monto: Double,
    val tipo: TipoTransaccion
)

// 🔹 Colores personalizados
val AzulGasto = Color(0xFF1976D2)
val VerdeIngreso = Color(0xFF388E3C)

// 🔹 Composable para mostrar una sola transacción
@Composable
fun ItemTransaccion(transaccion: Transaccion) {
    val colorMonto = when (transaccion.tipo) {
        TipoTransaccion.GASTO -> AzulGasto
        TipoTransaccion.INGRESO -> VerdeIngreso
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = transaccion.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black   // 🔹 siempre negro
                )
                Text(
                    text = transaccion.fecha,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray    // 🔹 siempre gris
                )
            }
            Text(
                text = "S/ ${"%.2f".format(transaccion.monto)}",
                style = MaterialTheme.typography.titleMedium,
                color = colorMonto      // 🔹 solo el número cambia de color
            )
        }
    }
}


// 🔹 Composable para mostrar lista completa
@Composable
fun ListaTransacciones(transacciones: List<Transaccion>) {
    LazyColumn {
        items(transacciones) { transaccion ->
            ItemTransaccion(transaccion)
        }
    }
}

// 🔹 Pantalla principal con datos de prueba
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val transacciones = listOf(
        Transaccion("Pago de arrendamiento", "29/08/2025", 1100.0, TipoTransaccion.INGRESO),
        Transaccion("Botica", "28/08/2025", 35.0, TipoTransaccion.GASTO),
        Transaccion("Venta de servicio", "27/08/2025", 400.0, TipoTransaccion.INGRESO),
        Transaccion("Cena", "22/08/2025", 70.0, TipoTransaccion.GASTO),

    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Transacciones")
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            ListaTransacciones(transacciones)
        }
    }
}

// 🔹 Actividad principal
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActividadTheme { // Usa tu theme personalizado aquí
                MainScreen()
            }
        }
    }
}

// 🔹 Preview para Compose
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ActividadTheme {
        MainScreen()
    }
}