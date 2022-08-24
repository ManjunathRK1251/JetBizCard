package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

//MainActivity inherits from ComponentActivity
class MainActivity : ComponentActivity() {

    //onCreate is the entry point for the android application
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                //surface is some sort of canvas that can be used to pass something else which is drawn on the surface
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard()
{
    //we are creating a variable that remembers the state of the button
    val buttonClickedState = remember {
        //mutableStateOf is the value type that needs to be remembered
        mutableStateOf(false)
    }
    //surface is just a canvas whenever we want to create something that has to been shown on the screen
    //fillMaxWidth() makes the whole surface, the whole canvas to fill max width
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    {
        Card(modifier = Modifier
            .width(200.dp) //dp is the android unit measure, this is how android measures pixels that we need
            .height(390.dp)
            .padding(12.dp),
            elevation = 4.dp, //elevation is not part of the Modifier, it's different property of the Card() composable
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White,
        )
        {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider(modifier = Modifier.padding(10.dp))
                CreateInfo()
                Button(
                    onClick = {
                       buttonClickedState.value = !buttonClickedState.value
                    }
                ) {
                    Text(
                        "Portfolio",
                        style = MaterialTheme.typography.button
                    )
                }
                if(buttonClickedState.value){
                    Content()
                }
                else {
                    Box{}
                }
            }

        }
    }
}

//@Preview
@Composable
fun Content(){
  Box(
      modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight()
          .padding(5.dp)
  )  {
      Surface(
          modifier = Modifier
              .padding(3.dp)
              .fillMaxWidth()
              .fillMaxHeight(),
          shape = RoundedCornerShape(corner = CornerSize(6.dp)),
          border = BorderStroke(width = 2.dp, color = Color.LightGray)
      ) {
            Portfolio(data = listOf(
                "Project 1",
                "Project 2",
                "Project 3",
                "Project 3",
                "Project 3",
                ))
      }
  }
}

@Composable
fun Portfolio(data: List<String>) {
    //LazyColumn creates a scrollable list and we get the advantage of recyclable views
    LazyColumn{
        items(data) {
            //data is the list, and each value of the list is stored in variable 'item'
                item -> Card(
                    modifier = Modifier
                        .padding(13.dp)
                        .fillMaxWidth(),
                        shape = RectangleShape,
                    elevation = 4.dp,
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(7.dp)
                ) {
                    CreateImageProfile(modifier = Modifier.size(100.dp))
                    Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great project", style = MaterialTheme.typography.body2)
                    }
                }
        }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp))
    {
        Text(
            text = "Miles Morale",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = "@therealmilesmorale",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
//    Modifier = Modifier makes the modifier field optional
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        //here we are just using the default color value and modifying it's visibility using alpha value
//                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        color = Color.White,
    )
    {
        Image(
            painter = painterResource(id = R.drawable.display_picture),
            contentDescription = "Profile picture",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
        //contentDescription helps visually impaired folks to be able to know what the image represents
        //ContentScale.Crop takes the entire view of the surface card
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}