package pt.isec.amov.contacts.ui.viewmodels

import androidx.lifecycle.ViewModel
import pt.isec.amov.contacts.model.Contact
import pt.isec.amov.contacts.model.ContactsList

class ContactsViewModel(
    val contactsList: ContactsList
) : ViewModel() {
    var currentContact: Contact? = null
    fun selectContact(contact: Contact) {
        currentContact = contact
    }
}