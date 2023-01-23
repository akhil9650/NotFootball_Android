package not.football.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import not.football.ui.matches.MatchData
import not.football.ui.matches.MatchRepository
import javax.inject.Inject
/*
// @HiltViewModel will make models to be
// created using Hilt's model factory
// @Inject annotation used to inject all
// dependencies to view model class
@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {
    private val matchDataEmitter = MutableLiveData<List<MatchData>>()
    val cryptoCurrency: LiveData<List<MatchData>> = matchDataEmitter
    init {
        loadCryptocurrency()
    }

    // getting cryptocurrencies list using
    // repository and passing it into live data
    private fun loadCryptocurrency() {
        matchDataEmitter.value = matchRepository.getMatches()
    }
}

 */
