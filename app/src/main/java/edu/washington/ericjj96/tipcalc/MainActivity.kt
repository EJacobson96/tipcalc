package edu.washington.ericjj96.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipButton.isEnabled = false

        this.amount.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                  count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                when {
                    s.toString().isEmpty() -> {
                        amount.setText("$")
                    }
                    !s.toString().startsWith("$") -> {
                        amount.setText("$$s")
                    }
                    s.toString().contains(".") -> {
                        var splitString = s.toString().split(".")
                        if (splitString[1].length > 2) {
                            var prefix = splitString[0]
                            var num = splitString[1]
                            var decimals = splitString[1].slice(0..1)
                            amount.setText(prefix + "." + decimals.toString())
                        }
                    }
                }
                tipButton.isEnabled = s.toString().length > 1
                amount.setSelection(amount.text.length)
                if (tipButton.isEnabled) {
                    tipButton.setOnClickListener {
                        var currentAmount: Double = s.toString().replace("$", "").toDouble()
                        var value = tipSpinner.getSelectedItem().toString()
                        var tipPercent = 0.15
                        when (value) {
                            "10%" -> {
                                tipPercent = 0.10
                            }
                            "15%" -> {
                                tipPercent = 0.15
                            }
                            "18%" -> {
                                tipPercent = 0.18
                            }
                            "20%" -> {
                                tipPercent = 0.20
                            }

                        }
                        var tip = currentAmount * tipPercent
                        Toast.makeText(this@MainActivity, "$" + "%.2f".format(tip), Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }


}
