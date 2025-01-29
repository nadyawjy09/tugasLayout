package com.example.tugaslayout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi elemen UI
        val nama = findViewById<EditText>(R.id.editTextText)
        val pass = findViewById<EditText>(R.id.editTextText2)
        val buttonClick = findViewById<Button>(R.id.button)

        buttonClick.setOnClickListener {
            // Validasi username dan password
            if (nama.text.toString() == "nadya" && pass.text.toString() == "123") {
                // Intent ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            } else {
                // Login gagal
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}