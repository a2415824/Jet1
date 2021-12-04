package com.example.jet1

import android.content.res.Configuration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jet1.ui.theme.Jet1Theme
import com.google.android.material.shape.TriangleEdgeTreatment
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.animateColorAsState as animateColorAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jet1Theme {

                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author:String,val body:String)

@Composable
fun MessageCard(msg:Message ){
    Row (modifier = Modifier.padding(all = 8.dp)){
        Image(
            painter = painterResource( id = R.drawable.im45) , contentDescription = "Contact profile picture",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))

        var isExpanded by remember {mutableStateOf(false)}
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )


        
        Column (modifier = Modifier.clickable { isExpanded = !isExpanded }){
            Text(text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2,
                )
            Spacer(modifier = Modifier.height(10.dp))




            Surface(shape = MaterialTheme.shapes.medium,elevation = 1.dp,
            color = surfaceColor,modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),

                    style = MaterialTheme.typography.body2,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1

                )
            }
        }
    }

}
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name= "Dark Mode"
)
@Composable
fun PreviewMessageCard(){
    Jet1Theme {
        MessageCard(
            msg = Message("lets begin with new jetpack", "its something new to use")
        )
    }
}
@Composable
fun Conversation(messages:List<Message>){
    LazyColumn{
        items(messages){
            message -> MessageCard(message)
        }
    }
}
@Preview
@Composable
fun PreviewConversation(){
    Jet1Theme {
        Conversation(SampleData.conversationSample)

    }
}