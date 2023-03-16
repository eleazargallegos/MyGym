package com.example.hacermovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class SignOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_out)

        title = "¿Ya te vas?"

        var btn_no = this.findViewById(R.id.btn_no) as Button
        btn_no.setOnClickListener{onBackPressed()}
        var btn_si = this.findViewById(R.id.btn_si) as Button
        btn_si.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            showLogin()
        }

    }

    private fun showLogin(){
        val loginIntent = Intent(this,MainActivity::class.java)
        startActivity(loginIntent)
    }

}