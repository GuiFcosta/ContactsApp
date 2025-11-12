package pt.isec.amov.contacts.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun EditScreen(
    name: MutableState<String>,
    email: MutableState<String>,
    phone: MutableState<String>,
    birthday: DatePickerState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
    ) {
        OutlinedTextField(
            value = name.value,
            isError = name.value.isEmpty(),
            label = {
                Text("Name:")
            },
            onValueChange = { newText ->
                name.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email.value,
            isError = email.value.isEmpty(),
            label = {
                Text("Email:")
            },
            onValueChange = { newText ->
                email.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = phone.value,
            isError = phone.value.isEmpty(),
            label = {
                Text("Phone:")
            },
            onValueChange = { newText ->
                phone.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text("Birthday:")
        DatePicker(
            state = birthday,
        )
    }
}