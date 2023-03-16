package com.example.hacermovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class DeleteInformationConfirmation : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_information_confirmation)

        title = "Confirmación"

        val bundle =intent.extras
        var email = bundle?.getString("email").toString()
        Toast.makeText(this, "User: "+email, Toast.LENGTH_SHORT).show()

        var btn_no = findViewById<Button>(R.id.btn_no)
        btn_no.setOnClickListener {
            onBackPressed()
        }
        var btn_si = findViewById<Button>(R.id.btn_si)
        btn_si.setOnClickListener {
            deleteInfo(email)
            onBackPressed()
        }

    }

    private fun deleteInfo(email:String){
        db.collection("users").document(email).delete()
    }

}