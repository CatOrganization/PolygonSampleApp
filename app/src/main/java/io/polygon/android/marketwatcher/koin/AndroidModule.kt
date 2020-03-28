package io.polygon.android.marketwatcher.koin

import android.content.Context
import org.koin.dsl.module

val androidModule = module {
    single { get<Context>().getSharedPreferences("polygon_prefs", Context.MODE_PRIVATE) }
}