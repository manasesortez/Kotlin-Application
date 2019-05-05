package com.univo.parcial_2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class splash_screen : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, return_fragment::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
    }



    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
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


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show()
    }


}
