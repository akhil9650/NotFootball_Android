package not.football.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

open class MatchRepository(private val matchDAO: MatchDAO) {

    val allMatches: LiveData<List<Matches>> = matchDAO.getAllMatches()

    val searchResults = MutableLiveData<List<Matches>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun insertMatch(match: Matches) {
        coroutineScope.launch(Dispatchers.IO) {
            matchDAO.insertMatch(matches = match)
        }
    }
    /*
    fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.deleteProduct(name)
        }
    }

     */

    fun findMatchesByLeague(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Matches>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async matchDAO.findMatchesByLeague(name) //..findProduct(name)
        }

}