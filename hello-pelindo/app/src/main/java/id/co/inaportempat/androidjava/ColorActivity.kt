package id.co.inaportempat.androidjava

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_color.*

class ColorActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        //casting views
       /* val boxOne = findViewById<TextView>(R.id.boxOne)
        val buttonYellow = findViewById<Button>(R.id.buttonYellow)*/

        buttonYellow.setOnClickListener(this)
        buttonRed.setOnClickListener(this)
        buttonGreen.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.buttonGreen -> coloringBox(resources.getColor(R.color.greenBox))
            R.id.buttonYellow -> coloringBox(resources.getColor(R.color.yellowBox))
            R.id.buttonRed -> coloringBox(resources.getColor(R.color.redBox))
        }
    }

    private fun coloringBox(color: Int) {
        boxOne.setBackgroundColor(color)
        boxTwo.setBackgroundColor(color)
        boxThree.setBackgroundColor(color)
        boxFour.setBackgroundColor(color)
        boxFive.setBackgroundColor(color)
    }
}
