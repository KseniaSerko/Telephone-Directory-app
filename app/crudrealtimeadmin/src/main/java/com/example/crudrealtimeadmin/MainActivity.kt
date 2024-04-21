package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener {
            val intent = Intent(this@MainActivity, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener {
            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}