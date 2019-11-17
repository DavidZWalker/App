package de.hdmstuttgart.kotlinapp.util

import android.content.Context
import android.content.res.Resources

class ResourceProvider  {
    companion object {
        lateinit var context : Context

        fun getResources() : Resources {
            return context.resources
        }

        fun getResourceIdByName(name : String, type : String) : Int {
            return getResources().getIdentifier(name, type, context.packageName)
        }
    }
}