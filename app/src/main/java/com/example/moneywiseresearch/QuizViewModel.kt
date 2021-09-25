package com.example.moneywiseresearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){

    private var _questionText = MutableLiveData<String>()
    val questionText: MutableLiveData<String> = _questionText

    private var _answerOptionTexts = MutableLiveData(listOf<String>())
    val answerText: MutableLiveData<List<String>> = _answerOptionTexts

    fun selectAnswer(answerOptionText: String){

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