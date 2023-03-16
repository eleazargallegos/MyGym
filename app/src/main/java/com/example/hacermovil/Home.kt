package com.example.hacermovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Home : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle =intent.extras
        val email = bundle?.getString("email")
        val username = bundle?.getString("username")
        val provider = bundle?.getString("provider")
        setup(email ?: "",provider ?: "", username ?: "")

    }

    private fun setup(email: String, provider: String, username: String){
        title = "Hola, Bienvenido!"
        var txt_email =  findViewById<TextView>(R.id.txt_email);
        var txt_emailEditable =  findViewById<TextView>(R.id.txt_emailEditable);
        var btn_logout = findViewById<Button>(R.id.btn_logout)
        var btn_saveInfo = findViewById<Button>(R.id.btn_saveInfo)
        var btn_deleteInfo = findViewById<Button>(R.id.btn_deleteInfo)
        var txt_phoneEditable = findViewById<EditText>(R.id.txt_phoneEditable)

        txt_email.text = "Iniciaste sesión como: "+ email
        txt_emailEditable.text = email

        db.collection("users").document(email).get().addOnSuccessListener {
            //Toast.makeText(this, "Phone: "+it.get("numero") as String?, Toast.LENGTH_SHORT).show()
            txt_phoneEditable.setText(it.get("numero") as String?)
            if(it.get("numero") as String? != null){
                btn_deleteInfo.setVisibility(View.VISIBLE)
            }else{
                btn_deleteInfo.setVisibility(View.GONE)
            }

        }

        btn_logout.setOnClickListener{
            showConfirmLogOut()
        }
        btn_saveInfo.setOnClickListener{
            showConfirmSaveInfo()
        }

        btn_deleteInfo.setOnClickListener {
            showConfirmDeleteInfo()
        }

    }

    private fun showConfirmLogOut(){
        val signOutIntent = Intent(this,SignOut::class.java)
        startActivity(signOutIntent)
    }

    private fun showConfirmSaveInfo(){

        var txt_emailEditable =  findViewById<EditText>(R.id.txt_emailEditable);
        var email = txt_emailEditable.text.toString()

        var txt_phoneEditable = findViewById<EditText>(R.id.txt_phoneEditable)
        var phone = txt_phoneEditable.text.toString()

        if (email != null && phone != null){
            val saveInfoIntent = Intent(this,SendInformationConfirmation::class.java).apply {
                putExtra("email", email)
                putExtra("phone", phone)
            }
            startActivity(saveInfoIntent)
        } else{
            Toast.makeText(this, "Completa todos los campos porfavor", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showConfirmDeleteInfo(){
        var txt_emailEditable =  findViewById<EditText>(R.id.txt_emailEditable);
        var email = txt_emailEditable.text.toString()
        if (email != null){
            val saveInfoIntent = Intent(this,DeleteInformationConfirmation::class.java).apply {
                putExtra("email", email)
            }
            startActivity(saveInfoIntent)
        } else{
            Toast.makeText(this, "Error intentado eliminar tu registro, intenta nuevamente", Toast.LENGTH_SHORT).show()
        }
    }

}