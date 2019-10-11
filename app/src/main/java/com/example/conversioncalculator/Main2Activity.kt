package com.example.conversioncalculator


import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Main2Activity : AppCompatActivity() {

    var spin: Spinner? = null
    private var spin2: Spinner? = null
    private var doneButton: FloatingActionButton? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.Layout.Main2Activity)
        spin = findViewById(R.id.spin)
        spin2 = findViewById(R.id.spin2)
        doneButton = findViewById(R.id.doneButton)

        val data:Bundle? = intent.extras

        val mode: String? = data?.getString("Mode")
        val options= arrayListOf<String>()


        if(mode.equals("Length")) {
            options.add("Meters")
            options.add("Yards")
            options.add("Miles")

        } else {
            options.add("Gallons")
            options.add("Liters")
            options.add("Quarts")
        }

        val select = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options)
        select.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spin?.setAdapter(select)
        spin2?.setAdapter(select)
        doneButton?.setOnClickListener { retChange() }
    }

    fun retChange() {
        val result = Intent(this, Main2Activity::class.java)
        result.putExtra("FromUnit", spin?.selectedItem.toString())
        result.putExtra("ToUnit", spin2?.selectedItem.toString())
        setResult(AppCompatActivity.RESULT_OK, result)
        finish()
    }


}
