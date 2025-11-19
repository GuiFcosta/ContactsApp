package pt.isec.amov.contacts.model

import java.io.Serializable
import java.util.Date

class Contact (
    var name: String,
    var email: String,
    var phone: String,
    var birthday: Date?=null,
    var picture : String? = null
) : Serializable