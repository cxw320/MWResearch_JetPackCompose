package com.example.moneywiseresearch

import android.util.Log
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


@ExperimentalFoundationApi
@Composable
fun QuizScreen(
    questionText: String,
    answerOptions:List<String>,
    onAnswerClick: (String) -> Unit,
    selectedAnswer: String
    ){

    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        backgroundColor = Color(0xFFe8f5fa)
    ){
            innerPadding->
        QuizLayout(Modifier.padding(innerPadding),questionText,answerOptions, onAnswerClick,selectedAnswer)
    }

}

data class AnswerOption(val answerOptionText: String)

@ExperimentalFoundationApi
@Composable
fun QuizLayout(modifier: Modifier = Modifier,
               questionText: String,
               answerOptions: List<String>,
               onAnswerClick: (String)->Unit,
               selectedAnswer:String
               ) {


    ConstraintLayout(
        modifier = Modifier.size(780.dp).padding(20.dp)
    ) {

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
                QuestionText(questionText)
            }
            Surface(
                color = MaterialTheme.colors.primary,
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(12.dp),
                    cells = GridCells.Fixed(2)
//                        verticalArrangement = Arrangement.spacedBy(10.dp),
//                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(answerOptions) { answerOption ->
                        if(selectedAnswer==""){
                            AnswerCard(answerOption, onAnswerClick)
                        }else if (selectedAnswer == answerOption) {
                            AnswerCard(answerOption, onAnswerClick)
                        } else {
                            AnswerCardFaded(answerOption)
                        }
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


@Composable
fun AnswerCard(answerOption: String,onAnswerClick: (String) -> Unit ){
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier.size(200.dp)
            .padding(10.dp)
            .clickable{
                Log.d("Caroline","clickable is working")
                onAnswerClick(answerOption)
                Log.d("Caroline","clickable is working")}
    ){
        AnswerText(answerOption,Color(0xFF555555))
    }
}

@Composable
fun AnswerCardFaded(answerOption: String ){
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Gray,
        modifier = Modifier.size(200.dp)
            .padding(10.dp)
    ){
        AnswerText(answerOption,Color.DarkGray)
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


