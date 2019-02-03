package com.billingapp.ui.main.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.SkuDetails
import com.billingapp.MainActivity
import com.billingapp.R
import com.billingapp.logic.BillingRepo
import com.billingapp.logic.INAPP_SKUS
import com.billingapp.ui.main.MainViewModel
import kotlinx.android.synthetic.main.product_list_fragment.*

class ProductListFragment : Fragment() {

    companion object {
        fun newInstance() = ProductListFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        BillingRepo.getInstance(activity?.application!!)
                .items(BillingClient.SkuType.INAPP, INAPP_SKUS)
                .startDataSourceConnections()
                .onBillingOk(BillingRepo.BillingOK.QUERY_INAPP)
                .onQueryResult { responseCode, skuDetailsList ->
                    queryProductsResult(responseCode, skuDetailsList)
                }
        btnViewPurchases.setOnClickListener { navigateToViewPurchase() }
    }

    private fun queryProductsResult(responseCode: Int, skuDetailsList: MutableList<SkuDetails>?) {
        Log.d(javaClass.name, skuDetailsList.toString())
        rvProductList?.adapter = ProductListAdapter(skuDetailsList
                ?: mutableListOf()) { pos, data ->
            // When product item is clicked, initiate purchase flow
            handleOnClick(pos, data)
        }
    }

    private fun handleOnClick(pos: Int, data: SkuDetails) {
        BillingRepo.getInstance(activity?.application!!).from(activity!!)
                .launchBillingFlow(data) { resCode, purchaseList ->
                    when (resCode) {
                        BillingClient.BillingResponse.ITEM_ALREADY_OWNED -> Toast.makeText(requireContext(), "You have already purchased the item", Toast.LENGTH_SHORT).show()
                    }
                    Log.d(javaClass.name, purchaseList.toString())
                }
    }

    private fun navigateToViewPurchase(){
        (activity as MainActivity).navigateToViewPurchaseFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        BillingRepo.getInstance(activity?.application!!).endDataSourceConnections()
    }
}
