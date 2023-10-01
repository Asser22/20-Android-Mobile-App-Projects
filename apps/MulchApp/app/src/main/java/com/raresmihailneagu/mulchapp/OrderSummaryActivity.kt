package com.raresmihailneagu.mulchapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OrderSummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)


        val mulchType = intent?.getStringExtra("mulchType")
        val pricePerCubicYard = intent?.getDoubleExtra("pricePerCubicYard", 0.0)
        val quantity = intent?.getIntExtra("quantity",0)
        val street = intent?.getStringExtra("street")
        val city = intent?.getStringExtra("city")
        val state = intent?.getStringExtra("state")
        val zipCode = intent?.getStringExtra("zipCode")
        val email = intent?.getStringExtra("email")
        val phone = intent?.getStringExtra("phone")
        val mulchCost = intent?.getStringExtra("mulchCost")
        val salesTax = intent?.getStringExtra("salesTax")
        val deliveryCharge = intent?.getStringExtra("deliveryCharge")
        val totalCost = intent?.getStringExtra("totalCost")

        // Binding views
        val mulchTypeTextView: TextView = findViewById(R.id.mulchTypeTextView)
        val pricePerCubicYardTextView: TextView = findViewById(R.id.pricePerCubicYardTextView)
        val deliveryDetails: TextView = findViewById(R.id.tvDeliveryDetails)
        val address: TextView = findViewById(R.id.tvAddress)
        val contacts: TextView = findViewById(R.id.tvContacts)
        val costs: TextView = findViewById(R.id.tvCosts)

        //Setting values
        mulchTypeTextView.text = mulchType + " - $quantity cubic yards"
        pricePerCubicYardTextView.text = String.format(getString(R.string.price_per_cubic_yard), pricePerCubicYard)
        deliveryDetails.text = "Delivering $quantity cubic yards of \n$mulchType to:\n"
        address.text = "$street\n$city, $state\n$zipCode\n"
        contacts.text = "$email\n$phone\n"
        costs.text = "\t\t\t\t$mulchCost\n\t\t\t\t$salesTax\n\t\t\t\t$deliveryCharge\n\t\t\t\t$totalCost"










        // Handle button clicks
        val backButton: Button = findViewById(R.id.btnBack)
        val placeOrderButton: Button = findViewById(R.id.btnPlaceOrder)

        backButton.setOnClickListener {
            // Handle back button click
            finish()
        }

        placeOrderButton.setOnClickListener {
            // Handle place order button click
            Toast.makeText(this, "Order Placed!", Toast.LENGTH_LONG).show()
        }
    }
}

