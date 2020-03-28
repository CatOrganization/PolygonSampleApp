package io.polygon.kotlin.persistence

import io.polygon.kotlin.persistence.sharedprefs.PolygonKeySPAO
import org.koin.dsl.module

val persistenceModule = module {
    single { PolygonKeySPAO(get()) }
}

val persistenceModules = arrayOf(persistenceModule)