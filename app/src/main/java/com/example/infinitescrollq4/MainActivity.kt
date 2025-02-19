package com.example.infinitescrollq4
import com.example.infinitescrollq4.ui.theme.InfiniteScrollQ4Theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.animation.core.animateDpAsState


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InfiniteScrollQ4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NumberGridScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun NumberGridScreen(modifier: Modifier = Modifier) {
    var numslist by remember { mutableStateOf((1..30).toList()) }
    val coroutinescope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            numslist = (1..30).toList()
        },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Reset",
            )
        }
    }
    LazyVerticalGrid(
        modifier = Modifier.padding(top = 80.dp), // avoid button and numbers overlap
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(50.dp),
        horizontalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        items(numslist) { number ->
            Text(
                text = number.toString(),
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(fontSize = 30.sp)
            )
        }
    }
    LaunchedEffect(numslist) {
        if (numslist.size % 30 == 0) {
            val lastIndex: Int

            if (numslist.isNotEmpty()) {
                lastIndex = numslist.last()
            } else {
                lastIndex = 0
            }

            coroutinescope.launch {
                delay(3333)
                numslist = numslist + ((lastIndex + 1)..(lastIndex + 30)).toList()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InfiniteScrollQ4Theme {
        NumberGridScreen()
    }
}
