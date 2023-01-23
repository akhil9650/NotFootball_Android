package not.football.ui.matches

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDAO {

    @Insert
    fun insertMatch(matches: Matches)

    @Query("SELECT * FROM matches WHERE league = :league")
    fun findMatchesByLeague(league: String): List<Matches>

    //@Query("DELETE FROM matches WHERE matchesName = :name")
    //fun deletematches(name: String)

    @Query("SELECT * FROM matches")
    fun getAllMatches(): LiveData<List<Matches>>
}