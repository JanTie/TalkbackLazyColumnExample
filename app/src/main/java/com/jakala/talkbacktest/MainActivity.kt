package com.jakala.talkbacktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jakala.talkbacktest.ui.theme.TalkbackTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TalkbackTestTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "A",
                ) {
                    composable(
                        route = "A",
                    ) {
                        ScreenA(
                            onNavIconClick = { navController.navigateUp() },
                            onItemClick = { navController.navigate("B") }
                        )
                    }

                    composable(
                        route = "B",
                    ) {
                        ScreenB(
                            onNavIconClick = { navController.navigateUp() }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenA(
    onNavIconClick: () -> Unit,
    onItemClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Test")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavIconClick() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
        modifier = Modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            // remove this and with a single item it will have correct focus
            modifier = Modifier.fillMaxSize()
        ) {
            // increase this and at some point the focus will jump to wrong item again
            items(1) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Red)
                        .clickable {
                            onItemClick()
                        }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenB(
    onNavIconClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Test")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavIconClick() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier.navigationBarsPadding(),
            ) {
                Button(
                    onClick = {}
                ) {
                    Text("Button A")
                }
                // removing down to 1 button at the bottom can potentially also fix the issue
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {}
                ) {
                    Text("Button B")
                }
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(padding)
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenAPreview() {
    TalkbackTestTheme {
        ScreenA(
            onNavIconClick = {},
            onItemClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenBPreview() {
    TalkbackTestTheme {
        ScreenB(
            onNavIconClick = {},
        )
    }
}