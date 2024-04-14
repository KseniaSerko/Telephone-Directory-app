package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val surname = binding.uploadSurname.text.toString()
            val phone = binding.uploadPhone.text.toString()
            val companyName = binding.uploadCompany.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Contact Information")
            val contactData = ContactData(name, surname, phone, companyName)
            databaseReference.child(phone).setValue(contactData).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadSurname.text.clear()
                binding.uploadPhone.text.clear()
                binding.uploadCompany.text.clear()

                Toast.makeText(this, "Contact has been saved successfully", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()
            }
        }
    }
}