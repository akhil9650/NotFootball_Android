package not.football.ui.matches

import android.icu.util.Calendar
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("matches")
class Matches {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0

    var league: String = ""
    var homeTeam: String = ""
    var homeGoals: Int = 0
    var awayTeam: String = ""
    var awayGoals: Int = 0
    var matchTime: String = Calendar.getInstance().time.toString()
    var minutesPlayed: Int = 0

    constructor() {}


    constructor(
        league: String,
        homeTeam: String,
        homeGoals: Int,
        awayTeam: String,
        awayGoals: Int,
        matchTime: String
    ) {
        this.league = league
        this.homeTeam = homeTeam
        this.homeGoals = homeGoals
        this.awayTeam = awayTeam
        this.awayGoals = awayGoals
        this.matchTime = matchTime
    }

    constructor(league: String, homeTeam: String, awayTeam: String, matchTime: String) {
        this.league = league
        this.homeTeam = homeTeam
        this.awayTeam = awayTeam
        this.matchTime = matchTime
    }

    constructor(
        homeTeam: String,
        homeGoals: Int,
        awayTeam: String,
        awayGoals: Int,
        minutesPlayed: Int
    ) {
        this.homeTeam = homeTeam
        this.homeGoals = homeGoals
        this.awayTeam = awayTeam
        this.awayGoals = awayGoals
        this.minutesPlayed = minutesPlayed
    }


}