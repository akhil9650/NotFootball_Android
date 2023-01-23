package not.football.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import not.football.ui.home.Greeting
import not.football.ui.theme.NotFootballTheme

@Composable
fun ProfileScreen(navController: NavController) {
    //val profileViewModel = hiltViewModel<ProfileViewModel>()


    NotFootballTheme {
        Greeting("Profile")
    }
}