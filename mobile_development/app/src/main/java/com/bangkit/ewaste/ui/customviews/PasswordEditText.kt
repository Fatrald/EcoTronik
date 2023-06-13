package com.bangkit.ewaste.ui.customviews

import android.content.Context
import android.graphics.Canvas
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import com.bangkit.ewaste.R


class PasswordEditText : AppCompatEditText {
    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs : AttributeSet): super(context,attrs){
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleattr: Int) : super(context,attrs, defStyleattr){
        init()

    }

    private val errorMessage = MutableLiveData<String>()
    private val hideError = MutableLiveData<Boolean>()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun init() {
        inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        transformationMethod = PasswordTransformationMethod()
        doAfterTextChanged { text ->
            when{
                text?.isEmpty() == true ->{setErrorMessage(context.getString(R.string.must_not_empty)) }
                (text?.length ?: 0) < 8 ->{setErrorMessage(context.getString(R.string.more_then_eight_character))}
                else -> hideErrorMessage()
            }
        }
    }

    private fun hideErrorMessage() {
        hideError.value = true
    }

    private fun setErrorMessage(message: String) {
        errorMessage.value = message
    }

}