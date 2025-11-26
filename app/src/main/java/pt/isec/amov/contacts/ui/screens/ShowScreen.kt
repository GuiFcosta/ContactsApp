package pt.isec.amov.contacts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import pt.isec.amov.contacts.R
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
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = contact.picture ?: R.drawable.avatar_icon,
            contentScale = ContentScale.FillBounds,
            contentDescription = "Avatar Icon",
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )
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