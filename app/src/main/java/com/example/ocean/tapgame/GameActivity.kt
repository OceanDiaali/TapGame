package com.example.ocean.tapgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    var count: Int = 0
    var hiScore: Int = 0

    var imageArray = ArrayList<ImageView>()


    var handler: Handler = Handler()
    var runnable: Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        imageArray = arrayListOf( boy1, boy2, boy3, boy4, boy5, boy6, boy7, boy8, boy9)

        controlImages() //images will appear/disappear randomly when game begins

        countDownTimer


    } // end of on create

    fun controlImages() {

        runnable = object : Runnable {

            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                    var random = (0..8).random()

                    imageArray[random].visibility = View.VISIBLE

                    imageArray[random].setOnClickListener { scoreIncrement() }

                    handler.postDelayed(runnable, 1000)

            } // end of run function
        } // runnable

               handler.post(runnable)

    } // end of control images

    fun scoreIncrement() {
            count++
            score_view.setText(count.toString())
        if (count > hiScore) {
            hiScore = count
        }
    } // score increment function


        // count down will start on launch
        var countDownTimer = object : CountDownTimer(21000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer.setText((millisUntilFinished / 1000).toString())

            }

            override fun onFinish() {
                timer.setText("Game Over")
                hi_score_view.setText("HiScore:\n$hiScore")
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
            }
        }.start() // end of the timer block

} // end of class
