package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    //If the button is a Digit value:
    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        //Leaving a message about the last entry:
        lastNumeric = true
        lastDot = false
    }

    //If the button is a Clear Button:
    fun onDel(view: View){
        var tempo = (tvInput?.text).toString()
        if(tempo != "")
            tvInput?.text = tempo?.substring(0,tempo.length-1)
    }

    //If the button is a Clear Button:
    fun onClear(view: View){
        tvInput?.text = ""
    }

    //If the button is a Decimal Point Button:
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            //Leaving a message about the last entry:
            lastNumeric = false
            lastDot = true
        }
    }

    //If the button is an Operation Button:
    fun onOperation(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                //Leaving a message about the last entry
                lastNumeric = false
                lastDot = false
            }
        }
    }
    private fun removeZeroAfterDot(result: String):String{
        var value = result
        if(result.contains("0")){
            value = result.substring(0,result.length - 2)
        }
        return value

    }
    //Checker whether the String contains an operation other than negative sign in the beginning.
    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    //Calculations if the button pressed is an Equal Button:
    fun onEqual(view: View){
        if(lastNumeric){
            //Store the values in the screen to the separate variable
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            //Checking Based on the Operator and Calculating the answer:
            try{

                //Checking for Negative operator in the beginning to ELIMINATE IT.:
                if(tvValue.startsWith(("-"))){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                // Check for MINUS Operator:
                if(tvValue.contains("-")){
                    //Splitting values:
                    val splitValue = tvValue.split("-")
                    var firstHalf = splitValue[0]
                    var secondHalf = splitValue[1]

                    //Putting the negative sign back to the first half:
                    if(prefix.isNotEmpty()){
                        firstHalf = prefix + firstHalf
                    }

                    //Calculation of the result:
                    tvInput?.text = removeZeroAfterDot((firstHalf.toDouble() - secondHalf.toDouble()).toString())

                }
                // Check for PLUS Operator:
                else if(tvValue.contains("+")){
                    //Splitting values:
                    val splitValue = tvValue.split("+")
                    var firstHalf = splitValue[0]
                    var secondHalf = splitValue[1]

                    //Putting the negative sign back to the first half:
                    if(prefix.isNotEmpty()){
                        firstHalf = prefix + firstHalf
                    }

                    //Calculation of the result:
                    tvInput?.text = removeZeroAfterDot((firstHalf.toDouble() + secondHalf.toDouble()).toString())

                }
                // Check for DIVIDE Operator:
                else if(tvValue.contains("/")){
                    //Splitting values:
                    val splitValue = tvValue.split("/")
                    var firstHalf = splitValue[0]
                    var secondHalf = splitValue[1]

                    //Putting the negative sign back to the first half:
                    if(prefix.isNotEmpty()){
                        firstHalf = prefix + firstHalf
                    }

                    //Calculation of the result:
                    tvInput?.text = removeZeroAfterDot((firstHalf.toDouble() / secondHalf.toDouble()).toString())

                }
                // Check for MULTIPLY Operator:
                else if(tvValue.contains("*")){
                    //Splitting values:
                    val splitValue = tvValue.split("*")
                    var firstHalf = splitValue[0]
                    var secondHalf = splitValue[1]

                    //Putting the negative sign back to the first half:
                    if(prefix.isNotEmpty()){
                        firstHalf = prefix + firstHalf
                    }

                    //Calculation of the result:
                    tvInput?.text = removeZeroAfterDot((firstHalf.toDouble() * secondHalf.toDouble()).toString())

                }

            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}

