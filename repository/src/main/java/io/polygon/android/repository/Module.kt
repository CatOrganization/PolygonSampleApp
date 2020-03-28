package io.polygon.android.repository

import io.polygon.kotlin.persistence.persistenceModules
import org.koin.dsl.module

val repositoryModule = module {
    single { EquityRepository() }
    single { UserRepository() }
}

val repositoryModules = arrayOf(repositoryModule, *persistenceModules)