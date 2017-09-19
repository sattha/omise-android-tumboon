package com.omise.mobile.omisetumboon.ui.main

import com.omise.mobile.omisetumboon.data.local.models.Charity
import com.omise.mobile.omisetumboon.ui.BasePresenter
import com.omise.mobile.omisetumboon.ui.BaseView

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
interface MainContract {

    interface View: BaseView<Presenter> {

        fun showPaymentUi(charity: Charity)

        fun showLoadingIndicator()

        fun dismissLoadingIndicator()

        fun showErrorDialog(message: String)

        fun updateListItem(charities: List<Charity>)

        fun showEmptyViewByHideListView(message: String)

        fun showListViewByHideEmptyView()

        val charityItemCount: Int
    }

    interface Presenter: BasePresenter<View> {

        fun loadCharity(isForceReload: Boolean)

        fun openPayment(charity: Charity)
    }
}