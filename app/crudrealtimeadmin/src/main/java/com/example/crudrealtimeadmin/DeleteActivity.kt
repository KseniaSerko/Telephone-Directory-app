package com.example.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val phone = binding.deletePhone.text.toString()
            if (phone.isNotEmpty()) {
                deleteData(phone)
            } else {
                Toast.makeText(this, "Please, enter a phone number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(phone: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Contact Information")
        databaseReference.child(phone).removeValue().addOnSuccessListener {
            binding.deletePhone.text.clear()
            Toast.makeText(this, "The contact has been deleted successfully", Toast.LENGTH_SHORT)
                .show()
        }.addOnCanceledListener {
            Toast.makeText(this, "Unable to delete the contact", Toast.LENGTH_SHORT).show()
        }
    }
}