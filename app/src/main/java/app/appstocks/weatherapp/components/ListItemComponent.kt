package app.appstocks.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.appstocks.weatherapp.R
import app.appstocks.weatherapp.models.response.SearchListItem
import coil.compose.AsyncImage

@Composable
fun SearchListItems(list: List<SearchListItem>, onSearchItemClick: (SearchListItem) -> Unit) {
    LazyColumn() {
        items(list) { item ->
            ListItemComponent(item = item, onSearchItemClick)

            Spacer(modifier = Modifier.padding(bottom = 15.dp))
        }
    }
}

@Composable
fun ListItemComponent(item: SearchListItem, onSearchItemClick: (SearchListItem) -> Unit) {
    Box(modifier = Modifier
        .clickable {
            onSearchItemClick.invoke(item)
        }
        .fillMaxWidth()
        .background(
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            color = MaterialTheme.colorScheme.secondary
        ), contentAlignment = Alignment.Center) {

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Column(modifier = Modifier.padding(start = 30.dp).weight(0.6f)) {
                Text(
//                    modifier = Modifier.padding(top = 10.dp),
                    maxLines = 2,
                    text = item.name,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.W600
                )

                item.currentData?.current?.temp_c
                item.currentData?.let {
                    it.current.let { current ->
                        current.temp_c.let { temp_c ->
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                text = temp_c.toString(),
                                fontSize = 70.sp,
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                }
            }

//            Spacer(Modifier.padding(20.dp))

            /*Image(
                modifier = Modifier
                    .padding(end = 30.dp)
                    .weight(0.4f)
                    .height(80.dp)
                    .width(80.dp),
                painter = painterResource(R.drawable.ic_cloudy),
                contentDescription = ""
            )*/

            AsyncImage(
                model = "https://"+item.currentData?.current?.condition?.icon,
                contentDescription = "",
                modifier = Modifier.padding(top = 10.dp, start = 10.dp).size(100.dp),
            )
        }
    }
}

@Preview
@Composable
private fun ListItemPreview() {
    var list = mutableListOf(
        SearchListItem(
        id= 1104989,
        name= "Ahmedabad",
        region= "Gujarat",
        country="India",
        lat= 23.03,
        lon= 72.62,
        url= "ahmedabad-gujarat-india"
    ), SearchListItem(
        id= 1104989,
        name= "Delhi",
        region= "Gujarat",
        country="India",
        lat= 23.03,
        lon= 72.62,
        url= "ahmedabad-gujarat-india"
    ), SearchListItem(
        id= 1104989,
        name= "Gurugram",
        region= "Gujarat",
        country="India",
        lat= 23.03,
        lon= 72.62,
        url= "ahmedabad-gujarat-india"
    ), SearchListItem(
        id= 1104989,
        name= "Pune",
        region= "Gujarat",
        country="India",
        lat= 23.03,
        lon= 72.62,
        url= "ahmedabad-gujarat-india"
    ) )


    SearchListItems(list, {

    })
}