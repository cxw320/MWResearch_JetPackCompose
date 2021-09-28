package com.example.moneywiseresearch

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun SplashBody(onAnimationComplete:()->Unit){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.money))
    val progress by animateLottieCompositionAsState(composition)

    if (progress == 1f) { // if 1f, lottie animation is complete
        LaunchedEffect(progress) { // keeps track of progress state and is lifecycle aware
            onAnimationComplete() // navigates to login screen
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize()
    ){
        LottieAnimation(composition,progress)
    }

}

//@Composable
//fun Loader(){
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.money))
//    val progress by animateLottieCompositionAsState(composition)
//
//    Column(
//        modifier = Modifier
//            .fillMaxHeight()
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.Center
//    ){
//        LottieAnimation(composition)
//    }
//}