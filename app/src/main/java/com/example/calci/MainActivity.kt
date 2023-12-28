package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // create variable for getting id
   private var getId : TextView ? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getId = findViewById<TextView>(R.id.tvID)
    }

        fun onDigit (view : View){
       // Toast.makeText(this, "here button clicked", Toast.LENGTH_SHORT).show()
//           getId?.text = ""

          getId?.append((view as Button).text)
         lastNumeric = true
            lastDot = false
        }


       fun onClear(view: View){
           getId?.text = ""
         //  getId?.append("SachinNalla")
           //getId?.append("0")
       }

    fun onDecimalPoint(view:View){
       if(lastNumeric && !lastDot){
           getId?.append(".")
           lastNumeric = false
           lastDot = true
       }
    }

    fun onOperator(view: View) {
        getId?.text?.let {  // if text view is not null or empty
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                getId?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }


    fun onEqual(view: View){
        if(lastNumeric ) {
            var tvValue = getId?.text.toString()

            var prefix = " "
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    getId?.text = RemoveZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    getId?.text = RemoveZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    getId?.text = RemoveZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    getId?.text = RemoveZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }


   private  fun RemoveZeroAfterDot(result : String) : String{
       var value = result
       if(result.contains(".0")){
           value = result.substring(0,result.length-2)
       }
       return value
   }


    private fun isOperatorAdded(value:String) : Boolean {
           return if(value.startsWith("-")){ // we ignore the value which is started with -
               false
           }
        else {
               value.contains("/")
                       || value.contains("*")
                       || value.contains("+")
                       || value.contains("-")
           }
           }
    }