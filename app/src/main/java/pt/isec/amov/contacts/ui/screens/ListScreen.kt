package pt.isec.amov.contacts.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import pt.isec.amov.contacts.R
import pt.isec.amov.contacts.model.Contact
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ListScreen(
    contacts : List<Contact>,
    showExpanded : Boolean = false,
    onSelectContact : (Contact) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(
            contacts,
            key = { contact -> contact.hashCode() }
        ) {
            contact ->
            ContactCard(
                contact = contact,
                showExpanded = showExpanded,
                onSelectContact = onSelectContact,
            )
        }
    }
}
@Composable
fun ContactCard(
    contact: Contact, showExpanded: Boolean,
    onSelectContact: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    Card(
        modifier = modifier.fillMaxWidth().padding(8.dp), elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(255, 224, 192)),
        onClick = { onSelectContact(contact) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    model = contact.picture ?: R.drawable.avatar_icon,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Contact Image",
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                )
                Text(text = contact.name, fontSize = 26.sp)
            }
            if (showExpanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,

                ) {
                    Text(text = contact.email, fontSize = 18.sp)
                    Text(text = contact.phone, fontSize = 18.sp)
                    contact.birthday?.let { birthday ->
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Left,
                            text = formatter.format(birthday),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}