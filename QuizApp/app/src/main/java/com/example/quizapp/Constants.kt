package com.example.quizapp

object Constants {

    const val USER_NAME :   String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val IS_START_AGAIN: String = "is_start_again"




    //Declaring a function that returns an arraylist of Question Class
    fun getQuestions(): ArrayList<Question>{

        //Creating the ArrayList of  Questions:
        val questionList = ArrayList<Question>()

        //Creating the variable of Question Class
        val que1 = Question(
            1,"What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        //Adding the newly created Question Class to the Arraylist
        questionList.add(que1)

        //2
        val que2 = Question(
            2,"What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Austria",
            "Australia",
            "Argentina",
            "Armenia",

            2
        )
        questionList.add(que2)

        //3
        val que3 = Question(
            3,"What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Austria",
            "Australia",
            "Belgium",
            "Armenia",

            3
        )
        questionList.add(que3)

        //4
        val que4 = Question(
            4,"What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Austria",
            "Brazil",
            "Argentina",
            "Armenia",

            2
        )
        questionList.add(que4)

        //5
        val que5 = Question(
            5,"What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Denmark",
            "Australia",
            "Argentina",
            "Armenia",

            1
        )
        questionList.add(que5)

        //6
        val que6 = Question(
            6,"What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Austria",
            "Australia",
            "Argentina",
            "Fiji",

            4
        )
        questionList.add(que6)

        //7
        val que7 = Question(
            7,"What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Germany",
            "Australia",
            "Argentina",
            "Armenia",

            1
        )
        questionList.add(que7)

        //8
        val que8 = Question(
            8,"What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Germany",
            "New Zealand",
            "Argentina",
            "Armenia",

            2
        )
        questionList.add(que8)

        //9
        val que9 = Question(
            9,"What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Germany",
            "Australia",
            "Argentina",
            "India",

            4
        )
        questionList.add(que9)

        //10
        val que10 = Question(
            10,"What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Germany",
            "Australia",
            "Kuwait",
            "Armenia",

            3
        )
        questionList.add(que10)


        return questionList
    }


}