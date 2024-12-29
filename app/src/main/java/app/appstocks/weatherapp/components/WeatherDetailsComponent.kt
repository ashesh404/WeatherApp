package app.appstocks.weatherapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.appstocks.domain.SearchListItem

@Composable
fun WeatherDetailsComponent(item: app.appstocks.domain.SearchListItem) {
    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                color = MaterialTheme.colorScheme.secondary
            ), contentAlignment = Alignment.TopCenter
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text("Humidity", color = MaterialTheme.colorScheme.tertiary, fontSize = 12.sp)

                item.currentData?.current?.humidity?.let {
                    Text(
                        "$it%",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 15.sp,
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text("UV", color = MaterialTheme.colorScheme.tertiary, fontSize = 12.sp)

                item.currentData?.current?.uv?.let {
                    Text(
                        it.toString(),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 15.sp,
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text("Feels Like", color = MaterialTheme.colorScheme.tertiary, fontSize = 8.sp)

                item.currentData?.current?.feelslike_c.let {
                    Text(
                        it.toString(),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 15.sp,
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun WeatherDetailsPreview() {
//    WeatherDetailsComponent(Modifier)
}