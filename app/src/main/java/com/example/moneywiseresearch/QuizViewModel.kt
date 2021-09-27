package com.example.moneywiseresearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){

    private var _quizQuestionList = MutableLiveData<List<QuizQuestion>>()
    val quizQuestionList : MutableLiveData<List<QuizQuestion>> = _quizQuestionList

    private var _currentQuizQuestion = MutableLiveData<QuizQuestion>()
    val currentQuizQuestion : MutableLiveData<QuizQuestion> = _currentQuizQuestion

    private var _questionText = MutableLiveData<String>()
    val questionText: MutableLiveData<String> = _questionText

    private var _answerOptionTexts = MutableLiveData(listOf<String>())
    val answerText: MutableLiveData<List<String>> = _answerOptionTexts

    private var _selectedAnswer= MutableLiveData<String>()
    val selectedAnswer : MutableLiveData<String> = _selectedAnswer

    fun selectAnswer(answerOptionText: String){
        //Log.d("Caroline",answerOptionText)
        _selectedAnswer.value = answerOptionText
    }

    fun retrieveQuizData(){
        var question1 = QuizQuestion(
            "What metrics are a part of your financial journey?",
            listOf("Wealth, Security, Happiness",
                "Freedom, Liberty, Happiness",
                "Cash, Money, Wealth",
                "Credit, Checkings, Loans"),
            "Wealth, Security, Happiness"
        )

        var question2 = QuizQuestion(
            "How many Time Tokens does it cost to Time Jump?",
            listOf("This is a trick question; you can't Time Jump!",
                "1 Time Token per Time Jump",
                "3 Time Tokens per Time Jump"),
            "1 Time Token per Time Jump"
        )


        var questionList : List<QuizQuestion> = listOf(
            question1, question2)
        _quizQuestionList.value = questionList
        

    }

}