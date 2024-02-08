package com.example.mobileexam.features.customer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.mobileexam.R
import com.example.mobileexam.features.customer.domain.models.User

@Composable
fun CustomerCard(
    modifier: Modifier = Modifier,
    customer: User,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .shadow(
                elevation = 75.dp,
                spotColor = Color(0x26262C48),
                ambientColor = Color(0x26262C48)
            )
            .background(Color.White)
            .padding(vertical = 8.dp, horizontal = 10.dp)
//            .background(Color.White),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                field(text = "Full Name", value = customer.name + " " + customer.lastName)
                Spacer(modifier = Modifier.height(8.dp))
                field(text = "Email", value = customer.email)
                Spacer(modifier = Modifier.height(8.dp))
                field(text = "Phone Number", value = customer.phoneNumber)
//                Spacer(modifier = Modifier.height(8.dp))
//                field(text = "Date of Birth", value = customer.birthDate)
            }

            Row {
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color(0xFFF6F6F9))
                        .padding(5.dp)
                        .clickable {
                            onEditClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.TwoTone.Edit, contentDescription = null, tint = Color(0xFF2A2B30))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.Black)
                        .padding(5.dp)
                        .clickable {
                            onDeleteClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.TwoTone.Delete, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun field(
    text: String,
    value: String
) {
    Row {
        Text(
            text = "$text :",
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                fontWeight = FontWeight(500),
                color = Color(0xFF2A2B30),
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                fontWeight = FontWeight(500),
                color = Color(0xFF6A6D7D),
            )
        )
    }
}