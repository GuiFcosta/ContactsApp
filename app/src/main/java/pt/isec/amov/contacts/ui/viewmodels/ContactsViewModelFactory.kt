package pt.isec.amov.contacts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.isec.amov.contacts.model.ContactsList
import pt.isec.amov.contacts.ui.utils.location.LocationHandler

class ContactsViewModelFactory(
    private val contactsList : ContactsList,
    private val locationHandler: LocationHandler
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(contactsList, locationHandler) as T
    }
}