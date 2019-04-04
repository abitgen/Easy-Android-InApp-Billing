package com.billingapp.config

val GAS = "gas"
val PREMIUM_CAR = "premium_car"
val INAPP_SKUS = listOf(GAS, PREMIUM_CAR)
val CONSUMABLE_SKUS = listOf(GAS)

val GOLD_MONTHLY = "gold_monthly"
val GOLD_YEARLY = "gold_yearly"

/**
 * Inapp & Non Consumable - These are like onetime purchases, which unlocks a particular section in app
 * Should not be trigger consume on this.
 *
 * Eg: Upgrade from Free to Paid app.
 */
val STRESS_FREE_PACKAGE = "inapp_1_stress.free_package"
val BOOST_CONFIDENCE_PACKAGE = "inapp_2_boost.confidence_package"
val IMPROVE_FOCUS_PACKAGE = "inapp_3_improve.focus_package"

/**
 * Subscription are by default non-Consumable means they are recurring indeterminately and it cannot end automatically,
 * until the user manually cancels the package / payment option expires. And all packages in app are unlocked during the
 * subscription period.
 *
 * This generally renews fee automatically(Auto Renewal).
 * Eg: Netflix / Magazine Subscription.
 */
val ONE_WEEK_PACKAGE = "subs_week_package"
val ONE_MONTH_PACKAGE = "subs_month_package"
val THREE_MONTH_PACKAGE = "subs_3.month_package"
val SIX_MONTH_PACKAGE = "subs_6.month_package"
val ONE_YEAR_PACKAGE = "subs_year_package"

/**
 * Inapp & Consumable means when this package is bought, the chosen plan is available for that period of time,
 * which is handled by our backend api. Once our server sends that its locked,
 * you should consume this productId, so it can be bought again.
 *
 * This option can act as Manual Renewal.
 *
 * Eg: You are Renting a Movie for 1 Month. So Movie is available to be viewed anytime in 1 month and not after that.
 */
val ONE_WEEK_STRESS_FREE_PACKAGE = "inapp_1_stress.free_oneweek_package"
val ONE_MONTH_BOOST_CONFIDENCE_PACKAGE = "inapp_2_boost.confidence_onemonth_package"
val ONE_WEEK_IMPROVE_FOCUS_PACKAGE = "inapp_3_improve.focus_oneweek_package"


/**
 * Used to validate the purchase
 */
val PUBLIC_BASE_64_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiErmNalKGOsU+tY0akRGc1yNfXDbJqL5Cc1cccudDZzMzT00eKn4E3Sh/YYGBREtIJUYV1/tvIChz+AAmPhijEdEUm4dY0N+MsfigAuVelIAI8lm2VRg7dUZQjXsX5Rdk3l4HaqdRTOK6R4jAFu6VheYxggVOLQQtyUAf+sEx0JdBdXb2fOfSEF/yIWGGMVdIy4uoR2+PxvzU/rv0/wh/gyKithaV69lP+gmfvDnsg/B5ZVqtnih4mDJhwyzeNiu57GdxJVbe2yJGt/w1AZBev/DP4Q962JH7FliQo4eLjZ55vQpvAntlBZucv7EFYNlZJolIu2uRiDWdg0cHUUlAwIDAQAB"


val SUBS_SKUS = listOf(ONE_WEEK_PACKAGE, ONE_MONTH_PACKAGE, ONE_YEAR_PACKAGE)
val INAPP_NON_CONSUMABLE_SKUS = listOf(STRESS_FREE_PACKAGE, BOOST_CONFIDENCE_PACKAGE, IMPROVE_FOCUS_PACKAGE)
val INAPP_CONSUMABLE_SKUS = listOf(ONE_WEEK_STRESS_FREE_PACKAGE, ONE_MONTH_BOOST_CONFIDENCE_PACKAGE, ONE_WEEK_IMPROVE_FOCUS_PACKAGE)