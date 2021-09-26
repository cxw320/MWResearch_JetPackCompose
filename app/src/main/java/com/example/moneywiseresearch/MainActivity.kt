package com.example.moneywiseresearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moneywiseresearch.codeLabPractice.*
import com.example.moneywiseresearch.ui.theme.MoneyWiseResearchTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        val quizViewModel by viewModels<QuizViewModel>()

        super.onCreate(savedInstanceState)
        setContent {
           MyApp(
               { QuizActivityScreen(quizViewModel) }
           )
        }
    }
}

//@ExperimentalFoundationApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyApp{
//
//    }
//}


@ExperimentalFoundationApi
@Composable
fun QuizActivityScreen(quizViewModel: QuizViewModel){
    val questionText : String by quizViewModel.questionText.observeAsState("")
    val answerOptions : List<String> by quizViewModel.answerText.observeAsState(listOf())
    val selectedAnswer: String by quizViewModel.selectedAnswer.observeAsState("")

    //POPULATE VIEW MODEL DATA
    quizViewModel.retrieveQuizData()

    QuizScreen(
        questionText = questionText,
        answerOptions = answerOptions,
        onAnswerClick = {quizViewModel.selectAnswer(it)},
        selectedAnswer = selectedAnswer
    )
}