package com.example.proyecto_30_diasapp_naim


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_30_diasapp_naim.data.DataSource
import com.example.proyecto_30_diasapp_naim.ui.theme.Proyecto_30_DiasAPP_NaimTheme





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyecto_30_DiasAPP_NaimTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FilmApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmApp(){
    Scaffold(
        topBar = {
            FilmTopAppBar()
        }
    ) {
        //FilmsList(films = films, Modifier.padding(it))
        FilmsList(filmsList = DataSource().loadFilms(), Modifier.padding(it))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Proyecto_30_DiasAPP_NaimTheme {
        FilmApp()
    }
}
