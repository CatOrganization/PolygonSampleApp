package io.polygon.android.marketwatcher.viewmodel

import androidx.lifecycle.ViewModel
import io.polygon.android.domain.usecase.UserUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginViewModel : ViewModel(), KoinComponent {

    private val userUseCase by inject<UserUseCase>()

    fun setApiKey(key: String) {
        // TODO: Validation and stuff
        userUseCase.setApiKey(key)
    }

}