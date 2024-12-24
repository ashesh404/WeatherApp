package app.appstocks.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.appstocks.weatherapp.R

@Composable
fun SearchBar(searchResult: String, onSearchText: (String) -> Unit) {
    var search by remember { mutableStateOf(searchResult) }
    BasicTextField(
        value = search,
        onValueChange = {
            search = it
            onSearchText.invoke(search)
        },
        modifier = Modifier
            .fillMaxWidth()
//            .padding(10.dp)
            .height(46.dp)
            .width(327.dp)
            .background(
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                color = MaterialTheme.colorScheme.secondary
            ),
        decorationBox = { textfield ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 18.dp), contentAlignment = Alignment.CenterStart
            ) {
//                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                if (search.isEmpty()) {
                    Text("Search Location", color = MaterialTheme.colorScheme.tertiary)
                }

                textfield()

//                    Spacer(modifier = Modifier.weight(1.0f))

                Image(
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary),
                    painter = painterResource(R.drawable.search_24px),
                    modifier = Modifier
                        .padding(end = 22.dp)
                        .align(Alignment.CenterEnd),
                    contentDescription = ""
                )
//                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewSearchBar(modifier: Modifier = Modifier) {
//    SearchBar(Modifier, homeViewModel.onSearchText())
}