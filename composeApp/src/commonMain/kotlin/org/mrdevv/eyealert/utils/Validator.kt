package org.mrdevv.eyealert.utils

class Validator {
    companion object{
        fun validateEmail(email: String): Boolean{
            val emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
            return email.matches(emailPattern.toRegex())
        }
    }
}