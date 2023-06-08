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
        editorPref(context, "localPref")
            .putString("token", token)
            .apply()
    }

    fun saveEmail(email : String, context : Context){
        editorPref(context, "localPref")
            .putString("email", email)
            .apply()
    }

    fun savePassword(password : String, context : Context){
        editorPref(context, "localPref")
            .putString("password", password)
            .apply()
    }

    fun saveLoginUser(loginData : String, context : Context){
        editorPref(context, "localPref")
            .putString("loginData", loginData)
            .apply()
    }


    fun logout(context: Context){
        editorPref(context, "localPref")
            .remove("token")
            .remove("nama")
            .apply()
    }
}