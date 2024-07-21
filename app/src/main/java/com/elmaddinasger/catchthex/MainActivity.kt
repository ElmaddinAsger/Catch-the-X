package com.elmaddinasger.catchthex

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var txtTime : TextView
    private lateinit var txtScore : TextView
    private lateinit var imgX : List <ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgX = listOf(
            findViewById(R.id.xZero),
            findViewById(R.id.xOne),
            findViewById(R.id.xTwo),
            findViewById(R.id.xThree),
            findViewById(R.id.xFour),
            findViewById(R.id.xFive),
            findViewById(R.id.xSix),
            findViewById(R.id.xSeven),
            findViewById(R.id.xEight),
            findViewById(R.id.xNine),
            findViewById(R.id.xTen),
            findViewById(R.id.xEleven),
            findViewById(R.id.xTwelve),
            findViewById(R.id.xThirteen),
            findViewById(R.id.xFourteen),
            findViewById(R.id.xFifteen),
            findViewById(R.id.xSixteen),
            findViewById(R.id.xSeventeen),
            findViewById(R.id.xEighteen),
            findViewById(R.id.xNineteen),
            findViewById(R.id.xTwenty),
            findViewById(R.id.xTwentyOne),
            findViewById(R.id.xTwentyTwo),
            findViewById(R.id.xTwentyThree),
            findViewById(R.id.xTwentyFour),
        )

        txtTime = findViewById(R.id.txtTime)
        txtScore = findViewById(R.id.txtScore)


    }


    override fun onResume() {
        super.onResume()

        var score = 0

        var newIndex: Int= -1
        val timer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                if ((newIndex != -1) && (imgX[newIndex].visibility == View.VISIBLE))
                    showHide(imgX[newIndex])

                newIndex  = Random.nextInt(0, 25)

                showHide(imgX[newIndex])


                imgX[newIndex].setOnClickListener{
                    score ++
                    txtScore.text = "Score: $score"
                    showHide(imgX[newIndex])
                }


                txtTime.text = "Time: ${millisUntilFinished/1000}"

            }

            override fun onFinish() {
                txtTime.text = "Time: Finised"
                showSelectionDialog()


            }
        }
        timer.start()
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }
    private fun showSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Time finished")
        builder.setMessage("Would you like:")
        builder.setPositiveButton("Restart") { dialog, _ ->
            val intent = intent
            finish()
            startActivity(intent)
            dialog.dismiss()
        }
        builder.setNegativeButton("Leave") { dialog, _ ->
            this.finish()
            dialog.dismiss()
        }
        builder.create().show()
    }


}