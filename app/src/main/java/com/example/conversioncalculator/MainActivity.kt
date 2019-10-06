package com.example.conversioncalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var isLength = true
    var fromLenUnits = UnitsConverter.LengthUnits.Yards
    var toLenUnits = UnitsConverter.LengthUnits.Meters
    var fromVolUnits = UnitsConverter.VolumeUnits.Gallons
    var toVolUnits = UnitsConverter.VolumeUnits.Liters

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

    }

    private fun calculateVol() {

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
}
