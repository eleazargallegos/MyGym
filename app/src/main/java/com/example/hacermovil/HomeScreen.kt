package com.example.hacermovil

import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.hacermovil.databinding.ActivityHomeScreenBinding
import com.google.firebase.auth.FirebaseAuth

class HomeScreen : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHomeScreen.toolbar)

        binding.appBarHomeScreen.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home_screen)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val bundle =intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        var itemMiCuenta = binding.navView.menu.getItem(0)
        var itemCerrarSesion = binding.navView.menu.getItem(1)
        itemCerrarSesion.setTitle("Cerrar Sesión")
        itemCerrarSesion.setIcon(null)
        itemMiCuenta.setTitle("Mi Cuenta")
        itemMiCuenta.setIcon(null)

        setup(email ?: "",provider ?: "")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_screen, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_screen)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setup(email: String, provider: String){
        title = "Hola! Bienvenido de nuevo"
        //var txt_email =  findViewById<TextView>(R.id.txt_email);
        //var btn_logout = findViewById<Button>(R.id.btn_logout)
        //txt_email.text = email
        /*btn_logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }*/


    }
}