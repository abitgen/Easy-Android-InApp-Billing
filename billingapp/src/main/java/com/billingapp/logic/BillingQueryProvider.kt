package com.billingapp.logic

import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails

interface BillingQueryProvider {
    fun onBillingOk(skuType: BillingRepo.BillingOK): BillingQueryProvider
    fun onQueryResult(result: (responseCode: Int, skuDetailsList: MutableList<SkuDetails>?) -> Unit)
    fun onPurchasesQueryResult(purchaseList: (List<Purchase>) -> Unit): BillingPurchaseProvider
}