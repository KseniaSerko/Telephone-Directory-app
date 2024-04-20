package com.example.telephonedirectoryapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.telephonedirectoryapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchContactNumber: String = binding.searchPhone.text.toString()
            if (searchContactNumber.isNotEmpty()) {
                readData(searchContactNumber)
            } else {
                Toast.makeText(this, "Please, enter the contact phone number", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun readData(contactNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Contact Information")
        databaseReference.child(contactNumber).get().addOnSuccessListener {
            if (it.exists()) {
                val contactName = it.child("name").value
                val contactSurname = it.child("surname").value
                val contactCompany = it.child("companyName").value
                Toast.makeText(this, "Found according to your request", Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readContactName.text = contactName.toString()
                binding.readContactSurname.text = contactSurname.toString()
                binding.readContactCompany.text = contactCompany.toString()
            } else {
                Toast.makeText(this, "Can't find any matching number", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

    }

}