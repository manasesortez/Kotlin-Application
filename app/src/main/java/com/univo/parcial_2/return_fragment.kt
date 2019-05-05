package com.univo.parcial_2

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class return_fragment : AppCompatActivity() {

    private lateinit var btn_Fragmento: Button
    private lateinit var btn_siguiente: Button


    private lateinit var fragment1: BlankFragment_1
    private lateinit var fragment2: BlankFragment_2
    private lateinit var fragment3: BlankFragment_3

    private var decicion = 0
    val TAG = "StateChange"

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return_fragment)

        btn_Fragmento = findViewById(R.id.btnFragmento) as Button
        btn_siguiente = findViewById(R.id.btnFragmento_siguiente) as Button

        btn_Fragmento.setOnClickListener {
            insertarFragmento(it)
        }

        btn_siguiente.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

    }
        private fun insertarFragmento(view: View){
            fragment1 = BlankFragment_1()
            fragment2 = BlankFragment_2()
            fragment3 = BlankFragment_3()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            if (decicion == 0){
                transaction.replace(R.id.container, fragment1)
                transaction.addToBackStack(null)
                transaction.commit()
                decicion++
            }

            else if (decicion == 1){
                transaction.replace(R.id.container, fragment2)
                transaction.addToBackStack(null)
                transaction.commit()
                decicion++
            }

            else if (decicion == 2){
                transaction.replace(R.id.container, fragment3)
                transaction.addToBackStack(null)
                transaction.commit()
                decicion = 0
            }

        }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show()
    }


    }

