package com.asser.mulchmobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar

class OrderMulchActivity : AppCompatActivity() {

    private lateinit var quantityEditText: EditText
    private lateinit var streetEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var stateEditText: EditText
    private lateinit var zipEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var mulchCostTextView: TextView
    private lateinit var salesTaxTextView: TextView
    private lateinit var deliveryChargeTextView: TextView
    private lateinit var totalTextView: TextView
    private var pricePerCubicYard = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_mulch)

        val mulchType = intent?.getStringExtra("mulchType") ?: "Default Type"
        pricePerCubicYard = intent.getDoubleExtra("pricePerCubicYard", 0.0)

        // Binding views
        val mulchTypeTextView: TextView = findViewById(R.id.mulchTypeTextView)
        val pricePerCubicYardTextView: TextView = findViewById(R.id.pricePerCubicYardTextView)
        quantityEditText = findViewById(R.id.quantityEditText)
        streetEditText = findViewById(R.id.streetEditText)
        cityEditText= findViewById(R.id.cityEditText)
        stateEditText = findViewById(R.id.stateEditText)
        zipEditText = findViewById(R.id.zipEditText)
        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        mulchCostTextView = findViewById(R.id.mulchCostTextView)
        salesTaxTextView = findViewById(R.id.salesTaxTextView)
        deliveryChargeTextView = findViewById(R.id.deliveryChargeTextView)
        totalTextView = findViewById(R.id.totalTextView)


        val backButton: Button = findViewById(R.id.backButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Setting values
        mulchTypeTextView.text = mulchType
        pricePerCubicYardTextView.text = String.format(getString(R.string.price_per_cubic_yard), pricePerCubicYard)

        quantityEditText.addTextChangedListener { calculateCosts() }
        zipEditText.addTextChangedListener { calculateCosts() }

        backButton.setOnClickListener { finish() }

        nextButton.setOnClickListener {
            if (isValidInput()) {
                val intent = Intent(this, OrderSummaryActivity::class.java).apply {
                    putExtra("mulchType", mulchType)
                    putExtra("pricePerCubicYard", pricePerCubicYard)
                    putExtra("quantity", quantityEditText.text.toString().toInt())
                    putExtra("street",streetEditText.text.toString())
                    putExtra("city",cityEditText.text.toString())
                    putExtra("state",stateEditText.text.toString())
                    putExtra("email",emailEditText.text.toString())
                    putExtra("phone",phoneEditText.text.toString())
                    putExtra("zipCode", zipEditText.text.toString())
                    putExtra("mulchCost", mulchCostTextView.text.toString())
                    putExtra("salesTax", salesTaxTextView.text.toString())
                    putExtra("deliveryCharge", deliveryChargeTextView.text.toString())
                    putExtra("totalCost", totalTextView.text.toString())
                }
                startActivity(intent)
            } else {
                Snackbar.make(it, "Please ensure all the details are filled correctly", Snackbar.LENGTH_LONG).show()
            }
        }

    }

    private fun isValidInput(): Boolean {
        // Validate the input fields here
        val quantity = quantityEditText.text.toString().toIntOrNull()
        val zipCode = zipEditText.text.toString()

        return !(quantity == null || quantity <= 0 || zipCode.length != 5)
    }

    private fun calculateCosts() {
        val quantity = quantityEditText.text.toString().toIntOrNull() ?: return
        val zipCode = zipEditText.text.toString()

        val mulchCost = pricePerCubicYard * quantity
        mulchCostTextView.text = getString(R.string.mulch_cost, mulchCost)

        val salesTax = mulchCost * 0.07 // Assuming 7% sales tax
        salesTaxTextView.text = getString(R.string.sales_tax, salesTax)

        val deliveryCharge = when (zipCode) {
            "60540" -> 25.0
            "60563" -> 30.0
            "60564", "60565", "60189" -> 35.0
            "60187", "60188", "60190" -> 40.0
            else -> 0.0
        }

        if (deliveryCharge == 0.0 && zipCode.length == 5) {
            Snackbar.make(zipEditText, R.string.delivery_unavailable_message, Snackbar.LENGTH_LONG).show()
        }

        deliveryChargeTextView.text = getString(R.string.delivery_charge, deliveryCharge)

        val totalCost = mulchCost + salesTax + deliveryCharge
        totalTextView.text = getString(R.string.total_cost, totalCost)
    }

}
