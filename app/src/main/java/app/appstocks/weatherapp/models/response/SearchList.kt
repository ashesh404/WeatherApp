package app.appstocks.weatherapp.models.response

data class SearchListItem(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String,
    var currentData: CurrentDataResponse? = null
)

class SearchList : ArrayList<SearchListItem>()
//class SearchList(items: ArrayList<SearchListItem>)