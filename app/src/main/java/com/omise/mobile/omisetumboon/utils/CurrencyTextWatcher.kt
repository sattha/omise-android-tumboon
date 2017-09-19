package com.omise.mobile.omisetumboon.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class CurrencyTextWatcher(val editText: EditText) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {

        editText.removeTextChangedListener(this)

        try {
            var originalString = s.toString()

            val longval: Long?
            if (originalString.contains(",")) {
                originalString = originalString.replace(",".toRegex(), "")
            }
            longval = java.lang.Long.parseLong(originalString)

            val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
            formatter.applyPattern("#,###,###,###")
            val formattedString = formatter.format(longval)

            //setting text after format to EditText
            editText.setText(formattedString)
            editText.setSelection(editText.text.length)
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }

        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}