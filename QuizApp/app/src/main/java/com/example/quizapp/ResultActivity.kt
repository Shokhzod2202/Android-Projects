package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private var isStartAgain = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var tvName: TextView = findViewById(R.id.tv_name)
        var tvScore: TextView = findViewById(R.id.tv_score)
        var btnFinish: Button = findViewById(R.id.btn_finish)

        tvName?.text = intent.getStringExtra(Constants.USER_NAME)

        //tvScore?.text = "Your Score is ${intent.getStringExtra(Constants.CORRECT_ANSWERS)} out of ${intent.getStringExtra(Constants.TOTAL_QUESTIONS)}"
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvScore.text = "Your score is $correctAnswers out of $totalQuestions"

        btnFinish.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Constants.IS_START_AGAIN, isStartAgain)
            startActivity(intent)
            finish()
        }
    }
}