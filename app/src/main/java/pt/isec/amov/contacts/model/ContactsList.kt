package pt.isec.amov.contacts.model

import java.io.*

class ContactsList : Serializable {
    private val contacts = mutableListOf<Contact>()
    fun addContact(contact: Contact) {
        contacts.add(contact)
    }
    fun getContacts(): List<Contact> {
        return contacts.toList()
    }

    fun save(destination: OutputStream) {
        try {
            ObjectOutputStream(destination).use { oos ->
                oos.writeObject(this)
            }
        } catch (_: Exception) { }
    }
    companion object {
        fun load(source : InputStream) : ContactsList? {
            try {
                ObjectInputStream(source).use { ois ->
                    return ois.readObject() as ContactsList
                }
            } catch (_: Exception){ return null }
        }
    }
}