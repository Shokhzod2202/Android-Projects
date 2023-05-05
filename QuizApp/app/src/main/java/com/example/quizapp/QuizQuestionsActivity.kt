package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    //Global Variables:
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedAnswerPosition: Int = 0
    private var mUserName : String? = null
    private var mCorrectAnswers: Int = 0

    //Creating needed variables:
    private var progressBar: ProgressBar? = null
    private var tvProgressBar : TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImageView: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null

    //Setting the page:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Connecting this page to the activity_quiz_questions.xml file,
        //Setting the UI:
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        //Connecting our variables to the UI elements:
        progressBar = findViewById(R.id.progresBar)
        tvProgressBar = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImageView = findViewById(R.id.iv_image)

        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btnSubmit)

        //Setting On Click Listeners:
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        //Getting the questions:
        mQuestionList = Constants.getQuestions()

        //Showing the Questions on the screen step-by-step with position.
        setQuestion()


    }

    //Declaring a function that shows the Question on screen according to it's position.
    private fun setQuestion() {
        //Setting the default options view:
        defaultOptionsView()
        //Get the question:
        val question: Question = mQuestionList!![mCurrentPosition - 1]

        //Setting the progress bar:
        progressBar?.progress = mCurrentPosition
        tvProgressBar?.text = "$mCurrentPosition/${progressBar?.max}"

        //Projecting the Question Stored mQuestionList
        tvQuestion?.text = question.question
        //Setting the Image:
        ivImageView?.setImageResource(question.image)
        //Projecting the options:
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        //Change the Submit button according to the question positioin:
        if(mCurrentPosition==mQuestionList!!.size) {
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }

    }

    // Declaring a function that sets the default style to the Options:
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let{
            options.add(0, it)
        }
        tvOptionTwo?.let{
            options.add(1, it)
        }
        tvOptionThree?.let{
            options.add(2, it)
        }
        tvOptionFour?.let{
            options.add(3, it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor(("#7A8089")))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }

    // Declaring a function that sets the different style to the Selected Option:
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedAnswerPosition = selectedOptionNum
        //Changes the color of the selected text:
        tv.setTextColor(Color.parseColor("#363A43"))
        //Changes the style of the selected text:
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        //Changes the background type of the selected text:
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }

    //Specifying the Action needed to take if Clicked:
    override fun onClick(view: View?) {
        when(view?.id){
            //If Option One is chosen:
            R.id.tv_option_one -> {
                tvOptionOne?.let{
                    selectedOptionView(it,1)
                }
            }
            //If Option Two is chosen:
            R.id.tv_option_two -> {
                tvOptionTwo?.let{
                    selectedOptionView(it,2)
                }
            }
            //If Option Three is chosen:
            R.id.tv_option_three -> {
                tvOptionThree?.let{
                    selectedOptionView(it,3)
                }
            }
            //If Option Four is chosen:
            R.id.tv_option_four -> {
                tvOptionFour?.let{
                    selectedOptionView(it,4)
                }
            }
            //If Submit button is clicked:
            R.id.btnSubmit ->{
                //Checking whether it is the last question or the first question:
                if(mSelectedAnswerPosition == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }else->{
                            //Sending intent to the next activity, which is result in this case:
                            val intent = Intent(this, ResultActivity::class.java)
                            //Putting USER_NAME as an intent extra
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            //Putting CORRECT_ANSWER as an intent extra
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            //Putting TOTAL_QUESTIONS as an intent extra
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                //if it is not the LAST question or the FIRST question:
                else{
                    val question = mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer != mSelectedAnswerPosition){
                        answerView(mSelectedAnswerPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question?.correctAnswer, R.drawable.correct_option_border_bg)
                    if(mCurrentPosition == mQuestionList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedAnswerPosition = 0
                }
            }
        }
    }

    //This function Changes the background color of the Options TextView/Button
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                //Sets any drawable color file to the tvOptionOne
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                //Sets any drawable color file to the tvOptionTwo
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}