package vault.voyage.app

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

class CurrencyConverterActivity : AppCompatActivity() {
    private lateinit var firstConversion:EditText
    private lateinit var secondConversion:EditText
    private lateinit var spinner_firstConversion:Spinner
    private lateinit var spinner_secondConversion:Spinner
    private lateinit var convert_button:Button
    var baseCurrency = "EUR"
    var convertedToCurrency= "USD"
    var conversionRate = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)
        init()
        spinnerSetup()
        convert_button.setOnClickListener {
            getApiResult()
        }
    }
    private fun init(){
        firstConversion = findViewById(R.id.et_firstConversion)
        secondConversion = findViewById(R.id.et_secondConversion)
        spinner_firstConversion = findViewById(R.id.spinner_firstConversion)
        spinner_secondConversion = findViewById(R.id.spinner_secondConversion)
        convert_button = findViewById(R.id.convert_button)
    }
    private fun getApiResult(){
        if(firstConversion.text.isNotEmpty() && firstConversion.text.isNotBlank()){
            val API = "https://api.exchangeratesapi.io/v1/2013-12-2n" +
                    "? access_key = b476714b3885e9427fe988fcecc2a412" +
                    "& base = " + baseCurrency
            "& symbols = " + convertedToCurrency
            if(baseCurrency==convertedToCurrency){
                TODO("SHOW TOAST")
            }else{
                GlobalScope.launch(Dispatchers.IO) {
                    try{
                        val apiResult = URL(API).readText()
                        val jsonObject = JSONObject(apiResult)
                        conversionRate = jsonObject.getJSONObject("rates").getString(convertedToCurrency).toFloat()

                        withContext(Dispatchers.Main){
                            val text = ((firstConversion.text.toString().toFloat())*conversionRate).toString()
                            secondConversion.setText(text)

                        }
                    }catch (e:Exception){

                    }
                }
            }
        }
    }
    private fun spinnerSetup(){


        ArrayAdapter.createFromResource(this,
            R.array.currencies, android.R.layout.simple_spinner_item)
            .also { adapter->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_firstConversion.adapter = adapter
            }

        ArrayAdapter.createFromResource(this,
            R.array.currencies2, android.R.layout.simple_spinner_item)
            .also { adapter->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner_secondConversion.adapter = adapter
            }

    }
}