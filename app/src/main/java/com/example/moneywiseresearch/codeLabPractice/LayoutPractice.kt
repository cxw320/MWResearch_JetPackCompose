package com.example.moneywiseresearch.codeLabPractice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moneywiseresearch.BodyContent
import com.example.moneywiseresearch.MyApp
import com.example.moneywiseresearch.R


@Composable
fun QuizScreen(){
    Scaffold(

    ){
        innerPadding->
        QuizLayout(Modifier.padding(innerPadding))
    }

}

@Composable
fun QuizLayout(modifier: Modifier = Modifier){
    Column(modifier = modifier.border(BorderStroke(2.dp, Color.Black)))
    {
        Text(text = "Hi there!", color = Color.Black)
        Text(text = "Thanks for going through the layouts codelab", color = Color.DarkGray)
    }
}
//
//Column(
//modifier = Modifier
//.fillMaxHeight()
//.fillMaxWidth(),
//verticalArrangement = Arrangement.Center
//){
//    Text("test")
//}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        QuizScreen()
    }

}