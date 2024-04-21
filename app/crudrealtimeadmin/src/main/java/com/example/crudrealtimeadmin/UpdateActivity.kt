package com.example.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val updateName = binding.updateName.text.toString()
            val updateSurname = binding.updateSurname.text.toString()
            val referencePhone = binding.referencePhone.text.toString()
            val updateCompany = binding.updateCompany.text.toString()

            updateData(updateName, updateSurname, referencePhone, updateCompany)
        }
    }

    private fun updateData(name: String, surname: String, phone: String, companyName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Contact Information")
        val contactData = mapOf<String, String>(
            "name" to name,
            "surname" to surname,
            "companyName" to companyName
        )
        databaseReference.child(phone).updateChildren(contactData).addOnSuccessListener {
            binding.updateName.text.clear()
            binding.updateSurname.text.clear()
            binding.referencePhone.text.clear()
            binding.updateCompany.text.clear()
            Toast.makeText(this, "The contact has been updated successfully", Toast.LENGTH_SHORT)
                .show()
        }.addOnCanceledListener {
            Toast.makeText(this, "Unable to update", Toast.LENGTH_SHORT)
                .show()
        }
    }
}