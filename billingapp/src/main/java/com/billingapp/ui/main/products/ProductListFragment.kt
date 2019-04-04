package com.billingapp.ui.main.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.billingapp.MainActivity
import com.billingapp.R
import com.billingapp.config.INAPP_SKUS
import com.billingapp.config.PUBLIC_BASE_64_KEY
import com.billingapp.config.SUBS_SKUS
import com.billingapp.ui.main.MainViewModel
import kotlinx.android.synthetic.main.product_list_fragment.*
import my.android.inappbilling.*
import my.android.inappbilling.enums.BillingOK
import my.android.inappbilling.enums.ProductCategory

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

       /* BillingRepo.getInstance(activity?.application!!)
                .items(BillingRepo.ProductCategory.INAPP.value, INAPP_SKUS)
                .startDataSourceConnections()
                .onBillingOk(BillingRepo.BillingOK.QUERY_INAPP)
                .onQueryResult { responseCode, skuDetailsList ->
                    queryProductsResult(responseCode, skuDetailsList)
                }*/
        BillingRepo.getInstance(activity?.application!!)
                .items(ProductCategory.SUBS.value, SUBS_SKUS)
                .items(ProductCategory.INAPP.value, INAPP_SKUS)
                .startDataSourceConnections()
                .onBillingOk(BillingOK.QUERY_BOTH)
                .onQueryResult { responseCode, skuDetailsList ->
                    queryProductsResult(responseCode, skuDetailsList)
                }
        btnViewPurchases.setOnClickListener { navigateToViewPurchase() }
    }

    private fun queryProductsResult(billingResponse: BillingResponse, skuDetailsList: MutableList<AugmentedSkuDetails>?) {
        Log.d(javaClass.name, skuDetailsList.toString())
        rvProductList?.adapter = ProductListAdapter(skuDetailsList
                ?: mutableListOf()) { pos, data ->
            // When product item is clicked, initiate purchase flow
            handleOnClick(pos, data)
        }
    }

    // PUBLIC_BASE_64_KEY is the key for verifying purchase signature,
    // get this from  Play console, Choose your app then in navigation drawer goto Development Tools -> Services & APIs
    private fun handleOnClick(pos: Int, data: AugmentedSkuDetails) {
        BillingRepo.getInstance(activity?.application!!).from(activity!!)
                .verifyWith(PUBLIC_BASE_64_KEY, true)
                .launchBillingFlow(data) { resCode, purchaseList ->
                    when (resCode) {
                        BillingResponse.OK -> Toast.makeText(requireContext(), "Purchase Success", Toast.LENGTH_SHORT).show()
                        BillingResponse.USER_CANCELED -> Toast.makeText(requireContext(), "Purchase Cancelled", Toast.LENGTH_SHORT).show()
                        BillingResponse.ITEM_ALREADY_OWNED -> Toast.makeText(requireContext(), "You have already purchased the item", Toast.LENGTH_SHORT).show()
                        BillingResponse.ERROR -> Toast.makeText(requireContext(), "Unable to verify the purchase", LENGTH_SHORT).show()
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
