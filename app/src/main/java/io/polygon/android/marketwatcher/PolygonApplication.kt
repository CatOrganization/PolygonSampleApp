package io.polygon.android.marketwatcher

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.polygon.android.domain.usecase.domainModules
import io.polygon.android.marketwatcher.koin.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PolygonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        startKoin {
            androidLogger()
            androidContext(this@PolygonApplication)
            modules(androidModule, *domainModules)
        }
    }
}