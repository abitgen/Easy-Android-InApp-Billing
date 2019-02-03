package com.billingapp.ui.main.purchases

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.billingapp.R
import com.billingapp.logic.BillingRepo
import com.billingapp.logic.CONSUMABLE_SKUS
import com.billingapp.ui.main.MainViewModel
import kotlinx.android.synthetic.main.purchase_list_fragment.*

class ProductPurchasedFragment : Fragment() {

    companion object {
        fun newInstance() = ProductPurchasedFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.purchase_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        BillingRepo.getInstance(activity?.application!!)
                .startDataSourceConnections()
                .onBillingOk(BillingRepo.BillingOK.QUERY_PURCHASES)
                .onPurchasesQueryResult {
                    rvPurchaseList?.adapter = ProductPurchasedAdapter(it) { pos, data ->
                        // on purchase item click, consume the inapp purchase
                        consumePurchase(data)
                    }
                }
        btnBack.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }
    }

    private fun consumePurchase(purchase: Purchase){
        BillingRepo.getInstance(activity?.application!!)
            .items(BillingRepo.PurchaseCategory.IS_CONSUMABLE.name, CONSUMABLE_SKUS)
            .consumePurchase(purchase){ responseCode, purchaseToken ->
                when(responseCode){
                    BillingClient.BillingResponse.OK ->{
                        Log.d(javaClass.name, "Product was consumed successfully")
                        Toast.makeText(requireContext(),"Product was consumed successfully,\n Now you can lock your paid content \n And notify server", LENGTH_SHORT).show()
                    }
                    else -> {
                        Log.d(javaClass.name, "Product was NOT consumed/ Already Consumed $responseCode")
                        Toast.makeText(requireContext(),"Product couldn't be consumed. May be this is a one time product or already consumed", LENGTH_SHORT).show()
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        BillingRepo.getInstance(activity?.application!!).endDataSourceConnections()
    }

}
