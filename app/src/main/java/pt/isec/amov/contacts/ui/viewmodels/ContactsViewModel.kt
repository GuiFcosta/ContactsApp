package pt.isec.amov.contacts.ui.viewmodels

import android.location.Location
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pt.isec.amov.contacts.model.Contact
import pt.isec.amov.contacts.model.ContactsList
import pt.isec.amov.contacts.ui.utils.location.FusedLocationHandler
import pt.isec.amov.contacts.ui.utils.location.LocationHandler
import java.util.Date
import java.util.Locale

class ContactsViewModel(
    val contactsList: ContactsList,
    private val locationHandler: LocationHandler
) : ViewModel() {
    var currentContact: Contact? = null
    val picture = mutableStateOf<String?>(null)
    val name = mutableStateOf("")
    val phone = mutableStateOf("")
    val email = mutableStateOf("")
    val birthdayDPState = DatePickerState(Locale.getDefault(), Date().time)

    fun createContact() {
        currentContact = null
        picture.value = null
        name.value = ""
        phone.value = ""
        email.value = ""
        birthdayDPState.selectedDateMillis = 0
    }

    fun selectContact(contact: Contact) {
        currentContact = contact
        picture.value = contact.picture
        name.value = contact.name
        phone.value = contact.phone
        email.value = contact.email
        birthdayDPState.selectedDateMillis = contact.birthday?.time ?: 0
    }

    fun saveContact(): Boolean {
        if (name.value.isEmpty() || email.value.isEmpty() || phone.value.isEmpty()) {
            return false
        }
        currentContact?.let { contact ->
            contact.picture = picture.value
            contact.name = name.value
            contact.phone = phone.value
            contact.email = email.value
            contact.birthday = birthdayDPState.selectedDateMillis?.let { Date(it) }
        } ?: contactsList.addContact(
            Contact(
                name.value,
                phone.value,
                email.value,
                birthdayDPState.selectedDateMillis?.let { Date(it) },
                picture.value,
            )
        )
        return true
    }

    var hasLocationPermission: Boolean = false
    private val currentLocation = mutableStateOf(Location(null))

    init {
        locationHandler.onLocation = { location ->
            currentLocation.value = location
        }
    }

    fun startLocationUpdates() {
        if (hasLocationPermission)
            locationHandler.startLocationUpdates()
    }

    fun stopLocationUpdates() {
        locationHandler.stopLocationUpdates()
    }

    fun storeCurrentLocation() {
        if (!hasLocationPermission || currentContact == null)
            return
        currentContact!!.addMeetingPoint(
            currentLocation.value.latitude,
            currentLocation.value.longitude,
            Date()
        )
    }
}