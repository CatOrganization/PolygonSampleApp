package io.polygon.android.domain.usecase

import io.polygon.android.repository.UserRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserUseCase : KoinComponent {

    private val userRepository by inject<UserRepository>()

    val hasApiKey
        get() = userRepository.hasApiKey

    fun setApiKey(key: String) {
        userRepository.setApiKey(key)
    }
}