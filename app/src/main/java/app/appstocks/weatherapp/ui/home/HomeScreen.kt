package app.appstocks.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.appstocks.weatherapp.R
import app.appstocks.weatherapp.components.SearchBar
import app.appstocks.weatherapp.components.SearchListItems
import app.appstocks.weatherapp.components.WeatherDetailsComponent
import coil.compose.AsyncImage

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(top = 44.dp))

        Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
            SearchBar(homeViewModel.searchQuery.value, homeViewModel::onSearchText)

            Box(
                Modifier.fillMaxSize(),
                contentAlignment = if (homeViewModel.searchQuery.value.isEmpty()) Alignment.Center else Alignment.TopCenter
            ) {

                if (!homeViewModel.citySelected.value) {

                    if (homeViewModel.searchQuery.value.isEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.align(
                                alignment = BiasAlignment(
                                    horizontalBias = 0.0f,
                                    verticalBias = -0.2f
                                )
                            )
                        ) {
                            Text(
                                "No City Selected",
                                fontSize = 30.sp,
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight.W600
                            )

                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                text = "Please search for a City",
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight.W600
                            )
                        }
                    } else {
                        val searchList = homeViewModel.searchListItem.collectAsState(initial = emptyList())
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.align(
                                alignment = Alignment.TopCenter
                            )
                        ) {
                            Spacer(modifier = Modifier.padding(top = 20.dp))

                            SearchListItems(
                                searchList.value,
                                homeViewModel::onSearchItemClick
                            )
                        }
                    }
                } else {

                    homeViewModel.selectedItem.let {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.align(
                                alignment = BiasAlignment(
                                    horizontalBias = 0.0f,
                                    verticalBias = -0.7f
                                )
                            )
                        ) {
                            AsyncImage(
                                model = it.currentData?.current?.condition?.icon?.replace("//",""),
                                contentDescription = "",
                                modifier = Modifier.padding(top = 10.dp, start = 10.dp).size(100.dp),
                            )

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.padding(top = 10.dp),
                                    text = it.name,
                                    fontSize = 30.sp,
                                    color = MaterialTheme.colorScheme.background,
                                    fontWeight = FontWeight.W600
                                )

                                Image(
                                    modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                                    painter = painterResource(R.drawable.ic_location),
                                    contentDescription = ""
                                )
                            }

                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                text = it.region,
                                fontSize = 70.sp,
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight.W500
                            )

                            Spacer(Modifier.padding(20.dp))

                            // Weather details
                            WeatherDetailsComponent(it)
                        }
                    }

                    // Search List

                }

            }
        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
//    HomeScreen(modifier = Modifier)
}