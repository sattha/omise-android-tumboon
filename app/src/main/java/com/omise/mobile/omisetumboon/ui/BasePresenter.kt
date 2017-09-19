package com.omise.mobile.omisetumboon.ui

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
interface BasePresenter<T> {

    fun takeView(view: T);

    fun dropView();
}