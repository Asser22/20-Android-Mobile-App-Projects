package com.asser.mulchapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asser.mulchapp.R

class MainActivity : AppCompatActivity() {

    data class MulchType(val name: String, val price: Double)

    private val mulchTypes = listOf(
        MulchType("Premium Bark Mulch", 56.0),
        MulchType("Special Blend", 35.0),
        MulchType("Triple Ground", 40.0),
        MulchType("Chocolate Dyed", 38.0),
        MulchType("Red Dyed", 38.0),
        MulchType("Black Dyed", 38.0),
        MulchType("Play Mat", 38.0),
        MulchType("Cedar", 38.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton: Button = findViewById(R.id.nextButton)
        val mulchTypesRadioGroup: RadioGroup = findViewById(R.id.mulchTypesRadioGroup)

        nextButton.setOnClickListener {
            val selectedMulchTypeId = mulchTypesRadioGroup.checkedRadioButtonId

            if (selectedMulchTypeId != -1) {  // -1 means no selection
                val selectedRadioButton = findViewById<RadioButton>(selectedMulchTypeId)
                val selectedMulchTypeIndex = mulchTypesRadioGroup.indexOfChild(selectedRadioButton)
                val selectedMulchType = mulchTypes[selectedMulchTypeIndex]

                val intent = Intent(this, OrderMulchActivity::class.java)
                intent.putExtra("mulchType", selectedMulchType.name)
                intent.putExtra("pricePerCubicYard", selectedMulchType.price)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a mulch type", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

