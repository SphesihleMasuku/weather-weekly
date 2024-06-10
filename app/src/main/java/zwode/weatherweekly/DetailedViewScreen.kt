package zwode.weatherweekly

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {

    private lateinit var dayTV : TextView
    private lateinit var minTV : TextView
    private lateinit var maxTv : TextView
    private lateinit var conditionTV : TextView
    private lateinit var averageMin : TextView
    private lateinit var averageMax : TextView
    private lateinit var backBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)

        dayTV = findViewById(R.id.dayTV)
        minTV = findViewById(R.id.minTV)
        maxTv = findViewById(R.id.maxTV)
        averageMin = findViewById(R.id.averageMin)
        averageMax = findViewById(R.id.averageMax)
        conditionTV = findViewById(R.id.conditionTV)
        backBtn = findViewById(R.id.backBtn)

        val rootView2 = findViewById<LinearLayout>(R.id.rootView2)

        val drawable : AnimationDrawable = rootView2.background as AnimationDrawable
        drawable.setEnterFadeDuration(1500)
        drawable.setExitFadeDuration(2000)
        drawable.start()


        val dayArray = intent.getStringArrayExtra("dayArray") ?: emptyArray()
        val minArray = intent.getFloatArrayExtra("minArray")?.toList() ?: emptyList()
        val maxArray = intent.getFloatArrayExtra("maxArray")?.toList() ?: emptyList()
        val conditionArray = intent.getStringArrayExtra("conditionArray") ?: emptyArray()
        val averageMinTemp = minArray.average()
        val averageMaxTemp = maxArray.average()

        val usuku = StringBuilder()
        for((index,dayT) in dayArray.withIndex()){
            usuku.append("Day ${index + 1}: $dayT\n}")
        }

        val minimum = StringBuilder()
        for((index,minT) in minArray.withIndex()){
            minimum.append("Mininum Tempereture : $minT \n")
        }

        val maximum = StringBuilder()
        for((index,maxT) in maxArray.withIndex()){
            maximum.append("Maximum Tempereture : $maxT")
        }

        val weatherCondition = StringBuilder()
        for ((index,conditionT) in conditionArray.withIndex()){
            weatherCondition.append("Weather Condition  $conditionT \n")
        }

        dayTV.text = usuku.toString()
        minTV.text = minimum.toString()
        maxTv.text = maximum.toString()
        conditionTV.text = weatherCondition.toString()
        averageMin.text = averageMinTemp.toString()
        averageMax.text = averageMaxTemp.toString()


        backBtn.setOnClickListener {
            finish()
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}