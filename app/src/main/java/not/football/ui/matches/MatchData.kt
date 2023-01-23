package not.football.ui.matches

data class MatchData(
    val homeTeam:String,
    val awayTeam:String,
    val homeGoals:Int,
    val awayGoals:Int,
    val minutesPlayed:Int
)