package com.example.moneywiseresearch.codeLabPractice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moneywiseresearch.BodyContent
import com.example.moneywiseresearch.MyApp
import com.example.moneywiseresearch.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference


@ExperimentalFoundationApi
@Composable
fun QuizScreen(){
    var answerOption1 = AnswerOption("Wealth, Security, Happiness")
    var answerOption2 = AnswerOption("Fredom, Liberty, Happiness")
    var answerOption3 = AnswerOption("Cash, Money, Wealth")
    var answerOption4 = AnswerOption("Credit, Checkings, Loans")
    var answerOptions : List<AnswerOption> = listOf(
        answerOption1,answerOption2,answerOption3,answerOption4)

    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        backgroundColor = Color(0xFFe8f5fa)
    ){
        innerPadding->
        QuizLayout(Modifier.padding(innerPadding),answerOptions)
    }

}

data class AnswerOption(val answerOptionText: String)

@ExperimentalFoundationApi
@Composable
fun QuizLayout(modifier: Modifier = Modifier, answerOptions: List<AnswerOption>){
    ConstraintLayout(
        modifier = Modifier.size(780.dp).padding(20.dp)
    ) {

        val (quizContainer, questionText) = createRefs()


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
                    QuestionText()
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
                            AnswerCard(answerOption)
                        }
                    }
                }
            }
        }
}

@Composable
fun QuestionText(){
    ConstraintLayout {
        val(questionText) = createRefs()

        Text(text = "What metrics are a part of your financial journey?",
            color = Color.White,
            modifier = Modifier.constrainAs(questionText) {
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
fun AnswerCard(answerOption: AnswerOption){
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier.size(200.dp).padding(10.dp)
    ){
        AnswerText(answerOption)
    }
}

@Composable
fun AnswerText(answerOption: AnswerOption){
    ConstraintLayout {
        val(answerText) = createRefs()

        Text(text = answerOption.answerOptionText,
            color = Color(0xFF555555),
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




//
//LazyVerticalGrid(
//modifier = Modifier.fillMaxWidth().padding(10.dp),
//cells = GridCells.Fixed(2)){
//    items(books){ book ->
//        BookCard(book)
//    }
//}
//ConstraintLayout {
//    // Creates references for the three composables
//    // in the ConstraintLayout's body
//    val (button1, button2, text) = createRefs()
//
//    Button(
//        onClick = { /* Do something */ },
//        modifier = Modifier.constrainAs(button1) {
//            top.linkTo(parent.top, margin = 16.dp)
//        }
//    ) {
//        Text("Button 1")
//    }
//
//    Text("Text", Modifier.constrainAs(text) {
//        top.linkTo(button1.bottom, margin = 16.dp)
//        centerAround(button1.end)
//    })
//
//    val barrier = createEndBarrier(button1, text)
//    Button(
//        onClick = { /* Do something */ },
//        modifier = Modifier.constrainAs(button2) {
//            top.linkTo(parent.top, margin = 16.dp)
//            start.linkTo(barrier)
//        }
//    ) {
//        Text("Button 2")
//    }
//}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        QuizScreen()
    }

}