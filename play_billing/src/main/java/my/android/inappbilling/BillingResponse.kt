package my.android.inappbilling

import com.android.billingclient.api.BillingClient

enum class BillingResponse(val value:Int) {
    OK(BillingClient.BillingResponse.OK),
    USER_CANCELED(BillingClient.BillingResponse.USER_CANCELED),
    DEVELOPER_ERROR(BillingClient.BillingResponse.DEVELOPER_ERROR),
    ITEM_ALREADY_OWNED(BillingClient.BillingResponse.ITEM_ALREADY_OWNED),
    ITEM_NOT_OWNED(BillingClient.BillingResponse.ITEM_NOT_OWNED),
    ITEM_UNAVAILABLE(BillingClient.BillingResponse.ITEM_UNAVAILABLE),
    SERVICE_DISCONNECTED(BillingClient.BillingResponse.SERVICE_DISCONNECTED),
    ERROR(BillingClient.BillingResponse.ERROR)
}