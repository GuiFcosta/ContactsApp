package pt.isec.amov.contacts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import pt.isec.amov.contacts.ContactsApp
import pt.isec.amov.contacts.ui.screens.MainScreen
import pt.isec.amov.contacts.ui.theme.ContactsTheme
import pt.isec.amov.contacts.ui.viewmodels.ContactsViewModel
import pt.isec.amov.contacts.ui.viewmodels.ContactsViewModelFactory

class MainActivity : ComponentActivity() {
    private val app by lazy { application as ContactsApp }
    private val viewModel: ContactsViewModel
            by viewModels { ContactsViewModelFactory(app.contactsList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
    }
    override fun onPause() {
        super.onPause()
        app.saveData()
    }
}