package io.polygon.android.domain.usecase

import io.polygon.android.repository.repositoryModules
import org.koin.dsl.module

val domainModule = module {
    single { EquityUseCase() }
    single { UserUseCase() }
}

val domainModules = arrayOf(domainModule, *repositoryModules)