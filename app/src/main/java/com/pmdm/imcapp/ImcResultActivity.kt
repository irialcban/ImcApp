package com.pmdm.imcapp

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import java.text.DecimalFormat

class ImcResultActivity : AppCompatActivity() {
    private lateinit var btnReCalculate:AppCompatButton
    private lateinit var tvResult:TextView
    private lateinit var tvTextResult:TextView
    private lateinit var tvDescription:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)

        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvTextResult = findViewById(R.id.tvTextResult)
        btnReCalculate = findViewById(R.id.btnReCalculate)
        tvDescription = findViewById(R.id.tvDescription)
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener {
            navigate2calculator()
        }
    }

    private fun setResult() {
        var imc = intent.extras?.getString("IMC").orEmpty()
        tvResult.text = imc
    }

    private fun setTextResult() {
        var imc = intent.extras?.getString("IMC").orEmpty()

        when(imc.toDouble()) {
            in 0.0 .. 18.4 -> tvTextResult.text = getString(R.string.lower)
            in 18.5 .. 24.9 -> tvTextResult.text = getString(R.string.normal)
            in 25.0 .. 29.9 -> tvTextResult.text = getString(R.string.superior)
            in 30.0 .. Double.MAX_VALUE -> tvTextResult.text = getString(R.string.obesity)
            else -> tvTextResult.text = "Error"
        }
    }

    private fun setDescription() {
        var imc = intent.extras?.getString("IMC").orEmpty()

        when(imc.toDouble()) {
            in 0.0 .. 18.4 -> tvDescription.text = getString(R.string.lower_description)
            in 18.5 .. 24.9 -> tvDescription.text = getString(R.string.normal_description)
            in 25.0 .. 29.9 -> tvDescription.text = getString(R.string.superior_description)
            in 30.0 .. Double.MAX_VALUE -> tvDescription.text = getString(R.string.obesity_description)
            else -> tvDescription.text = "Error"
        }
    }

    private fun navigate2calculator() {
        var intentICA = Intent(this,ImcCalculatorActivity::class.java)
        startActivity(intentICA)
    }

    private fun initUI() {
        setResult()
        setTextResult()
        setDescription()
    }

}