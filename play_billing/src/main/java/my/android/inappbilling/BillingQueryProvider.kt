package my.android.inappbilling

import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails

interface BillingQueryProvider {
    fun onBillingOk(skuType: BillingRepo.BillingOK): BillingQueryProvider
    fun onQueryResult(result: (responseCode: BillingResponse, skuDetailsList: MutableList<AugmentedSkuDetails>?) -> Unit)
    fun onPurchasesQueryResult(purchaseList: (List<Purchase>) -> Unit): BillingPurchaseProvider
}