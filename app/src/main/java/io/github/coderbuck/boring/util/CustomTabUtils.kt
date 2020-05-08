package io.github.coderbuck.boring.util

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback

object CustomTabUtils {

    fun open(context: Context, link: String) {
        val customTabsIntent = CustomTabsIntent.Builder()
            .addDefaultShareMenuItem()
//                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setShowTitle(true)
            .build()
        CustomTabsHelper.addKeepAliveExtra(context, customTabsIntent.intent)
        CustomTabsHelper.openCustomTab(
            context,
            customTabsIntent,
            Uri.parse(link),
            WebViewFallback()
        )
    }

}