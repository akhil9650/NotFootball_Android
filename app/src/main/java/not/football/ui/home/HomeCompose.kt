package not.football.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text

import androidx.compose.ui.tooling.preview.Preview
import not.football.ui.theme.NotFootballTheme
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import not.football.R

@Composable
fun HomeScreen() {
    Box(
        //contentAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth().wrapContentHeight().padding(8.dp).background(Color.Black)
    ) {
        Column(
        ){
            //HorizontalScrollScreen()

            Text(text = stringResource(id = R.string.screen_title_home), color = Color.White)
            //HorizontalScrollScreen()

        }

    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotFootballTheme {
        //HorizontalScrollScreen()
    }
}
