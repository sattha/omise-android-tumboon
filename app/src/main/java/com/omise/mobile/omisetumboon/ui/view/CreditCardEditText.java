package com.omise.mobile.omisetumboon.ui.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by X-tivity on 9/18/2017 AD.
 */

public class CreditCardEditText extends AppCompatEditText {

    private static final int CREDIT_CARD_LENGTH_INCLUSIVE = 19;
    private static final int NUMBER_GROUP_LENGTH = 4;

    public CreditCardEditText(Context context) {
        super(context);
        this.init();
    }

    public CreditCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public CreditCardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(CREDIT_CARD_LENGTH_INCLUSIVE)});
        this.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.addTextChangedListener(new CreditCardTextChanged(this));
    }

    public String getOriginalCreditCardNumber() {
        return this.getText().toString().replaceAll(" ", "");
    }

    private class CreditCardTextChanged implements TextWatcher {

        private EditText editText;
        private String textBefore;

        CreditCardTextChanged(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            this.textBefore = charSequence.toString();
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            editText.removeTextChangedListener(this);

            String displayString = s.toString();

            int originalLastIndex = s.length() - 1;
            int beforeLastIndex = textBefore.length() - 1;

            if (originalLastIndex >= 0) {

                boolean isRemove = beforeLastIndex > originalLastIndex;
                boolean isRemoveSeparator = isRemove && textBefore.substring(beforeLastIndex).equals(" ");
                boolean isNeedSeparator = !isRemove && normalize(displayString).length() % NUMBER_GROUP_LENGTH == 0;

                if (isRemoveSeparator) {
                    editText.setText(displayString.substring(0, originalLastIndex));
                    editText.setSelection(editText.getText().length());
                } else if (isNeedSeparator) {
                    editText.setText((displayString + " "));
                    editText.setSelection(editText.getText().length());
                }
            }

            editText.addTextChangedListener(this);
        }
    }

    private String normalize(String cardNumber) {
        return cardNumber == null ? "" : cardNumber.replace(" ", "");
    }
}
