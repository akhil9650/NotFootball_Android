package not.football.ui.matches

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
import java.util.*

@Composable
fun MatchesScreen(viewModel: MatchViewModel) {

    val allMatches by viewModel.allMatches.observeAsState(listOf())
    //val searchResults by viewModel.searchResults.observeAsState(listOf())

    LazyColumn(
        Modifier
            .fillMaxSize()
            //.padding(8.dp)
            .background(Color.Black)
    ) {
        item {
            //HorizontalScrollScreen()

            DateCard(viewModel.daysRange)
            Divider(color = Color.Gray, thickness = .5.dp, modifier = Modifier.padding(4.dp))
        }

       // itemsIndexed(allMatches) { index, match ->
        item(allMatches){
            MatchDayLayout(allMatches, "PL", 19, viewModel)
        }
        //Text(text = stringResource(id = R.string.screen_title_home), color = Color.White,
        //modifier = Modifier.padding(4.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DateCard(dates: List<LocalDate>) {


    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -7)

    val selectedIndex: MutableState<Int> = remember {
        mutableStateOf(4)
    }

    val selectedDate: MutableState<LocalDate> = remember {
        mutableStateOf(dates[4])
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                //horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = selectedDate.value.dayOfWeek.toString().slice(0..2) + ", "+ selectedDate.value.dayOfMonth.toString() + " " + selectedDate.value.month.toString(), //data.dayOfMonth.toString() + data.month.toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(8.dp),
                    color = Color.Red
                )

                Spacer(modifier = Modifier.weight(1F))

                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "TODAY")
                }

            }

            LazyRow(
                modifier = Modifier.background(Color.Black),
                state = rememberLazyListState(5,5)
            ) {

                itemsIndexed(dates) { index, date ->
                    Card(
                        modifier = Modifier
                            .height(50.dp)
                            .wrapContentWidth() // here is the trick
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        //.background(if (index.equals(0)) Color.Red else Color.Black),
                        elevation = 2.dp,
                        onClick = {
                            selectedIndex.value = index
                            selectedDate.value = date
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)

                        ) {
                            Text(
                                date.dayOfMonth.toString(),
                                style = MaterialTheme.typography.body2,
                                fontSize = 18.sp,
                                color = if (index == selectedIndex.value) Color.Red else Color.White
                            )

                            Text(
                                date.month.toString().slice(0..2),
                                style = MaterialTheme.typography.h6,
                                fontSize = 12.sp,
                                color = if (index == selectedIndex.value) Color.Red else Color.White
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MatchDayLayout(matchDataList: List<Matches>, league: String, week: Int, model: MatchViewModel) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "League Logo",
                modifier = Modifier.padding(4.dp)
            )

            Column {
                Text(
                    league,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    "Matchday $week",
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(Modifier.weight(1F))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "More")
            }
        }

        Divider(color = Color.Gray, thickness = .5.dp)

        Column {

            for (i in matchDataList.indices) {
                MatchCard(matchData = matchDataList.get(i))
            }
        }
    }
}

@Composable
fun MatchCard(matchData: Matches) {

    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Home",
                modifier = Modifier.weight(.2F)
            )
            Text(
                matchData.homeTeam,
                modifier = Modifier.weight(.2F),
                style = MaterialTheme.typography.body2
            )
            Text(
                matchData.homeGoals.toString() + "-" + matchData.awayGoals.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.weight(.2F)
            )
            //Spacer(Modifier.weight(1F))
            Text(
                matchData.awayTeam,
                modifier = Modifier.weight(.2F),
                style = MaterialTheme.typography.body2
            )
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Away",
                modifier = Modifier.weight(.2F)
            )
        }
    }
}
