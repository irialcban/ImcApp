package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private var isMaleSelected:Boolean = true
    private lateinit var tvWeight: TextView
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private var isAddWeightSelected:Boolean = true
    private var isAddAgeSelected:Boolean = true
    private var age:Int = 0
    private var weight:Int = 0
    private lateinit var btnCalculate: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {

        viewMale.setOnClickListener {
            isMaleSelected = true
            setGenderColor()
        }

        viewFemale.setOnClickListener {
            isMaleSelected = false
            setGenderColor()
        }

        rsHeight.addOnChangeListener {_,value,_ ->
            tvHeight.text = DecimalFormat("#.##").format(value) + "cm"
        }

        btnSubstractWeight.setOnClickListener {
            isAddWeightSelected = false
            setWeight()
        }

        btnAddWeight.setOnClickListener {
            isAddWeightSelected = true
            setWeight()
        }

        btnSubstractAge.setOnClickListener {
            isAddAgeSelected = false
            setAge()
        }

        btnAddAge.setOnClickListener {
            isAddAgeSelected = true
            setAge()
        }

        btnCalculate.setOnClickListener {
            navigate2result()
        }
    }

    private fun getBackgrounColor(isComponentSelected: Boolean): Int {
        val colorReference = if(isComponentSelected) {
            R.color.bg_comp_sel
        }else {
            R.color.bg_comp
        }

        return ContextCompat.getColor(this,colorReference)
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgrounColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgrounColor(!isMaleSelected))
    }

    private fun setWeight() {
        if(isAddWeightSelected) {
            weight = weight + 1
        }else {
            if(weight > 0) {
                weight = weight - 1
            }
        }

        tvWeight.text = DecimalFormat("#.##").format(weight)
    }

    private fun setAge() {
        if(isAddAgeSelected) {
            age = age + 1
        }else {
            if(age > 0) {
                age = age - 1
            }
        }

        tvAge.text = DecimalFormat("#.##").format(age)
    }

    private fun calculateIMC(): Double {
        var height = tvHeight.text.toString().subSequence(0,(tvHeight.text.toString()).length-2)

        var height_meters:Double = height.toString().toDouble()/100

        return (weight/(height_meters*height_meters))
    }

    private fun navigate2result() {
        val intentIRA = Intent(this,ImcResultActivity::class.java)
        var result = calculateIMC().toString()
        intentIRA.putExtra("IMC",result)
        startActivity(intentIRA)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

}