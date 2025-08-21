package br.com.fiap.imcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Altera a cor dos ícones da barra de status (claro ou escuro)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
            false // false = ícones claros
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IMCScreen()
                }
            }
        }
    }
}

fun Calcular(peso: String, altura: String): Double {

    var pesoConvertido: Double = peso.toDouble()
    var alturaConvertida: Double = altura.toDouble()
    var resultadoImc = (pesoConvertido / (alturaConvertida * alturaConvertida))
    return resultadoImc
}

@Composable
fun IMCScreen() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultadoImc by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // ---- header ---------
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(colorResource(R.color.vermelho_fiap))
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(R.drawable.imc),
                    contentDescription = "Logo do IMC",
                    modifier = Modifier
                        .size(width = 200.dp, height = 200.dp)
                        .clip(shape = RectangleShape),
                    contentScale = ContentScale.FillHeight //obriga a imagem ocupar todo o espaço da altura (esticar),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "CALCULADORA IMC",
                    color = colorResource(R.color.cor_do_texto),
                    fontSize = 18.sp
                )
                // conteúdo do cabeçalho
            }

            // --- formulário
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .height(420.dp)
                        .fillMaxWidth()
                        .offset(y = -15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(
                            255,
                            255,
                            255,
                            255
                        )
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Black),
                    shape = RoundedCornerShape(topStart = 40.dp, bottomEnd = 40.dp)

                ) {
                    Column(
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 32.dp
                        )
                    ) {
                        Text(
                            text = "Seus dados",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.vermelho_fiap),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = "Seu peso",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.vermelho_fiap)
                        )
                        OutlinedTextField(
                            value = peso,
                            onValueChange = { it ->
                                val filtro = it.filter { it.isDigit() || it == '.' || it == ',' }
                                peso = filtro.replace(',', '.')
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "Seu peso em kg"
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Sua altura",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.vermelho_fiap)
                        )
                        OutlinedTextField(
                            value = altura,
                            onValueChange = { it ->
                                val filtro = it.filter { it.isDigit() || it == '.' || it == ',' }
                                altura = filtro.replace(',', '.')
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "Sua altura em cm"
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.vermelho_fiap)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                if (altura != "" && peso != "") {
                                    resultadoImc =
                                        (Calcular(peso, altura.replace(",", ".")).toString())
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(
                                    id = R.color.vermelho_fiap
                                )
                            )
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                peso = ""
                                altura = ""
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(Color(192, 176, 42, 255))


                        ) {
                            Text(
                                text = "LIMPAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }


                        // campos do formulário
                    }
                }
                // -- Card Resultado (ainda vazio)
                var resultadoImcDouble = resultadoImc.toDoubleOrNull()

                if (resultadoImcDouble != null && resultadoImcDouble >= 18.5 && resultadoImcDouble <= 24.9) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 32.dp, vertical = 24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF74FF00)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        //border = BorderStroke(width = 1.dp, Color(0xffed145b))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .fillMaxSize()
                        ) {
                            Column() {
                                Text(
                                    text = "Resultado",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Peso Ideal.",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }
                            Text(
                                text = "%.2f".format(resultadoImcDouble),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 20.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                } else if (resultadoImcDouble != null && resultadoImcDouble < 18.5) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 32.dp, vertical = 24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF0000)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        //border = BorderStroke(width = 1.dp, Color(0xffed145b))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .fillMaxSize()
                        ) {
                            Column() {
                                Text(
                                    text = "Resultado",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Magreza",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }
                            Text(
                                text = "%.2f".format(resultadoImcDouble),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 20.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }

                } else if (resultadoImcDouble != null && resultadoImcDouble >= 25 && resultadoImcDouble <= 29.9) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 32.dp, vertical = 24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC900)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        //border = BorderStroke(width = 1.dp, Color(0xffed145b))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .fillMaxSize()
                        ) {
                            Column() {
                                Text(
                                    text = "Resultado",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Sobrepeso",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }
                            Text(
                                text = "%.2f".format(resultadoImcDouble),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 20.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }

                } else if (resultadoImcDouble != null && resultadoImcDouble > 30 && resultadoImcDouble <= 39.9) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 32.dp, vertical = 24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF6F00)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        //border = BorderStroke(width = 1.dp, Color(0xffed145b))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .fillMaxSize()
                        ) {
                            Column() {
                                Text(
                                    text = "Resultado",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Obesidade",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }
                            Text(
                                text = "%.2f".format(resultadoImcDouble),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 25.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }

                } else if (resultadoImcDouble != null && resultadoImcDouble > 40) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 32.dp, vertical = 24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF0800)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        //border = BorderStroke(width = 1.dp, Color(0xffed145b))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxSize()
                        ) {
                            Column() {
                                Text(
                                    text = "Resultado",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Obesidade Grave",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 20.sp
                                )
                            }
                            Text(
                                text = "%.2f".format(resultadoImcDouble),
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 25.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }
        }
//    }
//}
    }
}
