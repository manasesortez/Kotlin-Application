package com.univo.parcial_2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var mAdapter : ArrayAdapter<String>? = null
    private var mCityArray : ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        val mListView = findViewById(R.id.listViewCities) as ListView
        val txtTemp = findViewById(R.id.textViewTemperatura) as TextView
        val txtDesc = findViewById(R.id.textViewDescription) as TextView
        val imgWeahter = findViewById(R.id.imageViewWeather) as ImageView
        val txtCityName = findViewById(R.id.textViewCityName) as TextView

        GetWeatherTask(this, txtTemp, txtDesc, imgWeahter)
                .execute(resources.getString(R.string.JsonURL)
                        .replace("#CITY#", resources.getString(R.string.ciudadInicial), false))

        txtCityName.text = resources.getString(R.string.ciudadInicial)


        val iarr : Array<String> = resources.getStringArray(R.array.cities_array)

        mCityArray = ArrayList<String>(iarr.asList())

        mAdapter = ArrayAdapter<String>(this,
                                R.layout.list_row,
                                R.id.lblCityName,
                                mCityArray)
        mListView.adapter = mAdapter

        mListView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->

            val sCityName = parent.getItemAtPosition(position) as String

            txtCityName.text = sCityName

            GetWeatherTask(parent.context, txtTemp, txtDesc, imgWeahter).execute(resources.getString(R.string.JsonURL).replace("#CITY#", sCityName, false))
        }

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {

        R.id.menuAbout -> menuItemCaller {
            startActivity(Intent(this, Acerca::class.java))
        }
        R.id.menuAgregarCiudad ->  menuItemCaller {
            startActivityForResult(Intent(this, AgregarCiudad::class.java), 100)
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun menuItemCaller(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)  {

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {

            if(data != null) {

                mCityArray?.add(data.getStringExtra("nombreCiudad"))
            }

            mAdapter!!.notifyDataSetChanged()
        }
    }

    class GetWeatherTask(var context : Context, var tvTemperature: TextView,
                         var tvDescription: TextView,
                         var imgWeather: ImageView) : AsyncTask<String, Unit, String>() {

        override fun doInBackground(vararg params: String?): String? {

            val inputStream : InputStream
            var result = ""

            try {
                inputStream = URL(params[0]).openStream()

                if(inputStream != null) {
                    val buffer = BufferedReader (InputStreamReader(inputStream))
                    val stringBuilder = StringBuilder()
                    buffer.forEachLine { stringBuilder.append(it) }

                    result = stringBuilder.toString()
                }

            } catch (e : Exception) {
                Log.d("InputStream", e.localizedMessage)
            }

            return result
        }

        override fun onPostExecute(result: String?) {

            try {
                super.onPostExecute(result)

                val json = JSONObject(result)

                val json_main = json.getJSONObject("main")
                val json_array_weather = json.getJSONArray("weather")

                val temp_kelvin = json_main.getDouble("temp")
                val pressure = json_main.getLong("pressure")
                val humidity = json_main.getLong("humidity")

                var temp_description = ""
                var temp_icon = ""

                for (i in 0..json_array_weather.length() - 1) {

                    val json_weather = json_array_weather.getJSONObject(i)

                    temp_description = json_weather.getString("description")
                    temp_icon = json_weather.getString("icon")
                }

                tvTemperature.text = kelvinConversion(temp_kelvin)
                tvDescription.text = temp_description.capitalize()
                imgWeather.setImageResource(context.resources.getIdentifier("weather_" + temp_icon, "drawable", context.packageName))
            } catch (e: Exception) {
                Toast.makeText(context, context.resources.getString(R.string.AsyncTaskError), Toast.LENGTH_LONG).show()
            }                                                                                                                                    }

        fun kelvinConversion(kelvin_temperature : Double): String {

            val temperaturaKFloat = "%.2f".format((kelvin_temperature.toFloat()) - 273.15)

            return temperaturaKFloat
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
