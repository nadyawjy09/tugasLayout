package com.example.tugaslayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.tugaslayout.kalkulator



class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var extbtn: Button
    private lateinit var aboutMeBtn: Button // Deklarasi tombol "About Me"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Tombol "Logout"
        extbtn = findViewById(R.id.button_logout)
        extbtn.setOnClickListener(this)

        // Tombol "About Me"
        aboutMeBtn = findViewById(R.id.button_about_me) // Menghubungkan tombol "About Me"
        aboutMeBtn.setOnClickListener {
            showAboutMeDialog() // Menampilkan dialog ketika tombol "About Me" ditekan
        }

        // Menangani window insets (untuk padding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Fungsi untuk berpindah ke kalkulator
    fun goToKalkulator(view: View) {
        val intent = Intent(this, kalkulator::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        if (v?.id == R.id.button_logout) {
            finish() // Logout: Menutup aplikasi atau aktivitas
        }
    }

    // Fungsi untuk menampilkan dialog "About Me"
    private fun showAboutMeDialog() {
        val builder = MaterialAlertDialogBuilder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_about_me, null)
        builder.setView(dialogView)
            .setTitle("About Me")
            .setPositiveButton("Close") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
