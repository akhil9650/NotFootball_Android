package not.football.ui.matches

data class Gameweek(
    val league:String,
    val week:Int,
    val matchDataList:List<Matches>,
)