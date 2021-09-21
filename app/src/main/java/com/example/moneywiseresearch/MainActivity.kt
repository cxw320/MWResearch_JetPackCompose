package com.example.moneywiseresearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moneywiseresearch.ui.theme.MoneyWiseResearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MyApp(
               { Loader() }
           )
        }
    }
}


@Composable
fun Loader(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.money))
    val progress by animateLottieCompositionAsState(composition)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ){
        LottieAnimation(composition)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        Loader()
    }

}




//COMPOSABLES TO PLAY AROUND ARE BELOW

@Composable
fun testingWeightOnColumn1(){
    Column {
        // The child with no weight will have the specified size.
        Box(Modifier.size(40.dp, 80.dp).background(androidx.compose.ui.graphics.Color.Magenta))
        // Has weight, the child will occupy half of the remaining height.
        Box(Modifier.width(40.dp).weight(1f).background(androidx.compose.ui.graphics.Color.Yellow))
        // Has weight and does not fill, the child will occupy at most half of the remaining height.
        // Therefore it will occupy 80.dp (its preferred height) if the assigned height is larger.
        Box(
            Modifier.size(40.dp, 80.dp)
                .weight(1f, fill = false)
                .background(androidx.compose.ui.graphics.Color.Green)
        )
    }
}

@Composable
fun testingWeightOnColumn2(){
    Column() {
        Box(
            Modifier.width(40.dp)
                .weight(3f)
                .background(androidx.compose.ui.graphics.Color.Green)
        )
        Box(
            Modifier.width(40.dp)
                .weight(1f)
                .background(androidx.compose.ui.graphics.Color.Red))
    }
}

@Composable
fun MyApp(content: @Composable ()->Unit){
    MoneyWiseResearchTheme{
        Surface(){
            content()
        }
    }
}


@Composable
fun CodeLabMyScreenContent(names: List<String> = List(1000){"Hello Android #$it"}){
    val counterState = remember{mutableStateOf(0)}

    Column(modifier = Modifier.fillMaxHeight()){
        NameList(names,Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount={newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier){
        items(items = names){ name ->
            Greeting(name = name)
            Divider(color = androidx.compose.ui.graphics.Color.Black)
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) ->Unit){
    Button(
        onClick = {updateCount(count+1)},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember {mutableStateOf(false)}
    val backgroundColor by animateColorAsState(if(isSelected) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Transparent)

    Text(text = "Hello $name!",color= androidx.compose.ui.graphics.Color.Black,
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = {isSelected = !isSelected})
    )
}