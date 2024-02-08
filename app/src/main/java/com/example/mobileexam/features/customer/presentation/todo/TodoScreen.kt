package com.example.mobileexam.features.customer.presentation.todo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileexam.R
import com.example.mobileexam.core.navigation.Screen
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(navController: NavController) {
    val viewModel = getViewModel<TodoViewModel>()
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(horizontal = 20.dp),
        contentColor = Color.White
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color(0xFF2A2B30)
                )
            }

            Text(
                text = "Todos",
                modifier = Modifier
                    .padding(top = 20.dp),
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = FontFamily(Font(R.font.inter_bold)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2B30),
                )
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.todos.count()) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(12.dp))
                            .shadow(
                                elevation = 75.dp,
                                spotColor = Color(0x26262C48),
                                ambientColor = Color(0x26262C48)
                            )
                            .background(Color.White)
                            .padding(vertical = 8.dp, horizontal = 10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                if (state.todos[index].completed) Icons.TwoTone.Check else Icons.TwoTone.Close,
                                contentDescription = null,
                                tint = if (state.todos[index].completed) Color.Green else Color(0xFFE50914)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = state.todos[index].todo,
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF2A2B30),
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}