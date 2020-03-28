package io.polygon.android.marketwatcher.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.polygon.android.marketwatcher.MainActivity
import io.polygon.android.marketwatcher.R
import io.polygon.android.marketwatcher.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            viewModel.setApiKey(api_key.text.toString())

            finish()

            startActivity(MainActivity.IntentFactory.build(this))
        }
    }

    object IntentFactory {

        fun build(context: Context) = Intent(context, LoginActivity::class.java)
    }
}