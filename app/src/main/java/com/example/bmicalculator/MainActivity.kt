package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Linking of UI and code
        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener{
            //BMI = weight/ height in m power 2
            if(editTextWeight.text.isEmpty()){
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener // terminate program execution error
            }
            if(editTextHeight.text.isEmpty()){
                editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener // terminate program execution error
            }

            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()
            var bmi = weight/(height/100).pow(2)

            if (bmi < 18.5) { //underweight
                imageViewBMI.setImageResource(R.drawable.under)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.under))
            }
            else if (bmi in 18.5..24.9) { //normal
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
            }
            else if (bmi > 25) { //overweight
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.over))
            }
        }
        buttonReset.setOnClickListener{
            imageViewBMI.setImageResource(R.drawable.empty)
            textViewBMI.text = String.format("%s", getString(R.string.bmi))
            textViewStatus.text = String.format("%s", getString(R.string.status))
            editTextWeight.text.clear()
            editTextHeight.text.clear()
        }
    }
}