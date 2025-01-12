package com.example.alp_vp.view

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.alp_vp.repositories.NetworkAuthRepository
import com.example.alp_vp.services.AuthenticationAPIService
import com.example.alp_vp.viewmodel.LoginRegisterViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    private val viewModel: LoginRegisterViewModel by viewModels {
        LoginRegisterViewModelFactory(NetworkAuthRepository(initRetrofit()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create layout dynamically
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.WHITE)
            setPadding(50, 50, 50, 50)
        }

        val usernameField = EditText(this).apply {
            hint = "Enter your username"
            setPadding(20, 20, 20, 20)
        }

        val emailField = EditText(this).apply {
            hint = "Enter your email"
            setPadding(20, 20, 20, 20)
        }

        val passwordField = EditText(this).apply {
            hint = "Enter your password"
            setPadding(20, 20, 20, 20)
        }

        val registerButton = Button(this).apply {
            text = "Register"
            setBackgroundColor(Color.GREEN)
            setTextColor(Color.WHITE)
        }

        val messageView = TextView(this).apply {
            text = ""
            setTextColor(Color.BLACK)
            textSize = 16f
            gravity = Gravity.CENTER
        }

        // Add views to layout
        rootLayout.addView(usernameField)
        rootLayout.addView(emailField)
        rootLayout.addView(passwordField)
        rootLayout.addView(registerButton)
        rootLayout.addView(messageView)

        // Set content view
        setContentView(rootLayout)

        // Observe ViewModel
        viewModel.response.observe(this) {
            it?.let { response ->
                messageView.text = "Registered as ${response.username}"
            }
        }

        viewModel.error.observe(this) {
            it?.let { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listener for register
        registerButton.setOnClickListener {
            val username = usernameField.text.toString()
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            viewModel.register(username, email, password)
        }
    }

    private fun initRetrofit(): AuthenticationAPIService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AuthenticationAPIService::class.java)
    }
}