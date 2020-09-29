package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<View>(R.id.buttonRoll) as Button

        rollButton.setOnClickListener {
            val nbFacesView = findViewById<EditText>(R.id.nbFaces)
            try {
                val nbFaces = nbFacesView.text.toString().toInt() //On va chercher le text de l'EditText tout en le changeant en int
                val de1 = findViewById<TextView>(R.id.de1) // On va chercher le TextView avec son ID en l'instanciant en tant que TextView
                de1.text = Random.nextInt(1,nbFaces+1).toString() // On change le text par un entier entre 1 et le nombre de faces demandé

                val de2 = findViewById<TextView>(R.id.de2)
                de2.text = Random.nextInt(1,nbFaces+1).toString()
            }catch (e : Exception){
                val toast = Toast.makeText(this@MainActivity, "Le nombre de face doit être entier", Toast.LENGTH_SHORT)
                toast.show()
            }


        }
    }
}