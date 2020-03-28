package io.polygon.kotlin.persistence.sharedprefs

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class StringSharedPref(
    val sharedPrefs: SharedPreferences,
    val key: String,
    val default: String?
) : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPrefs.getString(key, default)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        sharedPrefs.edit().putString(key, value).apply()
    }
}