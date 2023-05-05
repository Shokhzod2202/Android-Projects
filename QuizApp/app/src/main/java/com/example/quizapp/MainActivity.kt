package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isStartAgain = intent.getBooleanExtra(Constants.IS_START_AGAIN,false)

        //Converting Chosen item to the editable text view item:
        val btnStart: Button = findViewById(R.id.btnStart)
        //Converting Chosen item to the editable text view item.
        val etName: EditText = findViewById(R.id.et_name)
        val btnQuit: Button = findViewById(R.id.btn_quit)

        if(isStartAgain){
            btnStart.text = "START AGAIN"
        }



        btnQuit.setOnClickListener {
            finish()
        }

        // Setting On Click Listener to the Button:
        btnStart.setOnClickListener{

            //On Click, If Editable text view is empty -> Make Toast, else -> StartActivity(intent):
            if(etName.text.isEmpty()){
                Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show()
            }else{
                //Send to the next page
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etName.text.toString())
                startActivity(intent)
                finish()
            }
                
        }
    }
}