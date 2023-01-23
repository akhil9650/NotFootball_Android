package not.football.ui.matches

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(application: Application) : ViewModel() {
    private val _selected = MutableLiveData(0)

    private val date: LocalDate = LocalDate.now().minusDays(4)
    val daysRange: List<LocalDate> = Stream.iterate(date) { date ->
        date.plusDays(
            1
        )
    }.limit(ChronoUnit.DAYS.between(date, date.plusDays(14))).collect(
        Collectors.toList()
    )

    val loading = mutableStateOf(true)

    private val matchesList = mutableStateListOf(
        Gameweek(
            "PL",
            19,
            listOf(
                Matches(
                    "PL",
                    "ARS",
                    4,
                    "MCI",
                    0,
                    "",
                ),
                Matches(
                    "PL",
                    "MUN",
                    4,
                    "MCI",
                    0,
                    "",
                ),
                Matches(
                    "PL",
                    "CHE",
                    2,
                    "LIV",
                    2,
                    "",
                ),
                Matches(
                    "PL",
                    "CRY",
                    4,
                    "LIV",
                    0,
                    "",
                ),

            )
        )
    )

    val allMatches: LiveData<List<Matches>>

    private val repository: MatchRepository
    val searchResults: MutableLiveData<List<Matches>>

    init {
        val matchDB = MatchesRoomDatabase.getInstance(application)
        val matchDAO = matchDB.matchDAO()
        repository = MatchRepository(matchDAO)
        getMatches()

        allMatches = repository.allMatches
        searchResults = repository.searchResults
    }

    
    fun insertMatch(match: Matches) {
        repository.insertMatch(match)
    }
    /*
        fun deleteProduct(name: String) {
        repository.deleteProduct(name)
    }
     */

    fun findMatchesByLeague(name: String) {
        repository.findMatchesByLeague(name)
    }




    fun onValueChanged(selected: Int) {
        _selected.value = selected
    }

    fun getDaysRange(){
    }

    private fun getMatches(){


        for (i in 1..matchesList.size) {
            val matchList = matchesList[i-1].matchDataList
            for (j in 1..matchList.size){
                insertMatch(match = matchList[j-1])
            }
        }
    }

}

