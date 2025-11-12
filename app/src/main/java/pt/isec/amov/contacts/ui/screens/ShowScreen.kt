package pt.isec.amov.contacts.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isec.amov.contacts.model.Contact
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ShowScreen(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    val dateFormatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Name:",
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        Text(
            text = contact.name,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Email:",
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        Text(
            text = contact.email,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Phone:",
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        Text(
            text = contact.phone,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Birthday:",
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
        contact.birthday?.let { birthday ->
            Text(
                text = dateFormatter.format(birthday),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 16.dp),
            )
        } ?: Text(
            text = "----.--.--",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp)
        )

    }
}