package com.example.hacermovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

enum class ProviderType{
    GOOGLE
}


class MainActivity : AppCompatActivity() {
    private val GOOGLE_SIGN_IN = 100;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btn_login = this.findViewById(R.id.btn_login) as Button

        btn_login.setOnClickListener{logIn()}

        setup()

    }

    private fun logIn(){
        //var txt_username =  findViewById<EditText>(R.id.txt_username);
        //var txt_password = findViewById<EditText>(R.id.txt_password)
        //var user = txt_username.text.toString()
        //var psw = txt_password.text.toString()

        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("295535290119-4lh1uqrhsrn54fcqaaoso50uo7hhn70l.apps.googleusercontent.com").requestEmail().build()
        val googleClient = GoogleSignIn.getClient(this,googleConf)

        startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        //Toast.makeText(this, "User: "+user+"Psw: "+psw, Toast.LENGTH_SHORT).show()
    }

    private fun setup(){
        title = "Inicia sesión en My Gym"
    }

    private fun showHome(email: String, username: String,provider: ProviderType){
        val homeIntent =Intent(this,Home::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
            putExtra("name",username)
        }
        startActivity(homeIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            if(account != null){
                val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                showHome(account.email.toString(),account.familyName.toString(),ProviderType.GOOGLE)
                Toast.makeText(this, "User: "+account.email, Toast.LENGTH_SHORT).show()
            }


        }

    }


}