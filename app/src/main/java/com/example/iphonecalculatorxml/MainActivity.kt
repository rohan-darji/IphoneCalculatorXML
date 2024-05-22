package com.example.iphonecalculatorxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.iphonecalculatorxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private var canAddDecimal = 1
    private var newOperation = false
    private var finalAns: Double? = 0.0
    private var operator = "none"
    private var oldNum = "0"

    fun numberAction(view: View) {
        try {
            if(newOperation) {
                binding.workingsTV.text = ""
                canAddDecimal = 1
            }
            newOperation = false
            val selectBut = view as Button
            var clickValueBut:String = binding.workingsTV.text.toString()

            when(selectBut.id) {
                binding.b00.id -> {
                    clickValueBut += "0"
                }
                binding.b01.id -> {
                    clickValueBut += "1"
                }
                binding.b02.id -> {
                    clickValueBut += "2"
                }
                binding.b03.id -> {
                    clickValueBut += "3"
                }
                binding.b04.id -> {
                    clickValueBut += "4"
                }
                binding.b05.id -> {
                    clickValueBut += "5"
                }
                binding.b06.id -> {
                    clickValueBut += "6"
                }
                binding.b07.id -> {
                    clickValueBut += "7"
                }
                binding.b08.id -> {
                    clickValueBut += "8"
                }
                binding.b09.id -> {
                    clickValueBut += "9"
                }
                binding.decimalBut.id -> {
                    if(canAddDecimal == 1){
                        if(clickValueBut == "") {
                            clickValueBut = "0."
                        }
                        else {
                            clickValueBut += "."
                        }
                        canAddDecimal = 0
                    }
                }

            }
            binding.workingsTV.text = clickValueBut
            finalAns = clickValueBut.toDouble()
        } catch (ex:Exception){
            Toast.makeText(this,ex.message,Toast.LENGTH_LONG).show()
        }
    }

    fun operationAction(view: View) {
        val selectBut = view as Button
        if (oldNum != "0" && binding.workingsTV.text.toString() != "" && operator != "none") {
            equalsAction(view)
        }

        when(selectBut.id) {
            binding.mulBut.id -> {
                operator = "*"
            }
            binding.divBut.id -> {
                operator = "/"
            }
            binding.addBut.id -> {
                operator = "+"
            }
            binding.subBut.id -> {
                operator = "-"
            }
        }
        oldNum = binding.workingsTV.text.toString()
        newOperation = true
        canAddDecimal = 1
    }
    fun clear(view: View) {
        binding.workingsTV.text = ""
        oldNum = "0"
        operator = "none"
        canAddDecimal = 1
        newOperation = true
    }
    fun equalsAction(view: View) {
        try {
            if(binding.workingsTV.text.toString() == ".") {
                binding.workingsTV.text = "0.0"
            }
            val newNumber = binding.workingsTV.text.toString()

            when(operator) {
                "*" -> {
                    finalAns = oldNum.toDouble() * newNumber.toDouble()
                }
                "/" -> {
                    finalAns = oldNum.toDouble() / newNumber.toDouble()
                }
                "+" -> {
                    finalAns = oldNum.toDouble() + newNumber.toDouble()
                }
                "-" -> {
                    finalAns = oldNum.toDouble() - newNumber.toDouble()
                }
                else -> {
                    finalAns = finalAns
                }
            }
            operator = "none"

            binding.workingsTV.text = finalAns.toString()
            canAddDecimal = 1
            newOperation = true
        } catch (ex:Exception) {
            Toast.makeText(this,ex.message,Toast.LENGTH_LONG).show()
        }
    }

    fun changeSign(view: View) {
        val numsign:Double = binding.workingsTV.text.toString().toDouble() * -1
        binding.workingsTV.text = numsign.toString()
        finalAns = numsign
    }

    fun percentAction(view: View) {
        val number:Double = binding.workingsTV.text.toString().toDouble() / 100
        finalAns = number
        binding.workingsTV.text = number.toString()
        canAddDecimal = 1
        newOperation = true
    }
}