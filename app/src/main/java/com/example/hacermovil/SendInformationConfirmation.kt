package com.example.hacermovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class SendInformationConfirmation : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_information_confirmation)

        title = "Confirmación"

        val bundle =intent.extras
        var email = bundle?.getString("email").toString()
        var phone = bundle?.getString("phone").toString()
        Toast.makeText(this, "User: "+email+ " Phone: "+phone, Toast.LENGTH_SHORT).show()

        Toast.makeText(this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show()

        var btn_no = findViewById<Button>(R.id.btn_no)
        btn_no.setOnClickListener {
            onBackPressed()
        }
        var btn_si = findViewById<Button>(R.id.btn_si)
        btn_si.setOnClickListener {
            sendInfo(email,phone)
            onBackPressed()
        }

    }

    private fun sendInfo(email:String,phone:String){
        db.collection("users").document(email).set(
            hashMapOf(
                "numero" to phone)
        )
    }

}