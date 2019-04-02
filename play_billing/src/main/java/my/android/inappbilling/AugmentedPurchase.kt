package my.android.inappbilling

import com.android.billingclient.api.Purchase

data class AugmentedPurchase(val purchase: Purchase,
                             val signatureVerified: Boolean = false)
