package com.omise.mobile.omisetumboon.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.omise.mobile.omisetumboon.R
import com.omise.mobile.omisetumboon.data.local.models.Charity
import com.omise.mobile.omisetumboon.di.scope.ActivityScoped
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import com.omise.mobile.omisetumboon.ui.payment.PaymentActivity
import kotlinx.android.synthetic.main.empty_view.*


/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@ActivityScoped
class MainFragment @Inject constructor() : DaggerFragment(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter;

    private val mItemListener = object : CharityItemListener {

        override fun onCharityClick(clickedCharity: Charity) {
            presenter.openPayment(clickedCharity)
        }
    }

    private val mSwipeListener = SwipeRefreshLayout.OnRefreshListener {
        presenter.loadCharity(true);
    }

    private val mAdapter = CharityListItemAdapter(mItemListener)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lvCharity.adapter = mAdapter
        swipeLayout.setOnRefreshListener(mSwipeListener);
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // Setup the scrolling view in the custom SwipeRefreshLayout.
        swipeLayout.setScrollUpChild(lvCharity);
        // Setup retry button
        btnRetry.setOnClickListener {
            presenter.loadCharity(true);
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    override fun showLoadingIndicator() {
        swipeLayout?.post({ swipeLayout?.isRefreshing = true })
    }

    override fun dismissLoadingIndicator() {
        swipeLayout?.post({ swipeLayout?.isRefreshing = false })
    }

    override fun showErrorDialog(message: String) {
        AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", { dialog, _ -> dialog.dismiss() })
                .show()
    }

    override fun showEmptyViewByHideListView(message: String) {
        swipeLayout.visibility = View.GONE
        emptyContainer.visibility = View.VISIBLE
        tvEmptyMessage.text = message
    }

    override fun showListViewByHideEmptyView() {
        swipeLayout.visibility = View.VISIBLE
        emptyContainer.visibility = View.GONE
    }

    override val charityItemCount: Int
        get() = mAdapter.count

    override fun updateListItem(charities: List<Charity>) {
        mAdapter.replaceData(charities)
    }

    override fun showPaymentUi(charity: Charity) {
        //Shown in it's own Activity, since it makes more sense that way
        // and it gives us the flexibility to show some Intent stubbing.
        val intent = Intent(context, PaymentActivity::class.java)
        intent.putExtra(PaymentActivity.EXTRA_CHARITY_ID, charity.id.toString())
        intent.putExtra(PaymentActivity.EXTRA_CHARITY_LOGO, charity.logoUrl);
        startActivity(intent)
    }

    interface CharityItemListener {

        fun onCharityClick(clickedCharity: Charity)
    }

    class CharityListItemAdapter(val listener: CharityItemListener) : BaseAdapter() {

        private var items: List<Charity> = mutableListOf()

        fun replaceData(items: List<Charity>) {
            this.items = items;
            notifyDataSetChanged()
        }

        override fun getView(position: Int, view: View?, container: ViewGroup?): View {

            var rowView = view;

            if (rowView == null) {
                rowView = View.inflate(container?.context, R.layout.listitem_charity, null)
                ViewHolder(rowView)
            }

            val holder = rowView?.tag as ViewHolder
            val item = getItem(position)

            Glide.with(container?.context)
                    .load(item.logoUrl)
                    .into(holder.imgHeader)
                    .onLoadFailed(ContextCompat.getDrawable(container?.context, android.R.drawable.ic_dialog_alert))

            holder.tvCharityName.text = item.name
            holder.cardView.tag = item
            holder.cardView.setOnClickListener {
                val clickItem = it.tag as Charity
                listener.onCharityClick(clickItem)
            }

            return rowView;
        }

        override fun getItem(index: Int): Charity {
            return items[index]
        }

        override fun getItemId(index: Int): Long {
            return index.toLong()
        }

        override fun getCount(): Int {
            return items.size
        }

        inner class ViewHolder(view: View) {

            val cardView: CardView = view.findViewById(R.id.cardView)
            val imgHeader: ImageView = view.findViewById(R.id.imgHeader)
            val tvCharityName: TextView = view.findViewById(R.id.tvCharityName)

            init {
                view.tag = this;
            }
        }
    }
}