package io.github.coderbuck.boring.util

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.core.net.toUri

object DeepLinkUtils {

    fun open(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW, link.toUri())
        val pm = context.packageManager
        val activities = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        val matched = activities.any { isSpecificUriMatch(it.match) }
        if (matched) {
            context.startActivity(intent)
        } else {
            CustomTabUtils.open(context, link)
        }
    }

    private fun isSpecificUriMatch(inputMatch: Int): Boolean {
        var match = inputMatch
        match = match and IntentFilter.MATCH_CATEGORY_MASK
        return match >= IntentFilter.MATCH_CATEGORY_HOST && match <= IntentFilter.MATCH_CATEGORY_PATH
    }
}