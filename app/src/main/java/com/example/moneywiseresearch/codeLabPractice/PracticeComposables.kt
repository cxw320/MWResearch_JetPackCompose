package com.example.moneywiseresearch.codeLabPractice

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
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
import com.example.moneywiseresearch.ui.theme.MoneyWiseResearchTheme
import kotlinx.coroutines.launch

@Composable
fun test(){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.size(100.dp),
        backgroundColor = Color.DarkGray
    ){}
}


//INTRINSICS
@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),
            text = text2
        )
    }
}


@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin= margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(guideline, parent.end)
                width = Dimension.preferredWrapContent
            }
        )
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // Creates references for the three composables
        // in the ConstraintLayout's body
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }
    }
}


fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Composable
fun MyApp(content: @Composable ()->Unit){
    MoneyWiseResearchTheme{
        Surface(){
            content()
        }
    }
}


//LAYOUTS IN JETPACK COMPOSE (CODELAB #2) PRACTICE COMPOSABLES

@Composable
fun ImageList(){
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column{
        Row{
            Button(
                onClick = {
                    coroutineScope.launch{
                        scrollState.animateScrollToItem(0)
                    }
                }
            ){
                Text("Scroll to the top")
            }
            Button(onClick = {
                coroutineScope.launch{
                    scrollState.animateScrollToItem(listSize -1)
                }
            }){
                Text("Scroll to the end")
            }
        }
    }

    LazyColumn(state= scrollState){
        items(listSize){
            ImageListItem(it)
        }
    }
}

@Composable
fun ImageListItem(index: Int){

    Row(verticalAlignment = Alignment.CenterVertically){

        Image(
            painter= rememberImagePainter(
                data ="https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", color= Color.Black,style = MaterialTheme.typography.subtitle1)

    }
}


@Composable
fun LayoutsCodeLab(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodeLab", color = Color.Black)
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ){
                        Icon(Icons.Filled.Favorite,contentDescription = null)
                    }
                }
            )
        }
    ){ innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier){
    Column(modifier = modifier){
        Text(text = "Hi there!", color = Color.Black)
        Text(text = "Thanks for going through the layouts codelab", color = Color.DarkGray)
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(modifier
        .padding(16.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .clickable(onClick={})
        .padding(16.dp)
    ){
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
        ){
            //Images goes here
        }
        Column(
            modifier = Modifier.padding(start = 5.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold, color = Color.Black)
            // LocalContentAlpha is defining opacity level of its children
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2, color= Color.Black)
            }
        }
    }

}



//LOTTIE ANIMATION COMPOSABLE PRACTICE
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



//JETPACK COMPOSE BASICS (CODELAB #1) PRACTICE COMPOSABLES

@Composable
fun testingWeightOnColumn1(){
    Column {
        // The child with no weight will have the specified size.
        Box(Modifier.size(40.dp, 80.dp).background(Color.Magenta))
        // Has weight, the child will occupy half of the remaining height.
        Box(Modifier.width(40.dp).weight(1f).background(Color.Yellow))
        // Has weight and does not fill, the child will occupy at most half of the remaining height.
        // Therefore it will occupy 80.dp (its preferred height) if the assigned height is larger.
        Box(
            Modifier.size(40.dp, 80.dp)
                .weight(1f, fill = false)
                .background(Color.Green)
        )
    }
}

@Composable
fun testingWeightOnColumn2(){
    Column() {
        Box(
            Modifier.width(40.dp)
                .weight(3f)
                .background(Color.Green)
        )
        Box(
            Modifier.width(40.dp)
                .weight(1f)
                .background(Color.Red))
    }
}




@Composable
fun CodeLabMyScreenContent(names: List<String> = List(1000){"Hello Android #$it"}){
    val counterState = remember{ mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()){
        NameList(names, Modifier.weight(1f))
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
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) ->Unit){
    Button(
        onClick = {updateCount(count+1)},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if(isSelected) Color.Red else Color.Transparent)

    Text(text = "Hello $name!",color= Color.Black,
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = {isSelected = !isSelected})
    )
}