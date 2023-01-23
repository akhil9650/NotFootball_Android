package not.football

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
//import not.football.di.MainViewModel
import not.football.navigation.BottomNavigationBar
import not.football.navigation.NavigationController
import not.football.ui.matches.MatchViewModel
import not.football.ui.theme.NotFootballTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private val viewModel: MainViewModel by viewModels()
    //private val matchViewModel: MatchViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotFootballTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = { TopAppBar(title = {Text("NOTFOOTBALL")},backgroundColor = MaterialTheme.colors.background,
                            contentColor = Color.White)},
                        bottomBar = { BottomNavigationBar(navController) }
                    ) { padding ->


                        val owner = LocalViewModelStoreOwner.current

                        owner?.let {
                            val matchViewModel: MatchViewModel = viewModel(
                                it,
                                "MainViewModel",
                                MatchViewModelFactory(
                                    LocalContext.current.applicationContext
                                            as Application
                                )
                            )
                            Box(modifier = Modifier.padding(padding)) {
                                NavigationController(navController = navController, viewModel = matchViewModel)
                        }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotFootballTheme {
        Greeting("Android")
    }
}

class MatchViewModelFactory(val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MatchViewModel(application) as T
        } }