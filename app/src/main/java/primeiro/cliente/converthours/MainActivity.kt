package primeiro.cliente.converthours

import android.icu.number.FormattedNumber
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input         = findViewById<EditText>(R.id.input)
        val btn_converter = findViewById<Button>(R.id.btn_converter)
        val btn_clear     = findViewById<Button>(R.id.btn_clear)
        val resultado     = findViewById<TextView>(R.id.resultado)
        val pm            = findViewById<RadioButton>(R.id.pm)
        val am            = findViewById<RadioButton>(R.id.am)
        var resultado_final: Int

        input.addTextChangedListener(Mask.mask("##:##", input))

        btn_clear.setOnClickListener {
            input.text.clear()
            resultado.text = ""
        }
        btn_converter.setOnClickListener {
            if(!input.text.isNullOrEmpty()) {
                resultado_final = input.text.toString().replace(":", "").toInt()
                if(resultado_final in 0..1200){
                    if(am.isChecked){
                        if(resultado_final == 1200) resultado.text = formattedHour(resultado_final - 1200) else resultado.text = input.text.toString()
                    }
                    if(pm.isChecked){
                        if(resultado_final != 1200) resultado.text = formattedHour(resultado_final + 1200) else resultado.text = input.text.toString()
                    }
                }else{
                    input.error = "Digite uma hora válida"
                }
            }
        }
    }
    fun formattedHour(num: Int): String {
        var numFormatted: String
        if(num == 0){
            return "00:00"
        }
        numFormatted = num.toString()
            return "${numFormatted[0]}${numFormatted[1]}:${numFormatted[2]}${numFormatted[3]}"
    }
}