package com.example.moneywiseresearch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moneywiseresearch.codeLabPractice.*

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        val quizViewModel by viewModels<QuizViewModel>()

        super.onCreate(savedInstanceState)
        setContent {
           MyApp(
               { MoneyWiseResearchApp(quizViewModel) }
           )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MoneyWiseResearchApp(quizViewModel:QuizViewModel) {
    val navController = rememberNavController()

    Scaffold(

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MoneyWiseScreens.Splash.name
        ) {

            //define routes
            composable(MoneyWiseScreens.Splash.name){
                SplashBody(){
                    navController.navigate(MoneyWiseScreens.TapToBegin.name){
                        popUpTo(MoneyWiseScreens.Splash.name) {inclusive =true}
                    }
                }
            }
            composable(MoneyWiseScreens.TapToBegin.name){
                TapToBegin({navController.navigate(MoneyWiseScreens.QuizScreen1.name)})
            }
            composable(MoneyWiseScreens.QuizScreen1.name) {
                QuizActivityScreen(quizViewModel, { navigateToNextQuestion(navController, quizViewModel) })
            }
            composable(MoneyWiseScreens.QuizScreen2.name){
                quizViewModel.retrieveNextQuestion()
                QuizActivityScreen(quizViewModel, { navigateToNextQuestion(navController, quizViewModel) })
            }
            composable(MoneyWiseScreens.QuizScreen3.name){
                quizViewModel.retrieveNextQuestion()
                QuizActivityScreen(quizViewModel, { navigateToNextQuestion(navController, quizViewModel) })
            }
            composable(MoneyWiseScreens.QuizScreen4.name){
                quizViewModel.retrieveNextQuestion()
                QuizActivityScreen(quizViewModel, { navigateToNextQuestion(navController, quizViewModel) })
            }
        }
    }
}

private fun navigateToNextQuestion(
    navController: NavHostController,
    quizViewModel: QuizViewModel
) {
    when(quizViewModel.questionCounter){
        0-> navController.navigate(MoneyWiseScreens.QuizScreen2.name)
        1 -> navController.navigate(MoneyWiseScreens.QuizScreen3.name)
        2 -> navController.navigate(MoneyWiseScreens.QuizScreen4.name)
    }
}



@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun QuizActivityScreen(quizViewModel: QuizViewModel, navigateToNextQuestion: ()->Unit){

    val selectedAnswer: String by quizViewModel.selectedAnswer.observeAsState("")
    val currentQuestion: QuizQuestion by quizViewModel.currentQuizQuestion.observeAsState(QuizQuestion("",listOf(""),""))
    //POPULATE VIEW MODEL DATA
    quizViewModel.retrieveQuizData()

    QuizScreen(
        currentQuestion = currentQuestion,
        onAnswerClick = {quizViewModel.selectAnswer(it)},
        selectedAnswer = selectedAnswer,
        navigateToNextQuestion = navigateToNextQuestion
    )
}

