package io.polygon.android.marketwatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.polygon.android.domain.usecase.UserUseCase
import io.polygon.android.marketwatcher.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

class SplashScreenActivity : AppCompatActivity(), KoinComponent {

    private val userUseCase by inject<UserUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (userUseCase.hasApiKey) {
            startActivity(MainActivity.IntentFactory.build(this))
        } else {
            startActivity(LoginActivity.IntentFactory.build(this))
        }

        finish()
    }
}
