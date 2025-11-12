package pt.isec.amov.contacts

import android.app.Application
import pt.isec.amov.contacts.model.Contact
import pt.isec.amov.contacts.model.ContactsList

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

    override fun onCreate(){
        super.onCreate()

        if (_contactsList.getContacts().isEmpty()) { // Adiciona contatos apenas se a lista estiver vazia
            _contactsList.addContact(Contact("Ana Silva", "+351912345678", "ana.silva@email.com"))
            _contactsList.addContact(Contact("Bruno Costa", "+351923456789", "bruno.costa@email.com"))
            _contactsList.addContact(Contact("Carlos Dias", "+351934567890", "carlos.dias@email.com"))
            _contactsList.addContact(Contact("Diana Melo", "+351965432109", "diana.melo@email.com"))
        }
    }
    fun saveData() {
        try {
            contactsList.save(openFileOutput(DATAFILE, MODE_PRIVATE))
        } catch (_: Exception) {}
    }
}