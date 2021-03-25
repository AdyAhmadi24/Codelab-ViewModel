package id.ac.unhas.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val model:QuizViewModel by viewModels()
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreat Called")
        setContentView(R.layout.activity_main)


        Log.d(TAG, "Got a QuizViewModel: $model")
        Log.d(TAG,"Got an Activity: $this")

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        val questionTextResId = model.currentQuestionText

        questionTextView.setText(questionTextResId)


        trueButton.setOnClickListener { view: View ->
            checkAnswer( true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer( false)
        }

        nextButton.setOnClickListener {
            model.moveToNext()
            questionTextView.setText(model.currentQuestionText)
        }


        }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

        private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = model.currentQuestionAnswer


        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        }

        else {
            R.string.incorrect_toast
        }
        var toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP,0,0)
        toast.show()


    }
}

