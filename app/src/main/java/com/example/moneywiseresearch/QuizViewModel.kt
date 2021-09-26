package com.example.moneywiseresearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){

    private var _questionText = MutableLiveData<String>()
    val questionText: MutableLiveData<String> = _questionText

    private var _answerOptionTexts = MutableLiveData(listOf<String>())
    val answerText: MutableLiveData<List<String>> = _answerOptionTexts

    private var _selectedAnswer= MutableLiveData<String>()
    val selectedAnswer : MutableLiveData<String> = _selectedAnswer

    fun selectAnswer(answerOptionText: String){
        Log.d("Caroline",answerOptionText)
        _selectedAnswer.value = answerOptionText
    }

    fun retrieveQuizData(){
        var answerOption1 = "Wealth, Security, Happiness"
        var answerOption2 = "Freedom, Liberty, Happiness"
        var answerOption3 = "Cash, Money, Wealth"
        var answerOption4 = "Credit, Checkings, Loans"
        var answerOptions : List<String> = listOf(
            answerOption1,answerOption2,answerOption3,answerOption4)
        _answerOptionTexts.value = answerOptions
        _questionText.value = "What financial values are important to you?"
    }

}