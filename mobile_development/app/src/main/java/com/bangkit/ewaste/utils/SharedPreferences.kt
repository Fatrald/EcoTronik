package com.bangkit.ewaste.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferences {
    fun initPreference(context: Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    private fun editorPref(context: Context, name:String): SharedPreferences.Editor{
        var initPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return initPref.edit()
    }


    fun saveToken(token : String, context: Context){
        editorPref(context, "SignIn")
            .putString("token", token)
            .apply()
    }

    fun saveName( name : String, context: Context){
        editorPref(context, "name")
            .putString("token", name)
            .apply()
    }


    fun logout(context: Context){
        editorPref(context, "SignIn")
            .remove("token")
            .remove("status")
            .apply()
    }
}