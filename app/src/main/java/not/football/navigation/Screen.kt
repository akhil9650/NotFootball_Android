package not.football.navigation


sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Matches : Screen("matches_screen")
    object Leagues : Screen("league_screen")
}