package not.football.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import not.football.ui.home.HomeScreen
import not.football.ui.leagues.LeaguesScreen
import not.football.ui.matches.MatchesScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import not.football.ui.matches.MatchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationController(navController: NavHostController, viewModel: MatchViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Matches.route) {
            MatchesScreen(viewModel)
        }
        composable(Screen.Leagues.route) {
            LeaguesScreen()
        }
    }
}