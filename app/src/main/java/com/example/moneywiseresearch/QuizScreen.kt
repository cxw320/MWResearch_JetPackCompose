package com.example.moneywiseresearch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun QuizScreen(
    currentQuestion: QuizQuestion,
    onAnswerClick: (String) -> Unit,
    selectedAnswer: String,
    navigateToNextQuestion: ()->Unit
    ){

    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        backgroundColor = Color(0xFFe8f5fa)
    ){
            innerPadding->
        QuizLayout(Modifier.padding(innerPadding),
            currentQuestion,
            onAnswerClick,
            selectedAnswer,
            navigateToNextQuestion)
    }

}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun QuizLayout(modifier: Modifier = Modifier,
                currentQuestion: QuizQuestion,
               onAnswerClick: (String)->Unit,
               selectedAnswer:String,
               navigateToNextQuestion: ()->Unit
               ) {


    ConstraintLayout(
        modifier = Modifier.size(780.dp).padding(20.dp)
    ) {
       // Log.d("Caroline","initial value for quiz layout: "+currentQuestion.correctAnswer)
        val (correctAnswer) = remember {mutableStateOf(currentQuestion.correctAnswer)}
        val (quizContainer) = createRefs()


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .constrainAs(quizContainer) {
                    top.linkTo(parent.top, margin = 7.dp)
                }
                .border(
                    BorderStroke(10.dp, Color.White)
                ))
        {

            Surface(
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .size(450.dp, 270.dp)
            ) {
                QuestionText(currentQuestion.questionText)
            }
            AnswerGrid(currentQuestion, onAnswerClick, selectedAnswer,navigateToNextQuestion)
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AnswerGrid(
    currentQuestion:QuizQuestion,
    onAnswerClick: (String) ->Unit,
    selectedAnswer: String,
    navigateToNextQuestion: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.primary,
    ) {

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            cells = GridCells.Fixed(2)
        ) {

            if (selectedAnswer == "") {
                items(currentQuestion.answerOptions) { answerOption ->
                    AnswerCardDefault(answerOption, onAnswerClick)
                }
            } else if (selectedAnswer != currentQuestion.correctAnswer) {
                items(currentQuestion.answerOptions) { answerOption ->
                    if(answerOption == currentQuestion.correctAnswer){
                        AnswerCardCorrect(answerOption,navigateToNextQuestion)
                    }else if(answerOption ==selectedAnswer){
                        AnswerCardIncorrect(answerOption)
                    }else{
                        AnswerCardDefault(answerOption,onAnswerClick)
                    }
                }
            } else {
                items(currentQuestion.answerOptions) { answerOption ->
                    if(answerOption ==currentQuestion.correctAnswer){
                        AnswerCardCorrect(answerOption,navigateToNextQuestion)
                    }else{
                        AnswerCardDefault(answerOption,onAnswerClick)
                    }

                }
            }
        }
    }
}

@Composable
fun QuestionText(questionText:String){
    ConstraintLayout {
        val(questionTextConstraint) = createRefs()

        Text(text = questionText,
            color = Color.White,
            modifier = Modifier.constrainAs(questionTextConstraint) {
                top.linkTo(parent.top, margin = 90.dp)
            }
                .padding(20.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
    }
}


@ExperimentalMaterialApi
@Composable
fun AnswerCardDefault(answerOption: String,onAnswerClick: (String) -> Unit ){
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier.size(200.dp)
            .padding(10.dp)
            .clickable {
                    onAnswerClick(answerOption)
                }
    ){
        AnswerText(answerOption,Color(0xFF555555))
    }
}

@Composable
fun AnswerCardCorrect(answerOption: String,navigateToNextQuestion: () -> Unit ){
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier.size(200.dp)
            .padding(10.dp)
            .clickable{
               navigateToNextQuestion()
            }
            .border(
                BorderStroke(5.dp, Color(0xFF62a54d))
            )

    ){
        AnswerText(answerOption,Color(0xFF62a54d))
    }
}

@Composable
fun AnswerCardIncorrect(answerOption: String ){
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier.size(200.dp)
            .padding(10.dp)
            .border(
                BorderStroke(5.dp, Color(0xFF900603))
            )
    ){
        AnswerText(answerOption,Color(0xFF900603))
    }
}

@Composable
fun AnswerText(answerOption: String, color:Color){
    ConstraintLayout {
        val(answerText) = createRefs()

        Text(text = answerOption,
            color = color,
            modifier = Modifier.constrainAs(answerText) {
                top.linkTo(parent.top, margin = 55.dp)
            }
                .padding(5.dp),
            fontSize = 16.sp,
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
    }
}


