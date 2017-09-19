package com.omise.mobile.omisetumboon.ui.payment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.omise.android.ui.ExpiryMonthSpinnerAdapter
import co.omise.android.ui.ExpiryYearSpinnerAdapter
import com.bumptech.glide.Glide
import com.omise.mobile.omisetumboon.R
import com.omise.mobile.omisetumboon.di.scope.ActivityScoped
import com.omise.mobile.omisetumboon.ui.thankyou.ThankYouActivity
import com.omise.mobile.omisetumboon.utils.CurrencyTextWatcher
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_payment.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@ActivityScoped
class PaymentFragment @Inject constructor() : DaggerFragment(), PaymentContract.View {

    private val spinnerMonthAdapter = SpinnerMonthAdapter()
    private val spinnerYearAdapter = SpinnerYearAdapter()

    @Inject
    @field:Named(PaymentActivity.EXTRA_CHARITY_ID)
    lateinit var charityId: String

    @Inject
    @field:Named(PaymentActivity.EXTRA_CHARITY_LOGO)
    lateinit var charityLogoUrl: String

    @Inject lateinit var presenter: PaymentContract.Presenter

    private lateinit var progress: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup image header
        Glide.with(context)
                .load(charityLogoUrl)
                .into(imgHeader)
                .onLoadFailed(ContextCompat.getDrawable(context, android.R.drawable.ic_dialog_alert))

        // setup expiration spinner
        spnMonth.adapter = spinnerMonthAdapter
        spnYear.adapter = spinnerYearAdapter

        // setup donate amount EditText
        edtAmount.addTextChangedListener(CurrencyTextWatcher(edtAmount))

        // setup donate button click
        btnDonate.setOnClickListener { presenter.donate() }

        // setup progress dialog
        progress = ProgressDialog(context)
        progress.setMessage("Payment In Progress..")
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgressNumberFormat(null);
        progress.setProgressPercentFormat(null);
        progress.setCancelable(false)
        progress.isIndeterminate = true;
        progress.progress = 0;
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    override fun creditCardNumber(): String {
        return edtCardNumber.text.toString()
    }

    override fun creditCardName(): String {
        return edtCardName.text.toString()
    }

    override fun securityCode(): String {
        return edtSecurityCode.text.toString()
    }

    override fun expirationMonth(): Int {
        return spinnerMonthAdapter.getItem(spnMonth.selectedItemPosition).toString().toInt()
    }

    override fun expirationYear(): Int {
        return spinnerYearAdapter.getItem(spnYear.selectedItemPosition).toString().toInt()
    }

    override fun donateAmount(): String {
        return edtAmount.text.toString()
    }

    override fun showLoadingIndicator() {
        progress.show();
    }

    override fun dismissLoadingIndicator() {
        progress.dismiss();
    }

    override fun showErrorDialog(message: String) {
        AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", { dialog, _ -> dialog.dismiss() })
                .show()
    }

    override fun showThankYouUi(amount: String) {
        val intent = Intent(context, ThankYouActivity::class.java)
        intent.putExtra(ThankYouActivity.EXTRA_DONATE_AMOUNT, amount)
        startActivity(intent)
    }

    class SpinnerMonthAdapter : ExpiryMonthSpinnerAdapter()
    class SpinnerYearAdapter : ExpiryYearSpinnerAdapter()
}