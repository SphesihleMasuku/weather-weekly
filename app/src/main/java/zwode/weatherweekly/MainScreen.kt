package zwode.weatherweekly

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.nfc.FormatException
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.IllegalFormatException

class MainScreen : AppCompatActivity() {

    private lateinit var day : EditText
    private lateinit var min : EditText
    private lateinit var max : EditText
    private lateinit var condition : EditText
    private lateinit var error : TextView
    private lateinit var saveBtn : Button
    private lateinit var clearBtn : Button
    private lateinit var nextBtn : Button

    private val dayArray = mutableListOf<String>()
    private val minArray = mutableListOf<Float>()
    private val maxArray = mutableListOf<Float>()
    private val conditionArray = mutableListOf<String>()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)

        day = findViewById(R.id.day)
        min = findViewById(R.id.min)
        max = findViewById(R.id.max)
        condition = findViewById(R.id.condition)
        error = findViewById(R.id.error)
        saveBtn = findViewById(R.id.saveBtn)
        clearBtn = findViewById(R.id.clearBtn)
        nextBtn = findViewById(R.id.nextBtn)

        val rootView = findViewById<ConstraintLayout>(R.id.rootView)

        val drawable : AnimationDrawable = rootView.background as AnimationDrawable
        drawable.setEnterFadeDuration(1500)
        drawable.setExitFadeDuration(2000)
        drawable.start()

        clearBtn.setOnClickListener {
            day.setText("")
            min.setText("")
            max.setText("")
            condition.setText("")
            error.setText("")
        }

        saveBtn.setOnClickListener {
            val dayOfWeek = day.text.toString()
            val minTemp = min.text.toString()
            val maxTemp = max.text.toString()
            val weatherCondition = condition.text.toString()

            if (dayOfWeek.isNotEmpty() && minTemp.isNotEmpty() && maxTemp.isNotEmpty() && weatherCondition.isNotEmpty())
                try {
                    dayArray.add((dayOfWeek))
                    minArray.add(minTemp.toFloat())
                    maxArray.add(maxTemp.toFloat())
                    conditionArray.add((weatherCondition))
                    day.text.clear()
                   min.text.clear()
                   max.text.clear()
                    condition.text.clear()
                } catch (e:NumberFormatException) {
                    error.text = "Please enter a valid number"
                } else {
                error.text = "Input cannot be empty"
            }
        }

        nextBtn.setOnClickListener {
            val intent = Intent(this, DetailedViewScreen::class.java)
            intent.putExtra("dayArray", dayArray.toTypedArray())
            intent.putExtra("minArray", minArray.toFloatArray())
            intent.putExtra("maxArray", maxArray.toFloatArray())
            intent.putExtra("conditionArray", conditionArray.toTypedArray())
            startActivity(intent)
        }










        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}