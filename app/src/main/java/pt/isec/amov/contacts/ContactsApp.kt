package pt.isec.amov.contacts

import android.app.Application
import com.google.android.gms.location.LocationServices
import pt.isec.amov.contacts.model.Contact
import pt.isec.amov.contacts.model.ContactsList
import pt.isec.amov.contacts.ui.utils.location.LocationHandler
import pt.isec.amov.contacts.ui.utils.location.FusedLocationHandler

class ContactsApp : Application() {
    companion object {
        private const val DATAFILE = "contacts.bin"
    }
    private val _contactsList by lazy {
        try {
            openFileInput(DATAFILE)?.let { srcFile -> ContactsList.load(srcFile) }
        } catch (_: Exception) {
            null
        } ?: ContactsList()
    }
    val contactsList get() = _contactsList

    val locationHandler : LocationHandler by lazy {
        val locationProvider = LocationServices
            .getFusedLocationProviderClient(this)
        FusedLocationHandler(locationProvider)
    }

    override fun onCreate(){
        super.onCreate()

        if (_contactsList.getContacts().isEmpty()) { // Adiciona contatos apenas se a lista estiver vazia
            _contactsList.addContact(Contact("Ana Silva", "ana.silva@email.com", "+351912345678"))
            _contactsList.addContact(Contact("Bruno Costa", "bruno.costa@email.com", "+351923456789"))
            _contactsList.addContact(Contact("Carlos Dias", "carlos.dias@email.com", "+351934567890"))
            _contactsList.addContact(Contact("Diana Melo", "diana.melo@email.com", "+351965432109"))
        }
    }
    fun saveData() {
        try {
            contactsList.save(openFileOutput(DATAFILE, MODE_PRIVATE))
        } catch (_: Exception) {}
    }
}