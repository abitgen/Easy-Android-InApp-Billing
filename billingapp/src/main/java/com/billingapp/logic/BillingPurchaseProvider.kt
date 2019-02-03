package com.billingapp.logic

import android.app.Activity
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails

interface BillingPurchaseProvider {
    //fun onLaunchBillingFlow(skuType: BillingRepo.BillingOK, data:SkuDetails): BillingPurchaseProvider
    /*fun onQueryResult(result: (responseCode: Int, skuDetailsList: MutableList<SkuDetails>?) -> Unit)
    fun startDataSourceConnections(): BillingPurchaseProvider*/
    fun onPurchaseUpdated(result: (responseCode: Int, purchaseList: MutableList<Purchase>?) -> Unit): BillingPurchaseProvider

    fun launchBillingFlow(skuDetails: SkuDetails): BillingPurchaseProvider

    fun launchBillingFlow(skuDetails: SkuDetails, result:(responseCode: Int, purchaseList: MutableList<Purchase>?) -> Unit) : BillingPurchaseProvider
    fun consumePurchase(purchase: Purchase, listener: (responseCode: Int, purchaseToken: String?) -> Unit): BillingPurchaseProvider
}