package com.example.conversioncalculator

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.Menu;
import android.view.MenuItem
import android.widget.TextView
import com.example.conversioncalculator.UnitsConverter.*
import android.content.Intent


class MainActivity : AppCompatActivity() {
    var isLength = true
    var fromLenUnits = LengthUnits.Yards
    var toLenUnits = LengthUnits.Meters
    var fromVolUnits = VolumeUnits.Gallons
    var toVolUnits = VolumeUnits.Liters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calcButton = findViewById<Button>(R.id.calcButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val modeButton = findViewById<Button>(R.id.modeButton)

        // set listeners for buttons
        clearButton.setOnClickListener { clearFields() }
        calcButton.setOnClickListener { if (isLength) calculateLen() else calculateVol() }
        modeButton.setOnClickListener { changeMode() }

    }

    private fun clearFields() {
        val fromInput = findViewById<EditText>(R.id.fromInput)
        val toInput = findViewById<EditText>(R.id.toInput)

        fromInput.text.clear()
        toInput.text.clear()
    }

    private fun calculateLen() {
       var enteredVal = 0.0
        val fromInput = findViewById<EditText>(R.id.fromInput)
        val toInput = findViewById<EditText>(R.id.toInput)

        // If fromInput empty or both input fields empty- display error message.
        if(fromInput.text.toString() == ""  || ( fromInput.text.toString() == ""
                        && toInput.text.toString() == "")) {
            AlertDialog.Builder(this).setTitle("Error Message").
                    setMessage("From Input Empty!")
                    .setPositiveButton("OK") { dialog, _->
                        //clearFields()
                        dialog.dismiss()
                    }.create().show()

        }

        // If toInput empty, or both are not empty only use FromInput
        if(toInput.text.toString() == "" || (fromInput.text.toString() != "" &&
                        toInput.text.toString() != "") ){
            enteredVal = (fromInput.text.toString()).toDouble()


        }

        // Length Conversion
        if(isLength) {
            var first: LengthUnits = LengthUnits.Meters
            var sec: LengthUnits = LengthUnits.Meters

            when {
                fromLenUnits.toString() == "Meters" -> first = LengthUnits.Meters
                fromLenUnits.toString() == "Yards" -> first = LengthUnits.Yards
                fromLenUnits.toString() == "Miles" -> first = LengthUnits.Miles
            }

            when {

                toLenUnits.toString() == "Meters" -> sec = LengthUnits.Meters
                toLenUnits.toString() == "Yards" -> sec = LengthUnits.Yards
                toLenUnits.toString() == "Miles" -> sec = LengthUnits.Miles
            }

            toInput.setText(convert(
                    enteredVal,
                    LengthUnits.valueOf(fromLenUnits.toString()),
                    LengthUnits.valueOf(toLenUnits.toString())).toString())
        }


    }

    private fun calculateVol() {
        var enteredVal = 0.0
        val fromInput = findViewById<EditText>(R.id.fromInput)
        val toInput = findViewById<EditText>(R.id.toInput)

        // If fromInput empty or both input fields empty- display error message.
        if(fromInput.text.toString() == ""  || ( fromInput.text.toString() == ""
                        && toInput.text.toString() == "")) {
            AlertDialog.Builder(this).setTitle("Error Message").
                    setMessage("From Input Empty!")
                    .setPositiveButton("OK") { dialog, _->
                        //clearFields()
                        dialog.dismiss()
                    }.create().show()

        }

        // If toInput empty, or both are not empty only use FromInput
        if(toInput.text.toString() == "" || (fromInput.text.toString() != "" &&
                        toInput.text.toString() != "") ){
            enteredVal = (fromInput.text.toString()).toDouble()


        }

        // Volume Conversion
        if(!isLength) {
            var first: VolumeUnits = VolumeUnits.Liters
            var sec: VolumeUnits = VolumeUnits.Liters

            when {
                fromVolUnits.toString() == "Liters" -> first = VolumeUnits.Liters
                fromVolUnits.toString() == "Gallons" -> first = VolumeUnits.Gallons
                fromVolUnits.toString() == "Quarts" -> first = VolumeUnits.Quarts
            }

            when {

                toVolUnits.toString() == "Liters" -> sec = VolumeUnits.Liters
                toVolUnits.toString() == "Gallons" -> sec = VolumeUnits.Gallons
                toVolUnits.toString() == "Quarts" -> sec = VolumeUnits.Quarts
            }

            toInput.setText(convert(
                    enteredVal,
                    VolumeUnits.valueOf(fromVolUnits.toString()),
                    VolumeUnits.valueOf(toVolUnits.toString())).toString())
        }

    }

    private fun changeMode() {
        isLength = !isLength
        val title = findViewById<TextView>(R.id.titleLabel)
        val fromUnits = findViewById<TextView>(R.id.fromUnits)
        val toUnits = findViewById<TextView>(R.id.toUnits)
        if (isLength) {
            title.text = resources.getText(R.string.lengthTitle)
            fromUnits.text = fromLenUnits.name
            toUnits.text = toLenUnits.name
        } else {
            title.text = resources.getText(R.string.volumeTitle)
            fromUnits.text = fromVolUnits.name
            toUnits.text = toVolUnits.name
        }
    }

//    override fun onCreateOptionsMenu(menu:Menu){
//        super.onCreateOptionsMenu(menu)
//        getMenuInflater.inflate(R.menu.settings, menu)
//        return true;
//
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.settings){
//            val i = Intent(MainActivity.this, Main2Activity::class.java)
//            i.putExtra("Mode", isLength)
//            startActivityForResult(i, VICE_SELECT)
//        }
//        return true
//    }
//
//    override fun onActivityResult(requestCode: int, resultCode: int, data: Intent) {
//        clearFields()
//        if(resultCode == Activity.RESULT_OK) {
//            fromUnit.setText(data.getStringExtra("FromUnit")
//            toUnit.setText(data.getStringExtra(ToUnit))
//        }
//    }
}
