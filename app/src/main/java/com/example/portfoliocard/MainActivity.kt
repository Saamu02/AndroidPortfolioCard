package com.example.portfoliocard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portfoliocard.ui.theme.PortfolioCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreatePortfolioCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CreatePortfolioCard(modifier: Modifier = Modifier) {
    val portfolioButtonClicked = remember {
        mutableStateOf(value = false)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(15.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HeaderView()

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 10.dp)
                )

                Button(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    onClick = {
                        portfolioButtonClicked.value = !portfolioButtonClicked.value
                    }
                ) {
                    Text(
                        text = "Portfolio"
                    )
                }
                
                if (portfolioButtonClicked.value) {
                    Content()
                }
            }
        }
    }
}

@Composable
private fun HeaderView() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage()

        UserDetailedView()
    }
}

@Composable
private fun ProfileImage(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier
            .size(120.dp)
            .padding(5.dp)
            .padding(top = 5.dp)
            .padding(),
        shape = CircleShape,
        border = BorderStroke(
            width = 0.5.dp,
            Color.LightGray
        ),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun UserDetailedView() {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {

        Text(
            text = "Ussama Irfan",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primaryContainer
        )

        Text(
            text = "Mobile Developer",
            modifier = Modifier
                .padding(top = 5.dp)
        )

        Text(
            text = "iusamairfan@gmail.com",
            modifier = Modifier
                .padding(top = 5.dp),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {

        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray),
            color = Color.White
        ) {
            Portfolio(data = listOf("Metric", "Revohloo", "ClaimIt"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {

    LazyColumn {

        items(data) { item ->

            PortfolioItem(projectName = item)
        }
    }
}

@Composable
fun PortfolioItem(projectName: String = "Project 1", projectDescription: String = "A great project") {

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(6.dp)
        ) {

            ProfileImage(
                modifier = Modifier
                    .size(100.dp)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {

                Text(
                    text = projectName,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = projectDescription,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PortfolioCardTheme {
        CreatePortfolioCard()
    }
}