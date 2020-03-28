package io.polygon.kotlin.persistence.sharedprefs

import android.content.SharedPreferences

class PolygonKeySPAO(sharedPrefs: SharedPreferences) {

    // TODO: DON'T COMMIT THE API KEY, MORON
    var polygonApiKey by StringSharedPref(
        sharedPrefs,
        "polygon_api_key_pref_key",
        null
    )
}